package com.iw.tms.service;

import com.iw.tms.entity.TicketStore;

public interface TicketStoreService {

    /**
     * 新增售票点账户
     * @param ticketStore
     */
    void insertTicketStore(TicketStore ticketStore);
}
