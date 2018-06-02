package com.ksit.tms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ksit.tms.entity.*;
import com.ksit.tms.exception.ServiceException;
import com.ksit.tms.mapper.TicketInRecordMapper;
import com.ksit.tms.mapper.TicketMapper;
import com.ksit.tms.mapper.TicketOutRecordMapper;
import com.ksit.tms.mapper.TicketStoreMapper;
import com.ksit.tms.service.TicketService;
import com.ksit.tms.util.ShiroUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class TicketServiceImpl implements TicketService {

    //记录日志
    private Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

    @Autowired
    private TicketInRecordMapper ticketInRecordMapper;
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private TicketStoreMapper ticketStoreMapper;
    @Autowired
    private TicketOutRecordMapper ticketOutRecordMapper;

    /**
     * 新增年票记录
     *
     * @param ticketInRecord
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void insertTicketInRecord(TicketInRecord ticketInRecord) {
        Account account = (Account) SecurityUtils.getSubject().getPrincipal();
        //region 新增年票记录
        //1.检查票号是否合法
        //2.判断当前票号和已存在的票号是否重复,如果重复打回
        //3.设置新增时间
        //4.每次插入都要设置更新数量
        //5.将每次入库的备注设置为票号区间
        //6.保存日志
        //7.将年票保存到年票表中,每一张年票都要保存一条
        //8.事务回滚
        //endregion

        //检查票号是否合法,就是截至票号不能大于开始票号
        BigInteger begin = new BigInteger(ticketInRecord.getBeginTicketNum());
        BigInteger end = new BigInteger(ticketInRecord.getEndTicketNum());
        //检查
        if (begin.compareTo(end)>=0){
            throw new ServiceException("结束票号不能小于起始票号");
        }

        //判断当前票号和已经存在的票号是否重复,去将所有的记录都查出来,比对开始和结束票号
        for (TicketInRecord inRecord : ticketInRecordMapper.selectByExample(new TicketInRecordExample())) {
            if (inRecord!=null){
                BigInteger recordEnd = new BigInteger(inRecord.getEndTicketNum());
                BigInteger recordStart = new BigInteger(inRecord.getBeginTicketNum());
                //如果新插入的记录的开始票号比任何一个记录的结束票号小,打回
                if (begin.compareTo(recordEnd)<=0){
                    throw new ServiceException("票号区间重复");
                }
            }
        }

        //设置入库时间
        ticketInRecord.setCreateTime(new Date());

        //总数量 = (结束数量-开始数量) + 1 ,总数量还有用,不要使用匿名对象
        int total = end.subtract(begin).add(new BigInteger("1")).intValue();
        ticketInRecord.setTotalNum(total);

        //设置备注
        ticketInRecord.setContent(begin + "-" + end);

        //为TicketInRecord 设置accountId 和account name
        ticketInRecord.setAccountId(account.getId());
        ticketInRecord.setAccountName(account.getAccountName());

        //exec
        ticketInRecordMapper.insertSelective(ticketInRecord);
        //记录日志,同时记录操作账户
        logger.info("插入年票记录{},操作账户:{}",ticketInRecord,account);

        //处理关联关系,保存每一张年票到年票表中去
        List<Ticket> ticketList = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            //每有一条记录,创建一个Ticket 对象
            Ticket ticket = new Ticket();
            ticket.setCreateTime(new Date());
            ticket.setTicketInTime(new Date());
            //设置票号,作为ticket 的id 使用
            ticket.setTicketNum(String.valueOf(begin.add(BigInteger.valueOf(i))));
            ticket.setTicketState(Ticket.TICKET_STATE_IN_STORE);
            ticketList.add(ticket);
        }

        //使用批量保存TicketList,而不是一张一张来,太慢
        batchInsertTicket(ticketList);
    }

    /**
     * 查询分页数据
     *
     * @param pageNo
     * @return
     */
    @Override
    public PageInfo<TicketInRecord> selectTicketRecordByPageNo(Integer pageNo) {
        //对数据分页需要一个PageHelper 对象,使用这个对象来设置相关参数,每页显示五条数据
        PageHelper.startPage(pageNo,5);

        TicketInRecordExample ticketInRecordExample = new TicketInRecordExample();

        //使用id 排序,倒排
        ticketInRecordExample.setOrderByClause("id desc");

        List<TicketInRecord> ticketInRecords = ticketInRecordMapper.selectByExample(ticketInRecordExample);
        return new PageInfo<>(ticketInRecords);
    }

    /**
     * 删除年票入库记录
     * 事务回滚
     * @param id
     */
    @Override
    @Transactional
    public void deleteTicketInRecord(Integer id) {
        TicketInRecord ticketInRecord = ticketInRecordMapper.selectByPrimaryKey(id);
        //如果ticketInRecord 不为空的话
        if (ticketInRecord!=null){

            //判断该记录对应的年票数量和入库记录数量是否相同,不相同不能删除
            //判断票号是否还在入库记录的那个区间内,并且状态是否和入库的时候的一样,如果全部都一样,说明未修改,否则就是修改了
            //这个查出来的是全部的ticket
            List<Ticket> ticketList = ticketMapper.selectListByBenginEndAndState(ticketInRecord.getBeginTicketNum(),ticketInRecord.getEndTicketNum(),Ticket.TICKET_STATE_IN_STORE);

            //如果数量不相同
            if (!ticketInRecord.getTotalNum().equals(ticketList.size())){
                throw new ServiceException("年票状态已经发生改变,不能删除");
            }

            //如果没有修改的话,就是年票还和入库的时候状态一样,则可以删除
            //自己实现删除逻辑
            ArrayList<Long> idLists = Lists.newArrayList(Collections2.transform(ticketList, new Function<Ticket, Long>() {
                @Nullable
                @Override
                public Long apply(@Nullable Ticket ticket) {
                    return Long.valueOf(ticket.getId());
                }
            }));

            ticketMapper.batchDelete(idLists);
            ticketInRecordMapper.deleteByPrimaryKey(ticketInRecord.getId());

        }
    }

    /**
     * 年票下发记录分页处理
     *
     * @param pageNo
     * @return
     */
    @Override
    public PageInfo<TicketOutRecord> selectTicketOutRecordByPageNo(Integer pageNo) {

        PageInfo<TicketOutRecord> pageInfo = selectTicketOutRecordByPageNoAndQueryParam(pageNo, Maps.newHashMap());

        return pageInfo;
    }

    /**
     * 年票下发记录
     *
     * @param ticketOutRecord
     */
    @Override
    public void saveTicketOutRecord(TicketOutRecord ticketOutRecord) {
        //如果输入的票段内有状态非 [已入库状态的],则说明票段被修改过,就不能下发
        //将前端输入的票段从数据库中查出来,如果其中有一张票的状态不是 [已入库,则不能下发该票段]
        List<Ticket> ticketList = ticketMapper.selectListByBeginAndEnd(ticketOutRecord.getBeginTicketNum(),ticketOutRecord.getEndTicketNum());
        for (Ticket t : ticketList) {
            if (!Ticket.TICKET_STATE_IN_STORE.equals(t.getTicketState())){
                throw new ServiceException("该票段内存在已下发的票,不可下发该票段");
            }
        }

        //获取当前下发的售票点对象,并赋值售票点名称
        //下发记录中有售票点的id,找到
        Integer storeAccountId = ticketOutRecord.getStoreAccountId();
        TicketStore ticketStore = ticketStoreMapper.selectByPrimaryKey(storeAccountId);

        //获取售票点名称
        String storeName = ticketStore.getStoreName();

        //整理下发的票段的信息,封装为一个TicketOutRecord,前端传过来的只是下发票段,还有其他的信息没有填充
        ticketOutRecord.setStoreAccountName(storeName);
        //获取总价格,总价格为单价*数量
        int totalNumber = ticketList.size();
        BigDecimal totalPrice = ticketOutRecord.getPrice().multiply(new BigDecimal(totalNumber));

        //获取当前登陆的账号,来对下发记录
        Account currAccount = ShiroUtil.getCurrAccount();
        ticketOutRecord.setCreateTime(new Date());
        ticketOutRecord.setContent(ticketOutRecord.getBeginTicketNum()+"-"+ticketOutRecord.getEndTicketNum());
        ticketOutRecord.setOutAccountName(currAccount.getAccountName());
        ticketOutRecord.setOutAccountId(currAccount.getId());
        ticketOutRecord.setTotalNum(totalNumber);
        ticketOutRecord.setState(TicketOutRecord.STATE_NO_PAY);
        ticketOutRecord.setTotalprice(totalPrice);

        ticketOutRecordMapper.insertSelective(ticketOutRecord);
        logger.info("新增一条年票下发记录{}",ticketOutRecord);
    }

    /**
     * 删除年票下发记录
     *
     * @param id
     */
    @Override
    public void deleteTicketOutRecord(Integer id) {
        // TODO: 2018/4/27 0027 根据主键查找对应的记录,如果存在的话,执行删除操作
        // TODO: 2018/4/27 0027 只有未支付的记录才可以删除,已支付的记录将无法删除

        TicketOutRecord ticketOutRecord = ticketOutRecordMapper.selectByPrimaryKey(id);
        if (ticketOutRecord!=null){
            if (TicketOutRecord.STATE_NO_PAY.equals(ticketOutRecord.getState())){
                ticketOutRecordMapper.deleteByPrimaryKey(id);
            }
        }
    }

    /**
     * 将票的状态统计一下,发送到前端
     *
     * @return
     */
    @Override
    public Map<String, Long> countTicketByState() {

        Map<String, Long> resultMap = ticketMapper.countTicketByState();

        return resultMap;
    }

    /**
     * 财务相关
     * 根据状态和页码将年票下发记录查出来
     *
     * @param queryParam
     * @param pageNo
     * @return
     */
    @Override
    public PageInfo<TicketOutRecord> selectTicketOutRecordByParam(Map<String, Object> queryParam, Integer pageNo) {
        return selectTicketOutRecordByPageNoAndQueryParam(pageNo,queryParam);
    }

    /**
     * 根据id 查询年票下发记录
     *
     * @param id
     * @return
     */
    @Override
    public TicketOutRecord selectticketoutrecordbyId(Integer id) {
        return ticketOutRecordMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据id 对下发的订单支付
     *
     * @param id
     * @param payType
     */
    @Override
    public void payTicketOutRecord(Integer id, String payType) {
        // TODO: 2018/5/2 0002 查出记录,如果状态正确,修改状态 ,并且将对应票段的票的状态修改为已支付

        TicketOutRecord ticketOutRecord = ticketOutRecordMapper.selectByPrimaryKey(id);
        if (ticketOutRecord!=null && TicketOutRecord.STATE_NO_PAY.equals(ticketOutRecord.getState())){
            Account currAccount = ShiroUtil.getCurrAccount();

            ticketOutRecord.setPayType(payType);
            ticketOutRecord.setState(TicketOutRecord.STATE_PAY);
            ticketOutRecord.setFinanceAccountId(currAccount.getId());
            ticketOutRecord.setFinanceAccountName(currAccount.getAccountName());

            //更新这条下发记录
            ticketOutRecordMapper.updateByPrimaryKeySelective(ticketOutRecord);

            //更新对应的票段,该记录中有开始票号和结束票号
            List<Ticket> ticketList = ticketMapper.selectListByBeginAndEnd(ticketOutRecord.getBeginTicketNum(), ticketOutRecord.getEndTicketNum());

            for (Ticket ticket : ticketList) {
                ticket.setTicketState(Ticket.TICKET_STATE_OUT_STORE);
                ticket.setStoreAccountId(ticketOutRecord.getStoreAccountId());
                ticket.setTicketOutTime(DateTime.now().toString("YYYY-MM-dd"));
                ticket.setUpdateTime(new Date());
                ticketMapper.updateByPrimaryKeySelective(ticket);
            }


        }
    }


    /**
     * 根据当前页号和查询参数去查询分页数据
     * @param pageNo
     * @param queryParam
     * @return
     */
    private PageInfo<TicketOutRecord> selectTicketOutRecordByPageNoAndQueryParam(Integer pageNo,Map<String,Object> queryParam){
        PageHelper.startPage(pageNo,5);
        TicketOutRecordExample ticketOutRecordExample = new TicketOutRecordExample();
        TicketOutRecordExample.Criteria criteria = ticketOutRecordExample.createCriteria();

        //如果查询参数中有state 的话,于state 进行比对
        String state  = (String) queryParam.get("state");

        // TODO: 2018/4/27 0027 无法添加lang3 依赖
        if (!StringUtils.isEmpty(state)){
            criteria.andStateEqualTo(state);
        }

        ticketOutRecordExample.setOrderByClause("id desc");
        List<TicketOutRecord> ticketOutRecordList = ticketOutRecordMapper.selectByExample(ticketOutRecordExample);

        return new PageInfo<>(ticketOutRecordList);
    }

    /**
     * 批量插入,解决mybaits 参数找不到的问题
     * @param ticketList
     */
    public void batchInsertTicket(@Param("ticketList") List<Ticket> ticketList){
        ticketMapper.batchInsertTicket(ticketList);
    }
}
