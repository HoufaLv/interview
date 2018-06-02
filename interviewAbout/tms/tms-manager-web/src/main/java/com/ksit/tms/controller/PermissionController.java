package com.ksit.tms.controller;

import com.ksit.tms.com.ksit.tms.dto.ResponseBean;
import com.ksit.tms.entity.Permission;
import com.ksit.tms.exception.ServiceException;
import com.ksit.tms.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

/**
 * 权限业务控制器
 * @author Lvhoufa
 */
@Controller
@RequestMapping(value = "/manage/permission")
public class PermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    /**
     * 如果只请求/manage/permission  这个连接,就到permission 首页去,显示所有权限信息
     * @param model
     * @return
     */
    @GetMapping
    public String home(Model model){
        /*将所有类型查出来,然后展示到页面上去,根据type 查*/

        List<Permission> permissionList = rolePermissionService.selectAll();

        model.addAttribute("permissionList",permissionList);

        return "manage/permission/home";
    }

    /**
     * 准备进入 新增权限页面
     * @return
     */
    @GetMapping(value = "/new")
    public String insertPermission(Model model){
        //将所有菜单的权限查出来返回到页面上去
        List<Permission> permissionList = rolePermissionService.selectPermissionListByType(Permission.MENU_TYPE);
        model.addAttribute("permissionList",permissionList);

        return "manage/permission/newPermission";
    }

    /**
     * 将新增的权限 提交到数据库
     * @param permission
     * @return
     */
    @PostMapping(value = "/new")
    public String insertPermission(Permission permission, RedirectAttributes redirectAttributes){
        //将前端传过来的权限添加到数据库中去,同时设置新增时间
        permission.setCreateTime(new Date());

        rolePermissionService.insertPermission(permission);
        redirectAttributes.addFlashAttribute("message","新增成功");

        return "redirect:/manage/permission";
    }

    /**
     * 根据id 来删除对应的权限
     * 需要接收重写url 传过来的路径上的id
     * @param id
     * @return      ajaxResult
     */
    @GetMapping(value = "/{id:\\d+}/del")
    public @ResponseBody ResponseBean deletePermission(@PathVariable Integer id){
        System.out.println("id = " + id);
        try {
            rolePermissionService.deletePermission(id);
            return ResponseBean.success();
        } catch (ServiceException e) {
            //如果失败则将message 传给前端,使用layer.js 来展示
            return ResponseBean.error(e.getMessage());
        }
    }

    /**
     * 根据id 来更新对应的权限
     * @param id
     * @return
     */
    @GetMapping(value = "/{id:\\d+}/update")
    public String updatePermission(@PathVariable("id") Integer id,Model model){
        //前端只要传过来一个数字就行了,不需要指定id 就可以在这里得到

        //查出Permission 并展示到页面上去
        Permission permission = rolePermissionService.selectPermissionById(id);

        //修改父权限,将所有的权限查出来,返回到页面上去
        //List<Permission> permissionList = rolePermissionService.selectPermissionListByType(Permission.MENU_TYPE);


        model.addAttribute("oldPpermission",permission);
        //model.addAttribute("permissionList",permissionList);

        return "manage/permission/updatePermission";
    }

    /**
     * 根据id 来个更新对应的权限
     * @param permission
     * @return
     */
    @PostMapping(value = "/{id:\\d+}/update")
    public String updatePermission(Permission permission){

        //设置更新时间,暂时不更新父权限
        permission.setUpdateTime(new Date());

        rolePermissionService.updatePermission(permission);

        return "redirect:/manage/permission";
    }

}
