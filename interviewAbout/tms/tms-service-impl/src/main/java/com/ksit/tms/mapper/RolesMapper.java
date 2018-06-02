package com.ksit.tms.mapper;

import com.ksit.tms.entity.Roles;
import com.ksit.tms.entity.RolesExample;
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

    List<Roles> selectAllWithPermission();

    /**
     * 根据id 查询角色的完整信息
     * @param id        角色id
     * @return          角色
     */
    Roles selectRoleWithPermissionById(Integer id);

    List<Roles> selectRoleByAccountId(Integer id);
}