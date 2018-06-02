package com.iw.tms.service;

import com.github.pagehelper.PageInfo;
import com.iw.tms.entity.StoreAccount;
import com.iw.tms.entity.TicketStore;

import java.util.Map;

public interface TicketStoreService {

    /**
     * 新增售票点账户
     * @param ticketStore
     */
    void insertTicketStore(TicketStore ticketStore);

    /**
     * 根绝页号和动态查询参数去查询
     * @param pageNo
     * @param queryParam
     * @return
     */
    PageInfo<TicketStore> selectAllTicketStoreByPage(Integer pageNo, Map<String, Object> queryParam);

    /**
     * 查找售票点
     * @param id
     * @return
     */
    TicketStore selectByStoreAccountId(Integer id);

    /**
     * 根据售票点id 查询对应的售票账户
     * @param id
     * @return
     */
    StoreAccount selectStoreAccountByTicketStoreId(Integer id);

    /**
     * 根据id查询售票点信息
     * @param id
     * @return
     */
    TicketStore selectTicketStoreById(Integer id);

    /**
     * 更新账户
     * @param ticketStore
     */
    void updateTicketStore(TicketStore ticketStore);
}
