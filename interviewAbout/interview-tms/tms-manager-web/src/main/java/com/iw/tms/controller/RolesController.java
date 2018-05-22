package com.iw.tms.controller;

import com.iw.tms.entity.Permission;
import com.iw.tms.entity.Roles;
import com.iw.tms.service.RolesPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * 角色相关业务控制器
 */
@Controller
@RequestMapping("/manage/roles")
public class RolesController {
    // TODO: 2018/5/22 0022 完成角色页面跳转 
    // TODO: 2018/5/22 0022 完成添加角色页面权限树形结构显示

    @Autowired
    private RolesPermissionService rolesPermissionService;

    /**
     * 跳转到角色首页
     * @return
     */
    @GetMapping
    public String home(){
        return "manage/role/home";
    }

    /**
     * 跳转到添加角色
     * 添加角色也要同时添加权限,将所有的权限查出来传到页面上去
     * @return
     */
    @GetMapping("/new")
    public String addRoles(Model model){
        List<Permission> permissionList = rolesPermissionService.listPermission();

        model.addAttribute("permissionList",permissionList);
        return "manage/role/new";
    }

    /**
     * 添加角色
     * @param roles                 角色
     * @param permissionId          权限id
     * @param redirectAttributes    提示消息
     * @return
     */
    @PostMapping("/new")
    public String addRoles(Roles roles,
                           Integer[] permissionId,
                           RedirectAttributes redirectAttributes){
        try {
            rolesPermissionService.addRole(roles,permissionId);
            redirectAttributes.addFlashAttribute("message","添加成功");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message",e.getMessage());
            e.printStackTrace();
        }

        return "redirect:/manage/roles";
    }

}
