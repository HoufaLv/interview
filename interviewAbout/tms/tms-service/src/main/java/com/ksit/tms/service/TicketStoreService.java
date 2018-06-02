package com.ksit.tms.service;

import com.ksit.tms.entity.TicketStore;

import java.util.List;

/**
 * @author Lvhoufa
 */
public interface TicketStoreService {

    /**
     * 新增销售点信息
     * @param ticketStore
     */
    void insertTicketStore(TicketStore ticketStore);

    /**
     * 查询所有售票点信息
     * @return
     */
    List<TicketStore> selectAllTicketStore();

    /**
     * 根据id 查询售票点信息
     * @param id
     * @return
     */
    TicketStore selectTIcketStoreById(Integer id);

    /**
     * 更新售票点信息
     */
    void updateTicketStore(TicketStore ticketStore);
}
