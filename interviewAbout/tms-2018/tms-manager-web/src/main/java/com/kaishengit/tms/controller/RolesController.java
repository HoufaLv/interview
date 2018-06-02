package com.kaishengit.tms.controller;

import com.kaishengit.tms.controller.exception.NotFoundException;
import com.google.common.collect.Maps;
import com.kaishengit.tms.dto.ResponseBean;
import com.kaishengit.tms.entity.Permission;
import com.kaishengit.tms.entity.Roles;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.service.RolePermissionService;
import com.kaishengit.tms.shiro.CustomerFilterChainDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;


/**
 * 角色管理控制器
 * @author drm
 * @date 2018/4/13
 */
@Controller
@RequestMapping("/manage/roles")
public class RolesController {

    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private CustomerFilterChainDefinition customerFilterChainDefinition;

    /**
     * 角色页首页，查看角色列表
     * @date 2018/4/15
     * @param []
     * @return java.lang.String
     */
    @GetMapping
    public String home(Model model){
        model.addAttribute("rolesList",rolePermissionService.findAllRolesWithPermission());
        return "manage/roles/home";
    }

    /**  
     * 新增角色
     * @date 2018/4/15
     * @param [model]  
     * @return java.lang.String  
     */ 
    @GetMapping("/new")
    public String newRoles(Model model){
        model.addAttribute("permissionList",rolePermissionService.findAllPermission());
        return "manage/roles/new";
    }

    @PostMapping("/new")
    public String newRoles(Roles roles,Integer[] permissionId,RedirectAttributes redirectAttributes){
        rolePermissionService.saveRoles(roles,permissionId);
        //刷新Shiro权限
        customerFilterChainDefinition.updateUrlPermission();
        redirectAttributes.addFlashAttribute("message","新增成功");
        return "redirect:/manage/roles";
    }

    /**
     * 删除角色
     * @date 2018/4/16
     * @param id 角色id
     * @return com.kaishengit.tms.dto.ResponseBean
     */
    @GetMapping("/{id:\\d+}/del")
    public @ResponseBody ResponseBean deldeteRoles(@PathVariable Integer id){
        try{
            rolePermissionService.delRolesById(id);
            //刷新Shiro权限
            customerFilterChainDefinition.updateUrlPermission();
            return ResponseBean.success();
        }catch(ServiceException ex){
            return ResponseBean.error(ex.getMessage());
        }
    }


    /**
     * 修改角色
     * @date 2018/4/16
     * @param id, model]
     * @return java.lang.String
     */
    @GetMapping("/{id:\\d+}/edit")
    public String updateRoles(@PathVariable Integer id,Model model){
        //查询角色及角色拥有的权限
        Roles roles = rolePermissionService.findRolesWithPermissionById(id);
        if (roles == null){
            throw new NotFoundException();
        }
        //查询所有权限列表
        List<Permission> permissionList = rolePermissionService.findAllPermission();

        //判断权限列表是否被checked
        Map<Permission,Boolean> map = checkedPermissionList(roles.getPermissionList(),permissionList);

        model.addAttribute("roles",roles);
        model.addAttribute("permissionMap",map);

        return "manage/roles/edit";
    }

    @PostMapping("/{id:\\d+}/edit")
    public String updateRoles(Roles roles,Integer[] permissionId,
                              RedirectAttributes redirectAttributes){
        rolePermissionService.updateRoles(roles,permissionId);
        //刷新Shiro权限
        customerFilterChainDefinition.updateUrlPermission();
        redirectAttributes.addFlashAttribute("message","角色修改成功");
        return "redirect:/manage/roles";
    }

    /**
     * 判断当前权限列表的复选框是否应该被checked
     * @date 2018/4/16
     * @param rolesPermissionList 当前角色拥有权限, permissionList 权限列表
     * @return java.util.Map<com.kaishengit.tms.entity.Permission,java.lang.Boolean>
     *     权限列表的有序map集合，如果权限列表中的权限被角色所拥有，那么boolean为true
     */
    private Map<Permission,Boolean> checkedPermissionList(List<Permission> rolesPermissionList,List<Permission> permissionList){
        Map<Permission,Boolean> resultMap = Maps.newLinkedHashMap();

        for (Permission permission:permissionList){
            Boolean flag = false;
            for (Permission rolesPermission:rolesPermissionList){
                if (permission.getId().equals(rolesPermission.getId())){
                    flag = true;
                    break;
                }
            }
            resultMap.put(permission,flag);
        }
        return resultMap;
    }



}
