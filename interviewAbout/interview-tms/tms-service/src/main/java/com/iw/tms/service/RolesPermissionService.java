package com.iw.tms.service;

import com.iw.tms.entity.Permission;
import com.iw.tms.entity.Roles;

import java.util.List;

public interface RolesPermissionService {
    /**
     * 根据权限累心查找所有符合权限类型的权限
     * @param menuType
     * @return
     */
    List<Permission> selectPermissionByPermissionType(String menuType);

    /**
     * 添加权限
     * @param permission
     */
    void insertPermission(Permission permission);

    /**
     * 查询所有权限信息
     * @return
     */
    List<Permission> listPermission();

    /**
     * 添加角色
     * @param roles
     * @param permissionId
     */
    void addRole(Roles roles, Integer[] permissionId);

    /**
     * 删除权限
     * @param id
     */
    void delPermission(Integer id);

    /**
     * 查询所有角色信息,角色信息内部封装了一个List<Permission>
     * @return
     */
    List<Roles> selectRolesWithPermission();

    /**
     * 查询所有角色信息
     * @return
     */
    List<Roles> listRoles();

    /**
     * 查询所有角色
     * @return
     */
    List<Roles> selectAllRoles();
}
