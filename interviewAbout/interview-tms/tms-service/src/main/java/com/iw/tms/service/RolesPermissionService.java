package com.iw.tms.service;

import com.iw.tms.entity.Permission;

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
}
