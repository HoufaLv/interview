package com.iw.tms.controller;

import com.iw.tms.dto.ResponseBean;
import com.iw.tms.entity.Permission;
import com.iw.tms.service.RolesPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    // TODO: 2018/5/23 0023 完成权限删除,进行权限合法值判断

    @Autowired
    private RolesPermissionService rolesPermissionService;


    /**
     * 跳转到权限管理首页
     *
     * @return
     */
    @GetMapping()
    public String home(Model model) {
        List<Permission> permissionList = rolesPermissionService.listPermission();
        model.addAttribute("permissionList", permissionList);
        return "manage/permission/home";
    }

    /**
     * 跳转到添加权限首页
     * 将所有类型为按钮的权限传到前端
     *
     * @param model
     * @return
     */
    @GetMapping("/new")
    public String insetPermission(Model model) {
        List<Permission> permissionList = rolesPermissionService.selectPermissionByPermissionType(Permission.MENU_TYPE);

        model.addAttribute("permissionList", permissionList);
        return "manage/permission/new";
    }

    /**
     * 添加权限
     *
     * @param permission
     * @return
     */
    @PostMapping("/new")
    public String insertPermission(Permission permission, RedirectAttributes redirectAttributes) {
        try {
            rolesPermissionService.insertPermission(permission);
            redirectAttributes.addFlashAttribute("message", "添加权限成功");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            e.printStackTrace();
        }
        return "redirect:/manage/permission";
    }


    /**
     * 根据id 删除权限
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}/del")
    public @ResponseBody ResponseBean delPermission(@PathVariable Integer id) {
        try {
            rolesPermissionService.delPermission(id);
            return ResponseBean.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error(e.getMessage());
        }
    }
}
