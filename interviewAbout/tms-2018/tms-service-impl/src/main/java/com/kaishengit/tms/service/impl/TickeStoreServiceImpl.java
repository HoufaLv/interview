package com.kaishengit.tms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.entity.*;
import com.kaishengit.tms.mapper.StoreAccountMapper;
import com.kaishengit.tms.mapper.StoreLoginLogMapper;
import com.kaishengit.tms.mapper.TicketStoreMapper;
import com.kaishengit.tms.service.TicketStoreService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ticketStore接口实现类
 * @author drm
 * @date 2018/4/25
 */
@Service
public class TickeStoreServiceImpl implements TicketStoreService {

    @Autowired
    private TicketStoreMapper ticketStoreMapper;
    @Autowired
    private StoreAccountMapper storeAccountMapper;
    @Autowired
    private StoreLoginLogMapper storeLoginLogMapper;


    /**
     * 创建新的售票点
     *
     * @param ticketStore
     * @return void
     * @date 2018/4/19
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveNewTicketStore(TicketStore ticketStore) {

        //1.数据库插入ticketStore对象数据，获得返回主键值
        ticketStore.setCreateTime(new Date());
        ticketStoreMapper.insertSelective(ticketStore);
        //2.创建售票点账号对象，并赋值，插入数据库
        StoreAccount storeAccount = new StoreAccount();
        storeAccount.setStoreAccount(ticketStore.getStoreTel());
          //密码默认为手机号后六位经过加密处理
        storeAccount.setStorePassword(DigestUtils.md5Hex(ticketStore.getStoreTel().substring(5)));
        storeAccount.setCreateTime(new Date());
        storeAccount.setStoreState(StoreAccount.ACCOUNT_STATE_NORMAL);
          //外键ticketStoreId为ticketStore的id
        storeAccount.setTicketStoreId(ticketStore.getId());

        storeAccountMapper.insertSelective(storeAccount);
        //3.获得storeAccount自动生成的id,并赋值给ticketStore的storeAccountId,更新数据库
        ticketStore.setStoreAccountId(storeAccount.getId());
        ticketStoreMapper.updateByPrimaryKeySelective(ticketStore);
    }

    /**
     * 根据当前页面和查询参数查询销售点
     *
     * @param pageNo
     * @param queryParam
     * @return com.github.pagehelper.PageInfo<com.kaishengit.tms.entity.TicketStore>
     * @date 2018/4/20
     */
    @Override
    public PageInfo<TicketStore> findAllTicketStoreByPage(Integer pageNo, Map<String, Object> queryParam) {

        PageHelper.startPage(pageNo,15);

        String storeName = (String) queryParam.get("storeName");
        String storeManager = (String) queryParam.get("storeManager");
        String storeTel = (String) queryParam.get("storeTel");

        TicketStoreExample ticketStoreExample = new TicketStoreExample();
        TicketStoreExample.Criteria criteria = ticketStoreExample.createCriteria();

        if (StringUtils.isEmpty(storeName)){
            criteria.andStoreNameLike("%"+storeName+"%");
        }
        if (StringUtils.isEmpty(storeManager)){
            criteria.andStoreNameLike("%"+storeManager+"%");
        }
        if (StringUtils.isEmpty(storeTel)){
            criteria.andStoreNameLike("%"+storeTel+"%");
        }

        ticketStoreExample.setOrderByClause("id desc");

        List<TicketStore> ticketStoreList = ticketStoreMapper.selectByExample(ticketStoreExample);

        return new PageInfo<>(ticketStoreList);
    }

    /**
     * 根据id查找对应售票点
     *
     * @param id
     * @return com.kaishengit.tms.entity.TicketStore
     * @date 2018/4/20
     */
    @Override
    public TicketStore findTicketStoreById(Integer id) {
        return ticketStoreMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据主键查找售票点账号对象
     *
     * @param id
     * @return com.kaishengit.tms.entity.StoreAccount
     * @date 2018/4/20
     */
    @Override
    public StoreAccount findStoreAccountById(Integer id) {
        return storeAccountMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改售票点对象
     *
     * @param ticketStore
     * @return void
     * @date 2018/4/20
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateTicketStore(TicketStore ticketStore) {

        ticketStore.setUpdateTime(new Date());

        //获得storeAccount对象 注意前端
        StoreAccount storeAccount = storeAccountMapper.selectByPrimaryKey(ticketStore.getStoreAccountId());
        //判断是否修改了手机号，如果修改了则需要同步
        if (!ticketStore.getStoreTel().equals(storeAccount.getStoreAccount())){
            storeAccount.setStoreAccount(ticketStore.getStoreTel());
            //重新设置密码，    这一步有待商榷
            storeAccount.setStorePassword(DigestUtils.md5Hex(ticketStore.getStoreTel().substring(5)));
            storeAccount.setUpdateTime(new Date());

            storeAccountMapper.updateByPrimaryKeySelective(storeAccount);
        }

        ticketStoreMapper.updateByPrimaryKeySelective(ticketStore);

    }

    /**
     * 根据id删除售票点
     *
     * @param id
     * @return void
     * @date 2018/4/20
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delTicketStoreById(Integer id) {
        //真删除，三表连删
        TicketStore ticketStore = ticketStoreMapper.selectByPrimaryKey(id);
        StoreAccount storeAccount = storeAccountMapper.selectByPrimaryKey(ticketStore.getStoreAccountId());
        StoreLoginLogExample storeLoginLogExample = new StoreLoginLogExample();
        storeLoginLogExample.createCriteria().andStoreAccountIdEqualTo(storeAccount.getId());

        //开始删除
        storeLoginLogMapper.deleteByExample(storeLoginLogExample);
        storeAccountMapper.deleteByPrimaryKey(storeAccount.getId());
        ticketStoreMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查找所有售票点
     * @return java.util.List<com.kaishengit.tms.entity.TicketStore>
     * @date 2018/4/23
     */
    @Override
    public List<TicketStore> findAllTicketStore() {
        TicketStoreExample ticketStoreExample = new TicketStoreExample();
        ticketStoreExample.setOrderByClause("id desc");
        return ticketStoreMapper.selectByExample(ticketStoreExample);
    }

    /**
     * 根据账号（手机号码）查找售票点账号对象
     *
     * @param userMobile 账号（手机号）
     * @return com.kaishengit.tms.entity.StoreAccount
     * @date 2018/4/29
     */
    @Override
    public StoreAccount findStoreAccountByName(String userMobile) {
        StoreAccountExample storeAccountExample = new StoreAccountExample();
        storeAccountExample.createCriteria().andStoreAccountEqualTo(userMobile);

        List<StoreAccount> storeAccountList = storeAccountMapper.selectByExample(storeAccountExample);
        if (storeAccountList!=null && !storeAccountList.isEmpty()){
            return storeAccountList.get(0);
        }
        return null;
    }

    /**
     * 保存售票点登录日志
     *
     * @param storeLoginLog 售票点登录日志
     * @return void
     * @date 2018/4/29
     */
    @Override
    public void saveStoreAccountLoginLog(StoreLoginLog storeLoginLog) {
        storeLoginLogMapper.insertSelective(storeLoginLog);
    }
}
