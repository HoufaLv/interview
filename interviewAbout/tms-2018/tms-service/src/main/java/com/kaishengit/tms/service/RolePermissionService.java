package com.kaishengit.tms.service;

import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.entity.Roles;
import com.kaishengit.tms.exception.ServiceException;

import java.util.List;

/**   
 * 角色和权限的业务类
 * @author drm  
 * @date 2018/4/13   
 */ 
public interface RolePermissionService {

    /**  
     * 添加权限
     * @date 2018/4/13
     * @param [permission]  
     * @return void  
     */ 
    void savePermission(Permission permission);
    
    /**  
     * 根据权限类型查询权限列表
     * @date 2018/4/13
     * @param [PermissionType]  权限类型 菜单|按钮
     * @return java.util.List<com.kaishengit.tms.entity.Permission>  
     */ 
    List<Permission> findPermissionByPermissionType(String permissionType);
    
    /**  
     * 查询所有权限
     * @date 2018/4/13
     * @param []  
     * @return java.util.List<com.kaishengit.tms.entity.Permission>  
     */ 
    List<Permission> findAllPermission();

    /**  
     * 新增角色
     * @date 2018/4/15
     * @param [roles 角色对象, permissionId 角色对应的权限ID列表]
     * @return void  
     */ 
    void saveRoles(Roles roles,Integer[] permissionId);

    /**  
     * 根据权限ID删除权限
     * @date 2018/4/15
     * @param [id]  
     * @return void  删除失败抛出此异常，例如权限已经被角色使用
     */ 
    void delPermissionById(Integer id) throws ServiceException;

    /**
     * 查询所有角色
     * @date 2018/4/15
     * @param []
     * @return java.util.List<com.kaishengit.tms.entity.Roles>
     */
    List<Roles> findAllRoles();

    /**
     * 查询角色以及角色拥有的权限
     * @date 2018/4/16
     * @param [id]
     * @return com.kaishengit.tms.entity.Roles
     */
    Roles findRolesWithPermissionById(Integer id);

    /**
     * 查询所有角色以及角色拥有的权限
     * @date 2018/4/16
     * @param []
     * @return java.util.List<com.kaishengit.tms.entity.Roles>
     */
    List<Roles> findAllRolesWithPermission();

    /**
     * 根据id删除角色
     * @date 2018/4/16
     * @param [id]
     * @return void
     */
    void delRolesById(Integer id);

    /**
     * 修改角色对象
     * @date 2018/4/16
     * @param roles 角色对象, permissionId 角色对象的新权限数组ID
     * @return void
     */
    void updateRoles(Roles roles, Integer[] permissionId);

    /**
     * 根据用户id查找用户拥有的角色集合列表
     * @date 2018/4/16
     * @param [id]
     * @return java.util.List<com.kaishengit.tms.entity.Roles>
     */
    List<Roles> findRolesByAccountId(Integer id);

    /**
     * 根据权限id查找权限
     * @date 2018/4/17
     * @param [id]
     * @return com.kaishengit.tms.entity.Permission
     */
    Permission findPermissionById(Integer id);

    /**
     * 修改权限
     * @date 2018/4/17
     * @param [permission]
     * @return void
     */
    void updatePermission(Permission permission);

    /**
     * 根据用户id查找用户拥有的权限，权限可能重复
     * @date 2018/4/18
     * @param [id]
     * @return java.util.List<com.kaishengit.tms.entity.Permission>
     */
    List<Permission> findPermissionsByAccountId(Integer id);

    /**
     * 根据角色id查找角色拥有的权限
     * @date 2018/4/18
     * @param [id]
     * @return java.util.List<com.kaishengit.tms.entity.Permission>
     */
    List<Permission> findPermissionsByRolesId(Integer id);
}
