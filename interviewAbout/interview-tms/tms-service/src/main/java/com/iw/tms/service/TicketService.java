package com.iw.tms.service;

import com.iw.tms.entity.TicketInRecord;

public interface TicketService {
    /**
     * 新增年票入库
     * @param ticketInRecord
     */
    void saveTicketInRecord(TicketInRecord ticketInRecord);
}
