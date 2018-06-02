package com.kaishengit.tms.service.impl;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.kaishengit.tms.entity.*;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.mapper.AccountRolesMapper;
import com.kaishengit.tms.mapper.PermissionMapper;
import com.kaishengit.tms.mapper.RolesMapper;
import com.kaishengit.tms.mapper.RolesPermissionMapper;
import com.kaishengit.tms.service.AccountService;
import com.kaishengit.tms.service.RolePermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *角色和权限的业务类
 * @author drm
 * @date 2018/4/13
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    public static final Logger logger = LoggerFactory.getLogger(RolePermissionServiceImpl.class);

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolesMapper rolesMapper;
    @Autowired
    private RolesPermissionMapper rolesPermissionMapper;
    @Autowired
    private AccountRolesMapper accountRolesMapper;
    @Autowired
    private AccountService accountService;

    /**
     * 添加权限
     *
     * @param permission
     * @return void
     * @date 2018/4/13
     */
    @Override
    public void savePermission(Permission permission) {

        permission.setCreateTime(new Date());
        permissionMapper.insertSelective(permission);
        logger.info("添加权限{}",permission);
    }

    /**
     * 根据权限类型查询权限列表
     *
     * @param  permissionType
     * @return java.util.List<com.kaishengit.tms.entity.Permission>
     * @date 2018/4/13
     */
    @Override
    public List<Permission> findPermissionByPermissionType(String permissionType) {

        PermissionExample permissionExample = new PermissionExample();
        permissionExample.createCriteria().andPermissionTypeEqualTo(permissionType);
        return permissionMapper.selectByExample(permissionExample);
    }

    /**
     * 查询所有权限
     *
     * @return java.util.List<com.kaishengit.tms.entity.Permission>
     * @date 2018/4/13
     */
    @Override
    public List<Permission> findAllPermission() {
        PermissionExample permissionExample = new PermissionExample();
        List<Permission> permissionList = permissionMapper.selectByExample(permissionExample);
        List<Permission> resultList = new ArrayList<>();
        treeList(permissionList,resultList,0);
        return resultList;
    }

    /**
     * 新增角色
     *
     * @param roles  角色对象
     * @param permissionId  角色对应的权限ID列表
     * @return void
     * @date 2018/4/15
     */
    @Override
    public void saveRoles(Roles roles, Integer[] permissionId) {
        //保存角色
        roles.setCreateTime(new Date());
        rolesMapper.insertSelective(roles);
        //保存角色和权限的关系
        if (permissionId!=null){
            for (Integer perId:permissionId){
                RolesPermissionKey rolesPermissionKey = new RolesPermissionKey();
                rolesPermissionKey.setPermissionId(perId);
                rolesPermissionKey.setRolesId(roles.getId());

                rolesPermissionMapper.insert(rolesPermissionKey);
            }
        }

        logger.info("保存角色{}",roles);
    }

    /**
     * 根据权限ID删除权限
     *
     * @param id
     * @return void  删除失败抛出此异常，例如权限已经被角色使用
     * @date 2018/4/15
     */
    @Override
    public void delPermissionById(Integer id) throws ServiceException {
        //查询权限是否有子节点
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.createCriteria().andParentIdEqualTo(id);

        List<Permission> permissionList = permissionMapper.selectByExample(permissionExample);
        if (permissionList!=null && !permissionList.isEmpty()){
            throw new ServiceException("该权限下有子节点，删除失败");
        }

        //查询权限是否被角色使用
        RolesPermissionExample rolesPermissionExample = new RolesPermissionExample();
        rolesPermissionExample.createCriteria().andPermissionIdEqualTo(id);

        List<RolesPermissionKey> rolesPermissionKeyList = rolesPermissionMapper.selectByExample(rolesPermissionExample);
        if (rolesPermissionKeyList!=null && !rolesPermissionKeyList.isEmpty()){
            throw new ServiceException("该权限已被角色引用，删除失败");
        }

        //没有子节点，没被角色使用，直接删除
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        permissionMapper.deleteByPrimaryKey(id);
        logger.info("删除权限{}",permission);
    }

    /**
     * 查询所有角色
     *
     * @return java.util.List<com.kaishengit.tms.entity.Roles>
     * @date 2018/4/15
     */
    @Override
    public List<Roles> findAllRoles() {
        RolesExample rolesExample = new RolesExample();
        return rolesMapper.selectByExample(rolesExample);
    }

    /**
     * 根据ID查询角色以及角色拥有的权限
     *
     * @param id
     * @return com.kaishengit.tms.entity.Roles
     * @date 2018/4/16
     */
    @Override
    public Roles findRolesWithPermissionById(Integer id) {
        return rolesMapper.findByIdWithPermission(id);
    }

    /**
     * 查询所有角色以及角色拥有的权限
     *
     * @return java.util.List<com.kaishengit.tms.entity.Roles>
     * @date 2018/4/16
     */
    @Override
    public List<Roles> findAllRolesWithPermission() {
        return rolesMapper.findAllWithPermission();
    }

    /**
     * 根据id删除角色
     * @param id
     * @return void
     * @date 2018/4/16
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void delRolesById(Integer id) throws ServiceException{
        //查询是否被账号引用，如果引用则不能删除
        AccountRolesExample accountRolesExample = new AccountRolesExample();
        accountRolesExample.createCriteria().andRolesIdEqualTo(id);

        List<AccountRolesKey> accountRolesKeys = accountRolesMapper.selectByExample(accountRolesExample);
        if (accountRolesKeys!=null && !accountRolesKeys.isEmpty()) {
            throw new ServiceException("该角色被账号引用，删除失败");
        }

        //删除角色和权限的关系记录
        RolesPermissionExample rolesPermissionExample = new RolesPermissionExample();
        rolesPermissionExample.createCriteria().andRolesIdEqualTo(id);

        rolesPermissionMapper.deleteByExample(rolesPermissionExample);
        //删除角色
        Roles roles = rolesMapper.selectByPrimaryKey(id);
        rolesMapper.deleteByPrimaryKey(id);

        logger.info("删除角色{}",roles);
    }

    /**
     * 修改角色对象
     *
     * @param roles        角色对象, permissionId 角色对象的新权限数组ID
     * @param permissionId
     * @return void
     * @date 2018/4/16
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateRoles(Roles roles, Integer[] permissionId) {

        //将原有的角色和权限的对应关系删除
        RolesPermissionExample rolesPermissionExample = new RolesPermissionExample();
        rolesPermissionExample.createCriteria().andRolesIdEqualTo(roles.getId());

        rolesPermissionMapper.deleteByExample(rolesPermissionExample);
        //重建角色和权限的对应关系
        if (permissionId!=null){
            for (Integer perId:permissionId){
                RolesPermissionKey rolesPermissionKey = new RolesPermissionKey();
                rolesPermissionKey.setRolesId(roles.getId());
                rolesPermissionKey.setPermissionId(perId);
                rolesPermissionMapper.insert(rolesPermissionKey);
            }
        }


        //修改角色对象
        rolesMapper.updateByPrimaryKeySelective(roles);

        logger.info("修改角色{}",roles);
    }

    /**
     * 根据用户id查找用户拥有的角色集合列表
     *
     * @param id
     * @return java.util.List<com.kaishengit.tms.entity.Roles>
     * @date 2018/4/16
     */
    @Override
    public List<Roles> findRolesByAccountId(Integer id) {
        return rolesMapper.findRolesByAccountId(id);
    }

    /**
     * 根据权限id查找权限
     *
     * @param id
     * @return com.kaishengit.tms.entity.Permission
     * @date 2018/4/17
     */
    @Override
    public Permission findPermissionById(Integer id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改权限
     *
     * @param permission
     * @return void
     * @date 2018/4/17
     */
    @Override
    public void updatePermission(Permission permission) {

        permissionMapper.updateByPrimaryKeySelective(permission);
    }

    /**
     * 根据用户id查找用户拥有的权限，权限可能重复
     *
     * @param id
     * @return java.util.List<com.kaishengit.tms.entity.Permission>
     * @date 2018/4/18
     */
    @Override
    public List<Permission> findPermissionsByAccountId(Integer id) {

        //1.根据AccountId查找用户所拥有的角色集合
        List<Roles> rolesList = findRolesByAccountId(id);
        //2.根据角色集合查找用户所拥有的权限集合
        List<Permission> permissionList = new ArrayList<>();
        for (Roles roles:rolesList){
            List<Permission> list = findPermissionsByRolesId(roles.getId());
            permissionList.addAll(list);
        }

        return permissionList;
    }

    @Override
    public List<Permission> findPermissionsByRolesId(Integer id) {
        return permissionMapper.findPermssionsByRolesId(id);
    }


    /**  
     * 将查询数据库的角色列表转换为树形集合结果
     * @date 2018/4/15
     * @param sourceList 查询出的集合, endList 转换后的集合, parentId 父ID
     * @return void  
     */ 
    private void treeList(List<Permission> sourceList,List<Permission> endList,int parentId){
        List<Permission> tempList = Lists.newArrayList(Collections2.filter(sourceList,permission -> permission.getParentId().equals(parentId)));

        for (Permission permission:tempList){
            endList.add(permission);
            treeList(sourceList,endList,permission.getId());
        }
    }
}
