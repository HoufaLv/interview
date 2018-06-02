package com.kaishengit.tms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.entity.*;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.mapper.*;
import com.kaishengit.tms.service.TicketService;
import com.kaishengit.tms.util.SnowFlake;
import com.kaishengit.tms.util.shiro.ShiroUtil;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TicketServiceImpl implements TicketService {

    public static final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

    @Autowired
    private ShiroUtil shiroUtil;

    @Autowired
    private TicketInRecordMapper ticketInRecordMapper;
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private TicketOutRecordMapper ticketOutRecordMapper;
    @Autowired
    private TicketStoreMapper ticketStoreMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private TicketOrderMapper ticketOrderMapper;
    @Value("${snowFlake.dataCenterId}")
    private Integer snowFlakeDataCenter;
    @Value("${snowFlake.machineId}")
    private Integer snowFlakeMachineId;


    /**
     * 保存一个入库记录
     * @param ticketInRecord
     * @return void
     * @date 2018/4/21
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveTicketInRecord(TicketInRecord ticketInRecord) {


        //获得 截止票号 和 起始票号
        BigInteger start = new BigInteger(ticketInRecord.getBeginTicketNum());
        BigInteger end = new BigInteger(ticketInRecord.getEndTicketNum());
        //判断起始票号是否小于截止票号
        if (start.compareTo(end)>=0){
            throw new ServiceException("起始票号必须小于截止票号");
        }
        //判断当前的入库票号的范围是否和之前的入库的范围重合，如果重合则不能添加
        List<TicketInRecord> ticketInRecordList = ticketInRecordMapper.selectByExample(new TicketInRecordExample());
        for (TicketInRecord record:ticketInRecordList){
            BigInteger recordStart = new BigInteger(record.getBeginTicketNum());
            BigInteger recordEnd = new BigInteger(record.getEndTicketNum());
            //如果起始票号在以往入库票段开始和结束之间  或者 结束票号在以往入库票段开始和结束之间 则代表重复
            boolean flag = (recordStart.compareTo(start)<=0 && recordEnd.compareTo(start)>=0) || (recordStart.compareTo(end)<=0 && recordEnd.compareTo(end)>= 0);
            if (flag){
                throw new ServiceException("票号区间重复，添加失败");
            }
        }

        //设置入库时间
        ticketInRecord.setCreateTime(new Date());
        //设置总数量 = 截止票号 - 起始票号 + 1
        BigInteger totalNum = end.subtract(start).add(new BigInteger(String.valueOf(1)));
        ticketInRecord.setTotalNum(totalNum.intValue());

        //获取当前登录对象
        Account account = shiroUtil.getCurrentAccount();
        ticketInRecord.setAccountId(account.getId());
        ticketInRecord.setAccountName(account.getAccountName());

        //设置入库的内容
        ticketInRecord.setContent(ticketInRecord.getBeginTicketNum()+"-"+ticketInRecord.getEndTicketNum());

        ticketInRecordMapper.insertSelective(ticketInRecord);

        logger.info("新增年票入库:{},入库人:{}",ticketInRecord,account);

        //添加totalNum条年票记录
        List<Ticket> ticketList = new ArrayList<>();
        for (int i=0;i<totalNum.intValue();i++){
            Ticket ticket = new Ticket();
            ticket.setCreateTime(new Date());
            ticket.setTicketInTime(new Date());
            ticket.setTicketNum(start.add(new BigInteger(String.valueOf(i))).toString());
            ticket.setTicketState(Ticket.TICKET_STATE_IN_STORE);
            ticket.setTicketInRecordId(ticketInRecord.getId());

            ticketList.add(ticket);
        }

        //批量保存年票记录
        ticketMapper.batchInsert(ticketList);
    }

    /**
     * 查找所有入库记录
     *
     * @return java.util.List<com.kaishengit.tms.entity.TicketInRecord>
     * @date 2018/4/21
     */
    @Override
    public List<TicketInRecord> findAllTicketInRecord() {
        TicketInRecordExample ticketInRecordExample = new TicketInRecordExample();
        return ticketInRecordMapper.selectByExample(ticketInRecordExample);
    }

    /**
     * 根据id删除入库记录
     * @param id
     * @return void
     * @date 2018/4/21
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delTicketInRecordById(Integer id) {
        //查询ticketInRecord对象
        TicketInRecord ticketInRecord = ticketInRecordMapper.selectByPrimaryKey(id);
        //在不知道前端是不是ajax请求的情况下,防止用户篡改url中id的值,修改和删除都要增加
        if (ticketInRecord==null){
            throw new ServiceException("参数异常,删除失败");
        }

        //根据入库记录删除年票ticket对象
            //1.根据入库记录id 和 票号状态为正常 查询年票集合
        /*TicketExample ticketExample = new TicketExample();
        ticketExample.createCriteria().andTicketInRecordIdEqualTo(ticketInRecord.getId()).andTicketStateEqualTo(Ticket.TICKET_STATE_IN_STORE);
        List<Ticket> ticketList = ticketMapper.selectByExample(ticketExample);*/

        List<Ticket> ticketList = ticketMapper.selectByStartAndEndNum(ticketInRecord.getBeginTicketNum(),ticketInRecord.getEndTicketNum(),Ticket.TICKET_STATE_IN_STORE);

            //2.判断ticket集合和ticketInRocord对象的数量是否相同，如果相同则票的状态未变
        if (!ticketInRecord.getTotalNum().equals(ticketList.size())){
            throw new ServiceException("该批次年票状态发生改变，不能删除");
        }

        //根据id删除入库记录ticketInRecord 和对应年票
        ticketMapper.deleteByStartAndEndNum(ticketInRecord.getBeginTicketNum(),ticketInRecord.getEndTicketNum());

        ticketInRecordMapper.deleteByPrimaryKey(id);

    }

    @Override
    public TicketInRecord findTicketInRecordById(Integer id) {
        return ticketInRecordMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改入库记录
     * @param ticketInRecord
     * @return void
     * @date 2018/4/21
     */
    @Override
    public void updateTicketInRecord(TicketInRecord ticketInRecord) {

        //设置入库修改时间
        ticketInRecord.setUpdateTime(new Date());
        //设置总数量 = 截止票号 - 起始票号 + 1
        BigInteger start = new BigInteger(ticketInRecord.getBeginTicketNum());
        BigInteger end = new BigInteger(ticketInRecord.getEndTicketNum());

        BigInteger totalNum = end.subtract(start).add(new BigInteger(String.valueOf(1)));
        ticketInRecord.setTotalNum(totalNum.intValue());

        //获取当前登录对象
        Account account = shiroUtil.getCurrentAccount();
        ticketInRecord.setAccountId(account.getId());
        ticketInRecord.setAccountName(account.getAccountName());

        //设置入库的内容
        ticketInRecord.setContent(ticketInRecord.getBeginTicketNum()+"-"+ticketInRecord.getEndTicketNum());

        ticketInRecordMapper.updateByPrimaryKeySelective(ticketInRecord);

        logger.info("修改年票入库:{},入库人:{}",ticketInRecord,account);

        //删除原年票入库记录对应的年票记录
        TicketExample ticketExample = new TicketExample();
        ticketExample.createCriteria().andTicketInRecordIdEqualTo(ticketInRecord.getId());
        //新增新年票入库记录对应的年票记录
        List<Ticket> ticketList = new ArrayList<>();
        for (int i=0;i<totalNum.intValue();i++){
            Ticket ticket = new Ticket();
            ticket.setCreateTime(new Date());
            ticket.setTicketInTime(new Date());
            ticket.setTicketNum(start.add(new BigInteger(String.valueOf(i))).toString());
            ticket.setTicketState(Ticket.TICKET_STATE_IN_STORE);
            ticket.setTicketInRecordId(ticketInRecord.getId());

            ticketList.add(ticket);
        }

        //批量保存新增年票记录
        ticketMapper.batchInsert(ticketList);

    }

    /**
     * 根据页码查询入库记录
     *
     * @param pageNo
     * @return com.github.pagehelper.PageInfo<com.kaishengit.tms.entity.TicketInRecord>
     * @date 2018/4/23
     */
    @Override
    public PageInfo<TicketInRecord> findTicketRecordByPageNo(Integer pageNo) {
        PageHelper.startPage(pageNo,15);

        TicketInRecordExample ticketInRecordExample = new TicketInRecordExample();
        ticketInRecordExample.setOrderByClause("id desc");

        List<TicketInRecord> ticketInRecordList = ticketInRecordMapper.selectByExample(ticketInRecordExample);
        return new PageInfo<>(ticketInRecordList);
    }

    /*----------------------------------年票统计----------------------------------------*/

    /**
     * 根据年票状态统计年票数量
     *
     * @return java.util.Map<java.lang.String , java.lang.Object>
     * @date 2018/4/23
     */
    @Override
    public Map<String, Long> countTicketByState() {
        return ticketMapper.countTicketByState();
    }


    /*---------------------------------年票下发-------------------------------------*/

    /**
     * 根据页码查询下发记录
     *
     * @param pageNo
     * @return com.github.pagehelper.PageInfo<com.kaishengit.tms.entity.TicketOutRecord>
     * @date 2018/4/23
     */
    @Override
    public PageInfo<TicketOutRecord> findTicketOutRecordByPageNo(Integer pageNo) {
        PageHelper.startPage(pageNo,15);

        TicketOutRecordExample ticketOutRecordExample = new TicketOutRecordExample();
        ticketOutRecordExample.setOrderByClause("id desc");

        List<TicketOutRecord> ticketOutRecordList = ticketOutRecordMapper.selectByExample(ticketOutRecordExample);
        return new PageInfo<>(ticketOutRecordList);
    }

    /**
     * 新增下发记录
     * @param ticketOutRecord
     * @return void
     * @date 2018/4/23
     */
    @Override
    public void saveTicketOutRecord(TicketOutRecord ticketOutRecord) {

        //判断当前票段内是否有非[已入库]状态的表，如果有则不能不发
        List<Ticket> ticketList = ticketMapper.selectOutByStartAndEndNum(ticketOutRecord.getBeginTicketNum(),ticketOutRecord.getEndTicketNum());

        for (Ticket ticket:ticketList){
            if (!Ticket.TICKET_STATE_IN_STORE.equals(ticket.getTicketState())){
                throw new ServiceException("该票段内已有下发的票，请重新选择");
            }
        }

        //判断所输入票段是否真实存在
        //获得 截止票号 和 起始票号 以及截止票号和起始票号之差
        BigInteger start = new BigInteger(ticketOutRecord.getBeginTicketNum());
        BigInteger end = new BigInteger(ticketOutRecord.getEndTicketNum());
        int difference = end.subtract(start).intValue();
        //起止票号之差加1，获得输入票数，与数据库中查到的票数做对比，如果相等，则票段真实存在
        difference = difference+1;
        if (difference!=ticketList.size()){
            throw new ServiceException("该票段格式不对，请重新选择");
        }


        //获取当前下发的售票点对象，并赋值售票点名称
        TicketStore ticketStore = ticketStoreMapper.selectByPrimaryKey(ticketOutRecord.getStoreAccountId());
        ticketOutRecord.setStoreAccountName(ticketStore.getStoreName());
        //选择总数量
        int totalSize = ticketList.size();
        //总价格
        BigDecimal totalPrice = ticketOutRecord.getPrice().multiply(new BigDecimal(totalSize));
        //当前登录对象
        Account account = shiroUtil.getCurrentAccount();

        ticketOutRecord.setCreateTime(new Date());
        ticketOutRecord.setContent(ticketOutRecord.getBeginTicketNum() +"-"+ticketOutRecord.getEndTicketNum());
        ticketOutRecord.setOutAccountId(account.getId());
        ticketOutRecord.setOutAccountName(account.getAccountName());
        ticketOutRecord.setTotalNum(totalSize);
        ticketOutRecord.setState(TicketOutRecord.STATE_NO_PAY);
        ticketOutRecord.setTotalPrice(totalPrice);

        ticketOutRecordMapper.insertSelective(ticketOutRecord);
        logger.info("新增年票下发记录：{}",ticketOutRecord);



    }

    /**
     * 删除下发记录
     * @param id
     * @return void
     * @date 2018/4/23
     */
    @Override
    public void delTicketOutRecordById(Integer id) {
        TicketOutRecord ticketOutRecord = ticketOutRecordMapper.selectByPrimaryKey(id);
        if (ticketOutRecord == null){
            throw new ServiceException("参数异常,删除失败");
        }
        if (TicketOutRecord.STATE_NO_PAY.equals(ticketOutRecord.getState())){
            ticketOutRecordMapper.deleteByPrimaryKey(id);
        } else {
            throw new ServiceException("支付完成，无法删除");
        }

    }

    /**
     * 根据当前页号和查询参数查询下发列表
     * @param pageNO
     * @param queryParam
     * @return com.github.pagehelper.PageInfo<com.kaishengit.tms.entity.TicketOutRecord>
     * @date 2018/4/24
     */
    @Override
    public PageInfo<TicketOutRecord> findTicketOutRecordByPageNoAndQueryParam(Integer pageNO, Map<String, Object> queryParam) {
        PageHelper.startPage(pageNO,15);

        TicketOutRecordExample ticketOutRecordExample = new TicketOutRecordExample();
        String state = (String) queryParam.get("state");
        if (StringUtils.isNotEmpty(state)){
            ticketOutRecordExample.createCriteria().andStateEqualTo(state);
        }
        ticketOutRecordExample.setOrderByClause("id desc");

        List<TicketOutRecord> ticketOutRecordList = ticketOutRecordMapper.selectByExample(ticketOutRecordExample);

        return new PageInfo<>(ticketOutRecordList);
    }

    /**
     * 根据ID查找对应下发单
     * @param id
     * @return com.kaishengit.tms.entity.TicketOutRecord
     * @date 2018/4/24
     */
    @Override
    public TicketOutRecord findTicketOutRecordById(Integer id) {
        return ticketOutRecordMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据ID对对应的售票单进行支付-财务结算
     *
     * @param id
     * @param payType
     * @return void
     * @date 2018/4/24
     */
    @Override
    public void payTicketOutRecord(Integer id, String payType) {
        TicketOutRecord ticketOutRecord = ticketOutRecordMapper.selectByPrimaryKey(id);
        if (ticketOutRecord!=null && TicketOutRecord.STATE_NO_PAY.equals(ticketOutRecord.getState())){
            ticketOutRecord.setPayType(payType);

            Account account = shiroUtil.getCurrentAccount();
            ticketOutRecord.setFinanceAccountId(account.getId());
            ticketOutRecord.setFinanceAccountName(account.getAccountName());
            ticketOutRecord.setState(TicketOutRecord.STATE_PAY);

            //修改下发记录
            ticketOutRecordMapper.updateByPrimaryKeySelective(ticketOutRecord);

            //将对应的年票状态修改为[已下发]  这个方法可以优化，批量修改，增加数据库性能
            List<Ticket> ticketList = ticketMapper.selectOutByStartAndEndNum(ticketOutRecord.getBeginTicketNum(),ticketOutRecord.getEndTicketNum());
            for (Ticket ticket:ticketList){
                ticket.setTicketState(Ticket.TICKET_STATE_OUT_STORE);
                ticket.setStoreAccountId(ticketOutRecord.getStoreAccountId());
                ticket.setTicketOutTime(DateTime.now().toString("YYYY-MM-dd"));
                ticket.setUpdateTime(new Date());

                ticketMapper.updateByPrimaryKeySelective(ticket);
            }
        }
    }

    /**
     * 根据年票状态查询年票列表
     *
     * @param ticketState
     * @return java.util.List<com.kaishengit.tms.entity.Ticket>
     * @date 2018/5/9
     */
    @Override
    public List<Ticket> findTicketByState(String ticketState) {
        TicketExample ticketExample = new TicketExample();
        ticketExample.createCriteria().andTicketStateEqualTo(ticketState);
        return ticketMapper.selectByExample(ticketExample);
    }

    /**
     * 批量修改年票状态
     *
     * @param outTimeTicketList
     * @return void
     * @date 2018/5/9
     */
    @Override
    public void batchUpdateTicketState(List<Ticket> outTimeTicketList) {
        ticketMapper.batchUpdateState(outTimeTicketList,Ticket.TICKET_STATE_OUT_DATE);
    }

    /**
     * 查询当前售票点库存年票数量和已售出年票数量
     *
     * @param id
     * @return java.util.Map<java.lang.String , java.lang.Long>
     * @date 2018/4/27
     */
    @Override
    public Map<String, Long> countTicketByStateAndStoreAccountId(Integer id) {
        return null;
    }

    /**
     * 销售年票
     * @param customer    销售年票的客户对象, ticketNum 年票票号, ticketStore 当前售票点, price 价格
     * @param ticketNum
     * @param ticketStore
     * @param price
     * @return void
     * @throws ServiceException 销售失败抛出异常
     * @date 2018/4/29
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void salesTicket(Customer customer, String ticketNum, TicketStore ticketStore, BigDecimal price) throws ServiceException {

        //1.根据年票票号查找年票，查看年票是否属于该售票点并看年票的状态是否属于已下发
        TicketExample ticketExample = new TicketExample();
        ticketExample.createCriteria().andTicketNumEqualTo(ticketNum);

        List<Ticket> ticketList = ticketMapper.selectByExample(ticketExample);
        if (ticketList!=null && !ticketList.isEmpty()){
            Ticket ticket = ticketList.get(0);
            if (Ticket.TICKET_STATE_OUT_STORE.equals(ticket.getTicketState())){
                if (ticket.getStoreAccountId().equals(ticketStore.getId())){

                    //判断该用户是否办理过你年票
                    CustomerExample customerExample = new CustomerExample();
                    customerExample.createCriteria().andCustomerIdCardEqualTo(customer.getCustomerIdCard());
                    List<Customer> customerList = customerMapper.selectByExample(customerExample);
                    if (customerList!=null && !customerList.isEmpty()){
                        Customer dbCustomer = customerList.get(0);
                        //查询当前用户绑定的年票
                        Ticket customerTicket = ticketMapper.selectByPrimaryKey(dbCustomer.getTicketId());
                        if (customerTicket!=null){
                            if (Ticket.TICKET_STATE_SALE.equals(customerTicket.getTicketState())){
                                throw new ServiceException("该用户已购买过年票，不能再次购买");
                            }
                        } else {
                            //用户存在，但未绑定年票
                            customer = dbCustomer;
                        }
                    }else {
                        //2.该用户未办理过年票，保存客户
                        customer.setTicketId(ticket.getId());
                        customer.setCreateTime(new Date());
                        customerMapper.insertSelective(customer);
                    }

                    //3.将年票状态修改为[已销售]
                    ticket.setTicketState(Ticket.TICKET_STATE_SALE);
                        //设置年票有效期
                    ticket.setTicketValidityStart(new Date());

                    DateTime endDate = DateTime.now().plusYears(1);
                    ticket.setTicketValidityEnd(endDate.toDate());
                    //绑定销售用户
                    ticket.setCustomerId(customer.getId());
                    //修该年票对象
                    ticketMapper.updateByPrimaryKeySelective(ticket);


                    //4.创建销售订单
                    TicketOrder ticketOrder = new TicketOrder();
                    ticketOrder.setCreateTime(new Date());
                    ticketOrder.setCustomerId(customer.getId());
                    ticketOrder.setStoreAccountId(ticketStore.getId());
                    ticketOrder.setTicketId(ticket.getId());
                    ticketOrder.setTicketOrderPrice(price);
                    //流水号
                    SnowFlake snowFlake = new SnowFlake(snowFlakeDataCenter,snowFlakeMachineId);
                    ticketOrder.setTicketOrderNum(String.valueOf(snowFlake.nextId()));
                    ticketOrder.setTicketOrderType(TicketOrder.ORDER_TYPE_NEW);

                    ticketOrderMapper.insertSelective(ticketOrder);
                } else {
                    throw new ServiceException("该年票不属于当前售票点，请核查");
                }
            }else{
                throw new ServiceException("该年票状态异常，请核查");
            }
        } else {
            throw new ServiceException("该年票不存在，请查找票号");
        }
    }

    /**
     * 根据年票号码查询年票
     *
     * @param ticketNum
     * @return com.kaishengit.tms.entity.Ticket
     * @date 2018/4/29
     */
    @Override
    public Ticket findTicketByTicketNum(String ticketNum) {
        TicketExample ticketExample = new TicketExample();
        ticketExample.createCriteria().andTicketNumEqualTo(ticketNum);

        List<Ticket> ticketList = ticketMapper.selectByExample(ticketExample);



        return (ticketList == null || ticketList.isEmpty())?null:ticketList.get(0);
    }
}
