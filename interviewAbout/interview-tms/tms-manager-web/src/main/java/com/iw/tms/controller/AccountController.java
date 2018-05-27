package com.iw.tms.controller;

import com.google.common.collect.Maps;
import com.iw.tms.controller.exception.ServiceException;
import com.iw.tms.entity.Account;
import com.iw.tms.entity.Permission;
import com.iw.tms.entity.Roles;
import com.iw.tms.service.AccountService;
import com.iw.tms.service.RolesPermissionService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/manage/account")
public class AccountController {

    // TODO: 2018/5/23 0023 完成添加账号页面设计
    // TODO: 2018/5/24 0024 完成添加账号功能
    // TODO: 2018/5/24 0024 完成账号显示功能
    // TODO: 2018/5/24 0024 完成账号删除功能

    // TODO: 2018/5/24 0024 完成账号显示,动态搜索功能
    // TODO: 2018/5/24 0024 动态搜索,根据[账号或手机号码] 角色分类
    // TODO: 2018/5/25 0025 完成账号修改功能
    // TODO: 2018/5/27 0027 spring整合 shiro 框架,完成登陆验证

    
    @Autowired
    private RolesPermissionService rolesPermissionService;
    @Autowired
    private AccountService accountService;

    /**
     * 跳转到账户管理首页
     * @return
     */
    @GetMapping
    public String home(Model model,
                       @RequestParam(required = false) Integer rolesId,
                       @RequestParam(required = false) String nameMobile){

        //完成动态搜索功能,根据角色id 和手机号来查询
        //如果传过来用户名和密码,根据账户或者密码查,如果没有,就查询出来所有,传到前端
        HashMap<String, Object> requestParam = Maps.newHashMap();
        requestParam.put("nameMobile",nameMobile);
        requestParam.put("rolesId",rolesId);

        //动态查询
        List<Account> accountList = accountService.selectAllAccountWithRolesByQueryParam(requestParam);
        List<Roles> rolesList = rolesPermissionService.selectAllRoles();

        model.addAttribute("accountList",accountList);
        model.addAttribute("rolesList",rolesList);
        return "manage/account/home";
    }

    /**
     * 跳转到新增账户页面
     * @return
     */
    @GetMapping("/new")
    public String addAccount(Model model){
        List<Roles> rolesList = rolesPermissionService.listRoles();
        model.addAttribute("rolesList",rolesList);
        return "manage/account/new";
    }

    /**
     * 添加账户
     * @param account
     * @param rolesIds
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/new")
    public String newAccount(Account account,
                             Integer[] rolesIds,
                             RedirectAttributes redirectAttributes){
        try {
            accountService.insertAccount(account,rolesIds);
            redirectAttributes.addFlashAttribute("message","添加成功");
            return "redirect:/manage/account/";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message",e.getMessage());
            return "redirect:/manage/account/";
        }
    }

    /**
     * 跳转到修改页面,将前端所需要的数据传过去
     * @param id
     * @return
     */
    @GetMapping("/{id:\\d+}/edit")
    public String updateAccount(@PathVariable Integer id,
                                Model model){
        //先检查账号是否存在
        Account account = accountService.selectByAccountId(id);
        if (account==null){
            throw new ServiceException("账号不存在");
        }

        //将所有的角色传到前端页面
        List<Roles> rolesList = rolesPermissionService.listRoles();
        //将当前账号对应的角色信息查出来传到后台
        List<Roles> accountRolesList = rolesPermissionService.selectRolesByAccountId(id);

        model.addAttribute("rolesList",rolesList);
        model.addAttribute("accountRolesList",accountRolesList);
        model.addAttribute("account",account);

        return "manage/account/edit";
    }

    /**
     * 修改账户
     * @param account
     * @param rolesIds
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/{id:\\d+}/edit")
    public String updateAccount(Account account,
                                Integer[] rolesIds,
                                RedirectAttributes redirectAttributes){
        accountService.updateAccount(account,rolesIds);
        redirectAttributes.addFlashAttribute("message","账号修改成功");
        return "redirect:/manage/account/";
    }
}
