package com.ksit.tms.mapper;

import com.ksit.tms.entity.Permission;
import com.ksit.tms.entity.Roles;
import com.ksit.tms.entity.RolesPermissionExample;
import com.ksit.tms.entity.RolesPermissionKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolesPermissionMapper {
    long countByExample(RolesPermissionExample example);

    int deleteByExample(RolesPermissionExample example);

    int deleteByPrimaryKey(RolesPermissionKey key);

    int insert(RolesPermissionKey record);

    int insertSelective(RolesPermissionKey record);

    List<RolesPermissionKey> selectByExample(RolesPermissionExample example);

    int updateByExampleSelective(@Param("record") RolesPermissionKey record, @Param("example") RolesPermissionExample example);

    int updateByExample(@Param("record") RolesPermissionKey record, @Param("example") RolesPermissionExample example);


    /**
     * 根据账户id 查出来对应的所有角色
     * @param id
     * @return
     */
    List<Roles> selectRoleByAccountId(Integer id);

    /**
     * 根据角色id 去查出来角色对应的所有权限
     * @param id
     * @return
     */
    List<Permission> selectPermissionByRolesId(Integer id);
}