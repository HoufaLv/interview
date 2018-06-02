package com.ksit.tms.service.impl;

import com.ksit.tms.entity.*;
import com.ksit.tms.exception.ServiceException;
import com.ksit.tms.mapper.PermissionMapper;
import com.ksit.tms.mapper.RolesMapper;
import com.ksit.tms.mapper.RolesPermissionMapper;
import com.ksit.tms.service.RolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 权限和角色的 业务层
 *
 * @author Lvhoufa
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    /**
     * 记录日志
     */
    private static Logger logger = LoggerFactory.getLogger(RolePermissionServiceImpl.class);

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolesMapper rolesMapper;
    @Autowired
    private RolesPermissionMapper rolesPermissionMapper;

    /**
     * 根据type 来查询 permissionList
     *
     * @param buttonType
     * @return
     */
    @Override
    public List<Permission> selectPermissionListByType(String buttonType) {
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.createCriteria().andPermissionTypeEqualTo(buttonType);

        return permissionMapper.selectByExample(permissionExample);
    }

    /**
     * 新增权限
     *
     * @param permission
     */
    @Override
    public void insertPermission(Permission permission) {
        permissionMapper.insertSelective(permission);
        logger.info("新增权限: {}", permission);

    }

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<Permission> selectAll() {
        PermissionExample permissionExample = new PermissionExample();
        /*递归一下,将数据归类,用于将前端数据展示*/
        return permissionMapper.selectByExample(permissionExample);
    }

    /**
     * 根据id 来删除权限
     *
     * @param id 权限的id
     */
    @Override
    public void deletePermission(Integer id) {

        /*//如果要删除的权限下有子节点,则不能删除
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.createCriteria().andParentIdEqualTo(id);

        List<Permission> permissionList = permissionMapper.selectByExample(permissionExample);

        //如果要删除的权限下有子节点,则不能删除,则无法删除,controller 捕获到异常,前端ajax 将可以接收到状态
        if (permissionList!=null && !permissionList.isEmpty()){
            throw new ServiceException("存在子节点,无法删除");
        }

        //如果权限已经被使用,则无法删除
        //根据权限id 去关联关系表中去删除对应数据
        RolesPermissionExample rolesPermissionExample = new RolesPermissionExample();
        rolesPermissionExample.createCriteria().andTPermissionIdEqualTo(id);
        List<RolesPermissionKey> rolesPermissionKeys = rolesPermissionMapper.selectByExample(rolesPermissionExample);
        if (rolesPermissionKeys!=null && !rolesPermissionKeys.isEmpty()){
            throw new ServiceException("权限被绑定,无法删除");
        }

        //否则可以删除
        //根据权限的id 查找到对应的权限对象
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        permissionMapper.deleteByPrimaryKey(id);
        logger.info("删除权限 {}",permission);*/

        //查询该权限是否有子节点
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.createCriteria().andParentIdEqualTo(id);

        List<Permission> permissionList = permissionMapper.selectByExample(permissionExample);
        if (permissionList != null && !permissionList.isEmpty()) {
            throw new ServiceException("该权限下有子节点，删除失败");
        }

        //查询权限是否被角色使用
        RolesPermissionExample rolesPermissionExample = new RolesPermissionExample();
        rolesPermissionExample.createCriteria().andTPermissionIdEqualTo(id);

        List<RolesPermissionKey> rolesPermissionKeyList = rolesPermissionMapper.selectByExample(rolesPermissionExample);
        if (rolesPermissionKeyList != null && !rolesPermissionKeyList.isEmpty()) {
            throw new ServiceException("该权限被角色引用，删除失败");
        }
        //如果没有被使用，则可以直接删除
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        permissionMapper.deleteByPrimaryKey(id);
        logger.info("删除权限 {}", permission);
    }

    /**
     * 新增角色
     * 要使用事务
     *
     * @param roles        要添加的角色
     * @param permissionId 对应的权限列表
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void insertRoles(Roles roles, Integer[] permissionId) {
        roles.setCreateTime(new Date());

        //新增角色
        rolesMapper.insertSelective(roles);

        //保存角色和权限的对应关系,每有一条关联关系,就在数据库中保存一下
        if (permissionId != null && permissionId.length != 0) {
            for (Integer i :
                    permissionId) {

                //封装每一条数据
                RolesPermissionKey rolesPermissionKey = new RolesPermissionKey();
                rolesPermissionKey.settRolesId(roles.getId());
                rolesPermissionKey.settPermissionId(i);

                System.out.println(rolesPermissionKey);
                //保存关联关系
                rolesPermissionMapper.insert(rolesPermissionKey);
            }
        }

        //记录日志
        logger.info("保存角色 {}", roles);

    }

    /**
     * 查询所有角色信息,不包括权限,权限需要额外查询
     *
     * @return
     */
    @Override
    public List<Roles> selectAllRoles() {
        return rolesMapper.selectByExample(new RolesExample());
    }

    /**
     * 根据id 来查找对应的权限
     *
     * @param id
     * @return
     */
    @Override
    public Permission selectPermissionById(Integer id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新权限
     *
     * @param permission 原来的权限
     */
    @Override
    public void updatePermission(Permission permission) {
        permission.setUpdateTime(new Date());
        permissionMapper.updateByPrimaryKeySelective(permission);

        logger.info("更新权限 {}", permission);
    }

    /**
     * 查出所有角色和对应的权限列表
     *
     * @return
     */
    @Override
    public List<Roles> selectAllRolesWithPermission() {
        return rolesMapper.selectAllWithPermission();
    }

    /**
     * 根据id 来删除角色
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteRoleById(Integer id) {
        //如果关联关系表中有角色的信息,应该先从关联关系表中删除角色的信息

        RolesPermissionExample rolesPermissionExample = new RolesPermissionExample();
        rolesPermissionExample.createCriteria().andTRolesIdEqualTo(id);
        List<RolesPermissionKey> rolesPermissionKeys = rolesPermissionMapper.selectByExample(rolesPermissionExample);
        if (rolesPermissionKeys != null && !rolesPermissionKeys.isEmpty()) {
            throw new ServiceException("角色绑定有权限,无法删除");
        }

        rolesMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据id 查询出完整的角色信息
     *
     * @param id 角色的id
     * @return 角色对象
     */
    @Override
    public Roles selectRoleWithPermissionById(Integer id) {
        Roles roles = rolesMapper.selectRoleWithPermissionById(id);
        return roles;
    }

    /**
     * 更新roles
     *
     * @param roles
     * @param permissionId
     */
    @Override
    public void updateRoles(Roles roles, Integer[] permissionId) {

        roles.setUpdateTime(new Date());
        rolesMapper.updateByPrimaryKeySelective(roles);

        //删除对应关系
        //从rolesPermission 表中删除所有roles.id 的记录
        RolesPermissionExample rolesPermissionExample = new RolesPermissionExample();
        rolesPermissionExample.createCriteria().andTRolesIdEqualTo(roles.getId());
        rolesPermissionMapper.deleteByExample(rolesPermissionExample);

        //重建关系
        //遍历数组,创建对应关系类,将一条一条的对应关系添加进数据库
        for (Integer i : permissionId) {
            RolesPermissionKey rolesPermissionKey = new RolesPermissionKey();
            rolesPermissionKey.settRolesId(roles.getId());
            rolesPermissionKey.settPermissionId(i);
            rolesPermissionMapper.insert(rolesPermissionKey);
        }

        //记录日志
        logger.info("更新角色{}",roles);
    }

    /**
     * 根据账户的id 来查找对应的所有角色
     *
     * @param id
     * @return
     */
    @Override
    public List<Roles> selectRoleByAccountId(Integer id) {
        return rolesMapper.selectRoleByAccountId(id);
    }

    /**
     * 根据角色id 去查找对应的所有权限
     *
     * @param id
     * @return
     */
    @Override
    public List<Permission> selectPermissionByRolesId(Integer id) {
        return permissionMapper.selectPermissionByRolesId(id);
    }


}
