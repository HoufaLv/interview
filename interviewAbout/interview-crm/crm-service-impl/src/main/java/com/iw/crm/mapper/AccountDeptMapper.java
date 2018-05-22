package com.iw.crm.mapper;

import com.iw.crm.entity.AccountDeptExample;
import com.iw.crm.entity.AccountDeptKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountDeptMapper {
    long countByExample(AccountDeptExample example);

    int deleteByExample(AccountDeptExample example);

    int deleteByPrimaryKey(AccountDeptKey key);

    int insert(AccountDeptKey record);

    int insertSelective(AccountDeptKey record);

    List<AccountDeptKey> selectByExample(AccountDeptExample example);

    int updateByExampleSelective(@Param("record") AccountDeptKey record, @Param("example") AccountDeptExample example);

    int updateByExample(@Param("record") AccountDeptKey record, @Param("example") AccountDeptExample example);
}