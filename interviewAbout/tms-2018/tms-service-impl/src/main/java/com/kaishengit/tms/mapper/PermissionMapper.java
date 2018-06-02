package com.kaishengit.tms.mapper;

import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.entity.PermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PermissionMapper {
    long countByExample(PermissionExample example);

    int deleteByExample(PermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    List<Permission> selectByExample(PermissionExample example);

    Permission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    /*-------------以下方法为自定义-------------------*/

    /**
     * 根据角色id查找角色拥有的权限
     * @date 2018/4/18
     * @param [id]
     * @return java.util.List<com.kaishengit.tms.entity.Permission>
     */
    List<Permission> findPermssionsByRolesId(Integer id);
}