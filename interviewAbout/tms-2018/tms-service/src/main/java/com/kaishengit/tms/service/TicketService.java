package com.kaishengit.tms.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.tms.entity.*;
import com.kaishengit.tms.exception.ServiceException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 年票业务类
 * @author drm
 * @date 2018/4/19
 */
public interface TicketService {
    /**
     * 保存一个入库记录
     * @date 2018/4/21
     * @param [ticket]
     * @return void
     */
    void saveTicketInRecord(TicketInRecord ticketInRecord);

    /**
     * 根据页码查询入库记录
     * @date 2018/4/23
     * @param [pageNo]
     * @return com.github.pagehelper.PageInfo<com.kaishengit.tms.entity.TicketInRecord>
     */
    PageInfo<TicketInRecord> findTicketRecordByPageNo(Integer pageNo);

    /**
     * 根据id删除入库记录
     * @date 2018/4/21
     * @param [id]
     * @return void
     */
    void delTicketInRecordById(Integer id);

    /**
     * 统计各个状态的年票数量
     * @date 2018/4/23
     * @param []
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    Map<String,Long> countTicketByState();

    /**
     * 新增年票下发记录
     * @date 2018/4/23
     * @param [ticketOutRecord]
     * @return void
     */
    void saveTicketOutRecord(TicketOutRecord ticketOutRecord);

    /**
     * 根据当前页码查询下发记录
     * @date 2018/4/23
     * @param [pageNo]
     * @return com.github.pagehelper.PageInfo<com.kaishengit.tms.entity.TicketOutRecord>
     */
    PageInfo<TicketOutRecord> findTicketOutRecordByPageNo(Integer pageNo);


    /**
     * 根据主键删除下发单
     * @date 2018/4/23
     * @param [id]
     * @return void
     */
    void delTicketOutRecordById(Integer id);

    /**
     * 根据当前页号和查询参数查询下发列表
     * @date 2018/4/24
     * @param [pageNO, queryParam]
     * @return com.github.pagehelper.PageInfo<com.kaishengit.tms.entity.TicketOutRecord>
     */
    PageInfo<TicketOutRecord> findTicketOutRecordByPageNoAndQueryParam(Integer pageNO, Map<String, Object> queryParam);

    /**
     * 根据ID对对应的售票单进行支付-财务结算
     * @date 2018/4/24
     * @param [id, payType]
     * @return void
     */
    void payTicketOutRecord(Integer id, String payType);

    /**  
     * 根据年票状态查询年票列表
     * @date 2018/5/9
     * @param [ticketState]  
     * @return java.util.List<com.kaishengit.tms.entity.Ticket>  
     */ 
    List<Ticket> findTicketByState(String ticketState);

    /**  
     * 批量修改年票状态
     * @date 2018/5/9
     * @param [outTimeTicketList]  
     * @return void  
     */ 
    void batchUpdateTicketState(List<Ticket> outTimeTicketList);














    /**
     * 查找所有入库记录
     * @date 2018/4/21
     * @param []
     * @return java.util.List<com.kaishengit.tms.entity.TicketInRecord>
     */
    List<TicketInRecord> findAllTicketInRecord();



    TicketInRecord findTicketInRecordById(Integer id);

    /**
     * 修改入库记录
     * @date 2018/4/21
     * @param [ticketInRecord]
     * @return void
     */
    void updateTicketInRecord(TicketInRecord ticketInRecord);













    /**
     * 根据ID查找对应下发单
     * @date 2018/4/24
     * @param [id]
     * @return com.kaishengit.tms.entity.TicketOutRecord
     */
    TicketOutRecord findTicketOutRecordById(Integer id);



    /**
     * 查询当前售票点库存年票数量和已售出年票数量
     * @date 2018/4/27
     * @param [id]
     * @return java.util.Map<java.lang.String,java.lang.Long>
     */
    Map<String,Long> countTicketByStateAndStoreAccountId(Integer id);

    /**
     *
     * @date 2018/4/29
     * @param customer 销售年票的客户对象, ticketNum 年票票号, ticketStore 当前售票点, price 价格
     * @return void
     * @throws ServiceException 销售失败抛出异常
     */
    void salesTicket(Customer customer, String ticketNum, TicketStore ticketStore, BigDecimal price) throws ServiceException;

    /**
     * 根据年票号码查询年票
     * @date 2018/4/29
     * @param  ticketNum
     * @return com.kaishengit.tms.entity.Ticket
     */
    Ticket findTicketByTicketNum(String ticketNum);
}
