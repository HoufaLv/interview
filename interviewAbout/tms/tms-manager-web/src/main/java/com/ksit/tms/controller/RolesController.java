package com.ksit.tms.controller;

import com.ksit.tms.com.ksit.tms.dto.ResponseBean;
import com.ksit.tms.entity.Permission;
import com.ksit.tms.entity.Roles;
import com.ksit.tms.exception.ServiceException;
import com.ksit.tms.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lvhoufa
 */
@Controller
@RequestMapping(value = "/manage/role")
public class RolesController {

    @Autowired
    private RolePermissionService rolePermissionService;

    /**
     * 跳转到角色管理 home 页面去
     * @return
     */
    @GetMapping
    public String home(Model model){
        //在跳转到角色页面的时候,将所有的角色查询出来,展示到页面中去
        //所有的角色信息
        //还要查询角色对应的权限信息
        //这样不行,得写一个三表联查,将角色对应的权限名称查出来

        /*List<Roles> rolesList = rolePermissionService.selectAllRoles();*/
        //List<Map<String,String>> rolePermissionService.selectPermissionByRoleId();
        /*model.addAttribute("rolesList",rolesList);*/
        //List rolePermissionService.selectRolesWithPermission();

        List<Roles> rolesList = rolePermissionService.selectAllRolesWithPermission();
        model.addAttribute("rolesList",rolesList);

        return "manage/roles/home";
    }

    @GetMapping(value = "/new")
    public String insertRoles(Model model){
        //查询出所有的权限,传给前端展示
        model.addAttribute("permissionList",rolePermissionService.selectAll());

        return "manage/roles/newRoles";
    }

    /**
     * 新增角色,使用数组来接收权限的列表
     * @param roles
     * @return
     */
    @PostMapping(value = "/new")
    public String insertRoles(Roles roles, Integer[] permissionId, RedirectAttributes redirectAttributes){

        rolePermissionService.insertRoles(roles,permissionId);
        redirectAttributes.addFlashAttribute("message","新增角色成功!");
        return "redirect:/manage/role";
    }

    /**
     * 删除角色相关信息
     * @param id
     * @return
     */
    @GetMapping(value = "/{id:\\d+}/del")
    public @ResponseBody ResponseBean deleteRoles(@PathVariable  Integer id){

        try {
            rolePermissionService.deleteRoleById(id);
            return ResponseBean.success();
        } catch (ServiceException e) {
            //如果删除失败,给前端传递状态值
            return ResponseBean.error(e.getMessage());
        }
    }

    /**
     * 更新角色相关信息
     * @param id            角色的id
     * @param model         向页面传值
     * @return              跳转页面
     */
    @GetMapping(value = "/{id:\\d+}/update")
    public String updateRole(@PathVariable Integer id,Model model){
        //更新角色信息,先跳到更新页面
        //将id 对应的rules 对象查出来,传给表现层
        Roles roles = rolePermissionService.selectRoleWithPermissionById(id);
        if (roles==null){
            throw new ServiceException("角色不存在.");
        }

        //如果角色存在的话,查询出权限列表,就是查出来所有权限的列表
        List<Permission> permissionList = rolePermissionService.selectAll();

        //做一下检查,看看permission 有没有被选中,返回一个Map
        Map<Permission,Boolean> permissionMap = checkSelect(permissionList,roles.getPermissionList());
        model.addAttribute("roles",roles);
        model.addAttribute("permissionMap",permissionMap);

        return "/manage/roles/updateRole";
    }

    /**
     * 更新角色
     * @param roles
     * @param permissionId
     * @param redirectAttributes
     * @return
     */
    @PostMapping(value = "/{id:\\d+}/update")
    @Transactional(rollbackFor = RuntimeException.class)
    public String updateRole(Roles roles,Integer[] permissionId,RedirectAttributes redirectAttributes){
        //保存关联关系
        roles.setUpdateTime(new Date());
        rolePermissionService.updateRoles(roles,permissionId);
        redirectAttributes.addFlashAttribute("message","角色更新成功");

        return "redirect:/manage/role";
    }

    private Map<Permission, Boolean> checkSelect(List<Permission> permissionList, List<Permission> rolesPermissionList) {
        Map<Permission,Boolean> resultMap = new LinkedHashMap<>();
        //总的思路,遍历所有权限,如果rolesPermissionList 中的被选中了,就添加到Map 中去
        for (Permission permission :permissionList) {
            Boolean flag = false;
            for (Permission rolesPermission :rolesPermissionList) {
                //如果权限和roles 的权限deid相等,则说明被选中了,就退出这次循环,可以保证所有有关联关系的都被选中
                if (rolesPermission.getId().equals(permission.getId())){
                    flag = true;
                    break;
                }
            }
            resultMap.put(permission,flag);
        }

        return resultMap;
    }

}
