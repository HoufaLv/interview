package com.ksit.tms.mapper;

import com.ksit.tms.entity.Ticket;
import com.ksit.tms.entity.TicketExample;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TicketMapper {
    long countByExample(TicketExample example);

    int deleteByExample(TicketExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Ticket record);

    int insertSelective(Ticket record);

    List<Ticket> selectByExample(TicketExample example);

    Ticket selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Ticket record, @Param("example") TicketExample example);

    int updateByExample(@Param("record") Ticket record, @Param("example") TicketExample example);

    int updateByPrimaryKeySelective(Ticket record);

    int updateByPrimaryKey(Ticket record);

    /**
     * 批量保存Ticket
     * @param ticketList
     */
    void batchInsertTicket(@Param("ticketList") List<Ticket> ticketList);

    /**
     * 根据年票记录删除对应的年票
     * @param beginTicketNum        开始票号
     * @param endTicketNum          结束票号
     * @param ticketStateInStore    票的状态
     * @return
     */
    List<Ticket> selectListByBenginEndAndState(String beginTicketNum, String endTicketNum, String ticketStateInStore);

    /**
     * 批量删除
     * @param idLists
     */
    void batchDelete(ArrayList<Long> idLists);

    /**
     * 年票下发的时候检测票段是否可用
     * @param beginTicketNum
     * @param endTicketNum
     * @return
     */
    List<Ticket> selectListByBeginAndEnd(String beginTicketNum, String endTicketNum);

    /**
     * 根据状态查询票的数量
     * @return
     */
    Map<String,Long> countTicketByState();

}