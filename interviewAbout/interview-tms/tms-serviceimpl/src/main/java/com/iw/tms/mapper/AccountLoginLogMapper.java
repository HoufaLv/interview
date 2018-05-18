package com.iw.tms.mapper;

import com.iw.tms.entity.AccountLoginLog;
import com.iw.tms.entity.AccountLoginLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountLoginLogMapper {
    long countByExample(AccountLoginLogExample example);

    int deleteByExample(AccountLoginLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AccountLoginLog record);

    int insertSelective(AccountLoginLog record);

    List<AccountLoginLog> selectByExample(AccountLoginLogExample example);

    AccountLoginLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AccountLoginLog record, @Param("example") AccountLoginLogExample example);

    int updateByExample(@Param("record") AccountLoginLog record, @Param("example") AccountLoginLogExample example);

    int updateByPrimaryKeySelective(AccountLoginLog record);

    int updateByPrimaryKey(AccountLoginLog record);
}