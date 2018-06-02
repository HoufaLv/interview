package com.kaishengit.tms.mapper;

import com.kaishengit.tms.entity.Roles;
import com.kaishengit.tms.entity.RolesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolesMapper {
    long countByExample(RolesExample example);

    int deleteByExample(RolesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Roles record);

    int insertSelective(Roles record);

    List<Roles> selectByExample(RolesExample example);

    Roles selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Roles record, @Param("example") RolesExample example);

    int updateByExample(@Param("record") Roles record, @Param("example") RolesExample example);

    int updateByPrimaryKeySelective(Roles record);

    int updateByPrimaryKey(Roles record);

    /*-------------以下方法为自定义-------------------*/

    Roles findByIdWithPermission(Integer accountId);

    List<Roles> findAllWithPermission();

    List<Roles> findRolesByAccountId(Integer id);
}