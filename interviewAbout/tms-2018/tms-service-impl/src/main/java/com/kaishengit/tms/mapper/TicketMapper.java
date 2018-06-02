package com.kaishengit.tms.mapper;

import com.kaishengit.tms.entity.Ticket;
import com.kaishengit.tms.entity.TicketExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TicketMapper {
    long countByExample(TicketExample example);

    int deleteByExample(TicketExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Ticket record);

    int insertSelective(Ticket record);

    List<Ticket> selectByExample(TicketExample example);

    Ticket selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Ticket record, @Param("example") TicketExample example);

    int updateByExample(@Param("record") Ticket record, @Param("example") TicketExample example);

    int updateByPrimaryKeySelective(Ticket record);

    int updateByPrimaryKey(Ticket record);
	
	 /*-------------以下方法为自定义-------------------*/

    /**
     * 批量插入年票
     * @date 2018/4/21
     * @param [ticketList]
     * @return void
     */
    void batchInsert(@Param("ticketList") List<Ticket> ticketList);

    /**
     * 根据年票状态统计年票数量
     * @date 2018/4/23
     * @param []
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    Map<String,Long> countTicketByState();

    List<Ticket> selectByStartAndEndNum(String beginTicketNum, String endTicketNum, String ticketStateInStore);

    /**
     * 根据起止票号批量删除
     * @date 2018/4/23
     * @param [beginTicketNum, endTicketNum, ticketStateInStore]
     * @return void
     */
    void deleteByStartAndEndNum(String beginTicketNum, String endTicketNum);

    /**
     * 根据起止票号查找
     * @date 2018/4/23
     * @param [beginTicketNum, endTicketNum]
     * @return java.util.List<com.kaishengit.tms.entity.Ticket>
     */
    List<Ticket> selectOutByStartAndEndNum(String beginTicketNum, String endTicketNum);

    /**  
     * 批量修改年票状态
     * @date 2018/5/9
     * @param [outTimeTicketList, state]  
     * @return void  
     */ 
    void batchUpdateState(@Param("ticketList") List<Ticket> outTimeTicketList,@Param("state") String state);

    /**
     * 批量删除年票
     * @date 2018/5/9
     * @param [idList]
     * @return void
     */
    void batchDeleteById(@Param("idList") List<Long> idList);


}