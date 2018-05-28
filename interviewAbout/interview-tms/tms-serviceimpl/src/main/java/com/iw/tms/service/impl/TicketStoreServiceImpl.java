package com.iw.tms.service.impl;

import com.iw.tms.entity.StoreAccount;
import com.iw.tms.entity.TicketStore;
import com.iw.tms.mapper.StoreAccountMapper;
import com.iw.tms.mapper.TicketMapper;
import com.iw.tms.mapper.TicketStoreMapper;
import com.iw.tms.service.TicketStoreService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class TicketStoreServiceImpl implements TicketStoreService {

    @Autowired
    private TicketStoreMapper ticketStoreMapper;
    @Autowired
    private StoreAccountMapper storeAccountMapper;

    /**
     * 新增售票点账户
     *
     * @param ticketStore
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void insertTicketStore(TicketStore ticketStore) {
        ticketStore.setStoreRegisterTime(new Date());
        ticketStoreMapper.insertSelective(ticketStore);

        //创建售票点账号
        StoreAccount storeAccount = new StoreAccount();
        storeAccount.setStoreAccount(ticketStore.getStoreTel());
        //默认密码为手机号码后六位
        storeAccount.setStorePassword(DigestUtils.md5Hex("000000"));
        storeAccount.setCreateTime(new Date());
        storeAccount.setTicketStoreId(ticketStore.getId());
        storeAccount.setStoreState(StoreAccount.ACCOUNT_STATE_NORMAL);

        storeAccountMapper.insertSelective(storeAccount);

        //更新关联的账号ID
        ticketStore.setStoreAccountId(storeAccount.getId());
        ticketStoreMapper.updateByPrimaryKeySelective(ticketStore);
    }
}
