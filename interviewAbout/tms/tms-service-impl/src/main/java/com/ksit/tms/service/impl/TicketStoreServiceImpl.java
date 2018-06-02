package com.ksit.tms.service.impl;

import com.ksit.tms.entity.StoreAccount;
import com.ksit.tms.entity.TicketStore;
import com.ksit.tms.entity.TicketStoreExample;
import com.ksit.tms.mapper.StoreAccountMapper;
import com.ksit.tms.mapper.TicketStoreMapper;
import com.ksit.tms.service.TicketStoreService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Lvhoufa
 */
@Service
public class TicketStoreServiceImpl implements TicketStoreService {

    @Autowired
    private TicketStoreMapper ticketStoreMapper;

    @Autowired
    private StoreAccountMapper storeAccountMapper;

    /**
     * 新增销售点信息
     * 对多张表的操作要使用事务
     *
     * @param ticketStore
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void insertTicketStore(TicketStore ticketStore) {

        //1.设置新增时间
        ticketStore.setStoreRegisterTime(new Date());

        //2.插入数据
        ticketStoreMapper.insert(ticketStore);

        //3.创建对应账户
        StoreAccount storeAccount = new StoreAccount();
        //销售账号为手机号
        storeAccount.setStoreAccount(ticketStore.getStoreTel());
        //密码为手机号后六位,MD5加密
        storeAccount.setStorePassword(DigestUtils.md5Hex(ticketStore.getStoreTel().substring(5)));
        storeAccount.setCreateTime(new Date());
        storeAccount.setUpdateTime(new Date().toString());
        storeAccount.setStoreState(StoreAccount.ACCOUNT_STATE_NORMAL);
        //4.添加对应关系,需要在mapper 中获取自动增长的主键
        storeAccount.setTicketStoreId(ticketStore.getId());

        storeAccountMapper.insertSelective(storeAccount);

        //5.然后再去更新tickStore 中的 ticked account id
        ticketStore.setStoreAccountId(storeAccount.getId());
        ticketStoreMapper.updateByPrimaryKeySelective(ticketStore);


    }

    /**
     * 查询所有售票点信息
     *
     * @return
     */
    @Override
    public List<TicketStore> selectAllTicketStore() {
        return ticketStoreMapper.selectByExample(new TicketStoreExample());
    }

    /**
     * 根据id 查询售票点信息
     *
     * @param id
     * @return
     */
    @Override
    public TicketStore selectTIcketStoreById(Integer id) {
        return ticketStoreMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新售票点信息
     */
    @Override
    @Transactional()
    public void updateTicketStore(TicketStore ticketStore) {
        //1.处理关联关系
        //2.更新售票点电话的之后,直接把售票点账户给改了,密码为电话号码后六位
        //根据修改的销售点信息查出销售账户的信息
        StoreAccount storeAccount = storeAccountMapper.selectByPrimaryKey(ticketStore.getStoreAccountId());

        //判断电话号码是否修改了,如果修改了,就将对应的销售账户的账号给改了,密码为电话号码后六位
        //如果销售点电话和销售账户的账号不一致,说明修改了
        if (!storeAccount.getStoreAccount().equals(ticketStore.getStoreTel())){
            //重新设置账户
            storeAccount.setStoreAccount(ticketStore.getStoreTel());
            //重新设置密码
            storeAccount.setStorePassword(DigestUtils.md5Hex(ticketStore.getStoreTel().substring(5)));
            storeAccount.setUpdateTime(new Date().toString());

            storeAccountMapper.updateByPrimaryKeySelective(storeAccount);
        }

        //如果没有修改
        ticketStoreMapper.updateByPrimaryKeySelective(ticketStore);

    }
}
