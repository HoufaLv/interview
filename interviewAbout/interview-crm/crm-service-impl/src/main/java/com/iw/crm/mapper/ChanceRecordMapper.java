package com.iw.crm.mapper;

import com.iw.crm.entity.ChanceRecord;
import com.iw.crm.entity.ChanceRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ChanceRecordMapper {
    long countByExample(ChanceRecordExample example);

    int deleteByExample(ChanceRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ChanceRecord record);

    int insertSelective(ChanceRecord record);

    List<ChanceRecord> selectByExample(ChanceRecordExample example);

    ChanceRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ChanceRecord record, @Param("example") ChanceRecordExample example);

    int updateByExample(@Param("record") ChanceRecord record, @Param("example") ChanceRecordExample example);

    int updateByPrimaryKeySelective(ChanceRecord record);

    int updateByPrimaryKey(ChanceRecord record);
}