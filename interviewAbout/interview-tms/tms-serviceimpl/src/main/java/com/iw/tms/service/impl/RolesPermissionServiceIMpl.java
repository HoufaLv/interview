package com.iw.tms.service.impl;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.iw.tms.entity.*;
import com.iw.tms.exception.ServiceException;
import com.iw.tms.mapper.PermissionMapper;
import com.iw.tms.mapper.RolesMapper;
import com.iw.tms.mapper.RolesPermissionMapper;
import com.iw.tms.service.RolesPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RolesPermissionServiceIMpl implements RolesPermissionService {
    private Logger logger = LoggerFactory.getLogger(RolesPermissionServiceIMpl.class);

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolesMapper rolesMapper;
    @Autowired
    private RolesPermissionMapper rolesPermissionMapper;

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

        logger.info("添加权限{}",permission);
    }

    /**
     * 查询所有权限信息
     *
     * @return
     */
    @Override
    public List<Permission> listPermission() {
        List<Permission> permissionList = permissionMapper.selectByExample(new PermissionExample());

        //处理trid js 显示异常问题
        List<Permission> resultList = new ArrayList<>();
        treeList(permissionList,resultList,0);
        return resultList;
    }

    /**
     * 将查询数据库的角色列表转换为树形集合结果
     * @param sourceList        数据库查询出来的集合
     * @param endList           转换结束的集合
     * @param parentId          父id
     */
    private void treeList(List<Permission> sourceList, List<Permission> endList, int parentId) {
        // 使用lambda 表达式
        List<Permission> tempList = Lists.newArrayList(Collections2.filter(sourceList,permission -> permission.getParentId().equals(parentId)));

//        List<Permission> liggleList = Lists.newArrayList(Collections2.filter(sourceList, new Predicate<Permission>() {
//            @Override
//            public boolean apply(@Nullable Permission permission) {
//                //遍历list参数中的Permission
//                if (permission.getParentId().equals(parentId)) {
//                    return true;
//                }
//                return false;
//            }
//        }));

        for (Permission permission: tempList) {
            endList.add(permission);
            treeList(sourceList,endList,permission.getId());
        }
    }

    /**
     * 添加角色
     *
     * @param roles
     * @param permissionId
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void addRole(Roles roles, Integer[] permissionId) {

        //判断角色是否存在
        RolesExample rolesExample = new RolesExample();
        rolesExample.createCriteria().andRolesNameEqualTo(roles.getRolesName());
        List<Roles> exitRoles = rolesMapper.selectByExample(rolesExample);

        if (exitRoles==null){
            roles.setCreateTime(new Date());
            roles.setUpdateTime(new Date());

            //添加角色
            rolesMapper.insertSelective(roles);
            logger.info("添加角色{}",roles);

            //每个角色有多个权限,一个角色要添加多个对应的权限记录,循环权限数组,多次添加权限
            for (Integer id : permissionId) {
                //处理角色和权限的管理
                RolesPermissionKey rolesPermissionKey = new RolesPermissionKey();
                rolesPermissionKey.settRolesId(roles.getId());
                rolesPermissionKey.settPermissionId(id);
                rolesPermissionMapper.insertSelective(rolesPermissionKey);
                logger.info("添加角色对应权限{}",rolesPermissionKey);
            }
        }else{
            throw new ServiceException("角色已经存在");
        }

    }

    /**
     * 删除权限
     *
     * @param id
     */
    @Override
    public void delPermission(Integer id) {
        //如果权限已经绑定有子节点,无法删除,如果绑定有角色,无法删除
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.createCriteria().andParentIdEqualTo(id);
        List<Permission> permissionList = permissionMapper.selectByExample(permissionExample);

        if (permissionList!=null && !permissionList.isEmpty()){
            throw new ServiceException("该权限下绑定有子节点,无法删除");
        }

        RolesPermissionExample rolesPermissionExample = new RolesPermissionExample();
        rolesPermissionExample.createCriteria().andTPermissionIdEqualTo(id);
        List<RolesPermissionKey> rolesPermissionKeys = rolesPermissionMapper.selectByExample(rolesPermissionExample);

        if (rolesPermissionKeys!=null && !rolesPermissionKeys.isEmpty()){
            throw new ServiceException("该权限绑定有角色,无法删除");
        }

        Permission permission = permissionMapper.selectByPrimaryKey(id);

        permissionMapper.deleteByPrimaryKey(id);
        logger.info("删除权限 {}",permission);
    }

    /**
     * 查询所有角色信息,角色信息内部封装了一个List<Permission>
     *
     * @return
     */
    @Override
    public List<Roles> selectRolesWithPermission() {
        return rolesMapper.selectAllRolesWithPermission();
    }
}