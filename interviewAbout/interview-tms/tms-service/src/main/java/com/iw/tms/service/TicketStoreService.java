package com.iw.tms.service;

import com.github.pagehelper.PageInfo;
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
}
