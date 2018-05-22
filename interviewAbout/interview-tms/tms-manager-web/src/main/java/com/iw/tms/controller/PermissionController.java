package com.iw.tms.controller;

import com.iw.tms.entity.Permission;
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
 * 权限控制器
 */
@Controller
@RequestMapping("/manage/permission")
public class PermissionController {
    // TODO: 2018/5/21 0021 完成权限页面跳转
    // TODO: 2018/5/21 0021 完成添加权限功能
    // TODO: 2018/5/22 0022 完成前端展示树形权限列表功能
    // TODO: 2018/5/22 0022 完成根据权限类型查找所有符合权限类型的权限
    // TODO: 2018/5/22 0022 完成新增权限
    // TODO: 2018/5/22 0022 完成权限合法判断
    // TODO: 2018/5/22 0022 解决前端trid.js插件显示BUG,将查询结果集过滤排序


    @Autowired
    private RolesPermissionService rolesPermissionService;


    /**
     * 跳转到权限管理首页
     * @return
     */
    @GetMapping()
    public String home(Model model) {
        List<Permission> permissionList = rolesPermissionService.listPermission();
        model.addAttribute("permissionList",permissionList);
        return "manage/permission/home";
    }

    /**
     * 跳转到添加权限首页
     * 将所有类型为按钮的权限传到前端
     * @param model
     * @return
     */
    @GetMapping("/new")
    public String insetPermission(Model model){
        List<Permission> permissionList = rolesPermissionService.selectPermissionByPermissionType(Permission.MENU_TYPE);

        model.addAttribute("permissionList",permissionList);
        return "manage/permission/new";
    }

    /**
     * 添加权限
     * @param permission
     * @return
     */
    @PostMapping("/new")
    public String insertPermission(Permission permission, RedirectAttributes redirectAttributes){
        try {
            rolesPermissionService.insertPermission(permission);
            redirectAttributes.addFlashAttribute("message","添加权限成功");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message",e.getMessage());
            e.printStackTrace();
        }

        return "redirect:/manage/permission";
    }
}
