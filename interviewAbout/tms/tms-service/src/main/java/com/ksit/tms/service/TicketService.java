package com.ksit.tms.service;

import com.github.pagehelper.PageInfo;
import com.ksit.tms.entity.TicketInRecord;
import com.ksit.tms.entity.TicketOutRecord;

import java.util.Map;

public interface TicketService {
    /**
     * 新增年票记录
     * @param ticketInRecord
     */
    void insertTicketInRecord(TicketInRecord ticketInRecord);

    /**
     * 查询分页数据
     * @param pageNo
     * @return
     */
    PageInfo<TicketInRecord> selectTicketRecordByPageNo(Integer pageNo);

    /**
     * 删除年票入库记录
     * @param id
     */
    void deleteTicketInRecord(Integer id);

    /**
     * 年票下发记录分页处理
     * @param pageNo
     * @return
     */
    PageInfo<TicketOutRecord> selectTicketOutRecordByPageNo(Integer pageNo);

    /**
     * 年票下发记录
     * @param ticketOutRecord
     */
    void saveTicketOutRecord(TicketOutRecord ticketOutRecord);

    /**
     * 删除年票下发记录
     * @param id
     */
    void deleteTicketOutRecord(Integer id);

    /**
     * 将票的状态统计一下,发送到前端
     * @return
     */
    Map<String,Long> countTicketByState();


    /**
     * 财务相关
     * 根据状态和页码将年票下发记录查出来
     * @param queryParam
     * @param pageNo
     * @return
     */
    PageInfo<TicketOutRecord> selectTicketOutRecordByParam(Map<String, Object> queryParam, Integer pageNo);

    /**
     * 根据id 查询年票下发记录
     * @param id
     * @return
     */
    TicketOutRecord selectticketoutrecordbyId(Integer id);

    /**
     * 根据id 对下发的订单支付
     * @param id
     * @param payType
     */
    void payTicketOutRecord(Integer id, String payType);
}
