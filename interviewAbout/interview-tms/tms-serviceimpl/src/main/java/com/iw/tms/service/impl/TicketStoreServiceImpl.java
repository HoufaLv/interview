package com.iw.tms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iw.tms.entity.StoreAccount;
import com.iw.tms.entity.TicketStore;
import com.iw.tms.entity.TicketStoreExample;
import com.iw.tms.mapper.StoreAccountMapper;
import com.iw.tms.mapper.TicketMapper;
import com.iw.tms.mapper.TicketStoreMapper;
import com.iw.tms.service.TicketStoreService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    /**
     * 根绝页号和动态查询参数去查询
     *
     * @param pageNo
     * @param queryParam
     * @return
     */
    @Override
    public PageInfo<TicketStore> selectAllTicketStoreByPage(Integer pageNo, Map<String, Object> queryParam) {
        //设置pageInfo 相关信息
        PageHelper.startPage(pageNo,15);

        //获取查询参数
        String storeName = (String)queryParam.get("storeName");
        String storeManager = (String) queryParam.get("storeManager");
        String storeTel = (String) queryParam.get("storeTel");

        TicketStoreExample ticketStoreExample = new TicketStoreExample();
        TicketStoreExample.Criteria criteria = ticketStoreExample.createCriteria();

        //动态生成查询条件,如果有电话根据电话查,如果有名字根据名字查,如果有售票点名字根据售票点名字查
        if(StringUtils.isNotEmpty(storeName)) {
            criteria.andStoreNameLike("%"+storeName+"%");
        }
        if(StringUtils.isNotEmpty(storeManager)) {
            criteria.andStoreManagerLike("%"+storeManager+"%");
        }
        if(StringUtils.isNotEmpty(storeTel)) {
            criteria.andStoreTelEqualTo(storeTel);
        }
        ticketStoreExample.setOrderByClause("id desc");

        List<TicketStore> ticketStoreList = ticketStoreMapper.selectByExample(ticketStoreExample);
        return new PageInfo<>(ticketStoreList);
    }
}
