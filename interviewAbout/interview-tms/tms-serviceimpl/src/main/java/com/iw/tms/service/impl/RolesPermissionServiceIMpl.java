package com.iw.tms.service.impl;

import com.iw.tms.entity.Permission;
import com.iw.tms.entity.PermissionExample;
import com.iw.tms.mapper.PermissionMapper;
import com.iw.tms.service.RolesPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RolesPermissionServiceIMpl implements RolesPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 根据权限类型查找所有符合权限类型的权限
     *
     * @param menuType
     * @return
     */
    @Override
    public List<Permission> selectPermissionByPermissionType(String menuType) {
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.createCriteria().andPermissionTypeEqualTo(menuType);

        List<Permission> permissionList = permissionMapper.selectByExample(permissionExample);
        return permissionList;
    }

    /**
     * 添加权限
     *
     * @param permission
     */
    @Override
    public void insertPermission(Permission permission) {
        permission.setCreateTime(new Date());
        permissionMapper.insertSelective(permission);
    }

    /**
     * 查询所有权限信息
     *
     * @return
     */
    @Override
    public List<Permission> listPermission() {
        List<Permission> permissionList = permissionMapper.selectByExample(new PermissionExample());
        return permissionList;
    }
}