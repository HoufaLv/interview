package com.iw.tms.mapper;

import com.iw.tms.entity.TicketOrder;
import com.iw.tms.entity.TicketOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TicketOrderMapper {
    long countByExample(TicketOrderExample example);

    int deleteByExample(TicketOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TicketOrder record);

    int insertSelective(TicketOrder record);

    List<TicketOrder> selectByExample(TicketOrderExample example);

    TicketOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TicketOrder record, @Param("example") TicketOrderExample example);

    int updateByExample(@Param("record") TicketOrder record, @Param("example") TicketOrderExample example);

    int updateByPrimaryKeySelective(TicketOrder record);

    int updateByPrimaryKey(TicketOrder record);
}