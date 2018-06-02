package com.ksit.tms.mapper;

import com.ksit.tms.entity.StoreLoginLog;
import com.ksit.tms.entity.StoreLoginLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StoreLoginLogMapper {
    long countByExample(StoreLoginLogExample example);

    int deleteByExample(StoreLoginLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StoreLoginLog record);

    int insertSelective(StoreLoginLog record);

    List<StoreLoginLog> selectByExample(StoreLoginLogExample example);

    StoreLoginLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StoreLoginLog record, @Param("example") StoreLoginLogExample example);

    int updateByExample(@Param("record") StoreLoginLog record, @Param("example") StoreLoginLogExample example);

    int updateByPrimaryKeySelective(StoreLoginLog record);

    int updateByPrimaryKey(StoreLoginLog record);
}