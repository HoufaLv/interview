package com.ksit.tms.controller;

import com.google.common.collect.Maps;
import com.ksit.tms.com.ksit.tms.dto.ResponseBean;
import com.ksit.tms.entity.Account;
import com.ksit.tms.entity.Roles;
import com.ksit.tms.exception.ServiceException;
import com.ksit.tms.service.AccountService;
import com.ksit.tms.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

/**
 * 账号控制器
 * @author Lvhoufa
 */
@Controller
@RequestMapping(value = "/manage/account")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private RolePermissionService rolePermissionService;

    @GetMapping
    public String home(Model model,
                       @RequestParam(required = false) Integer rolesId,
                       @RequestParam(required = false) String nameMobile){
        Map<String,Object> requestParam = Maps.newHashMap();
        requestParam.put("nameMobile",nameMobile);
        requestParam.put("rolesId",rolesId);

        model.addAttribute("accountList",accountService.findAllAccountWithRolesByQueryParam(requestParam));
        model.addAttribute("rolesList",rolePermissionService.selectAllRoles());
        return "manage/account/home";
    }

    @GetMapping(value = "/new")
    public String newAccount(Model model){
        //去添加账户之前,先将所有的角色查出来,然后展示到页面上去
        List<Roles> rolesList = rolePermissionService.selectAllRoles();
        model.addAttribute("rolesList",rolesList);

        return "manage/account/newAccount";
    }

    @PostMapping(value = "/new")
    public String newAccount(Account account,Integer[] rolesIds){
        //将account 和 account 对应的roles 存储到对应关系中去
        accountService.insertAccount(account,rolesIds);
        return "redirect:/manage/account";
    }

    @GetMapping(value = "/{id:\\d+}/del")
    public @ResponseBody ResponseBean delAccount(@PathVariable  Integer id){
        try {
            accountService.deleteAccount(id);
            return ResponseBean.success();
        } catch (ServiceException e) {
            return ResponseBean.error(e.getMessage());
        }
    }

    /**
     * 更新角色
     * @param id
     * @param model
     * @return
     */
    @GetMapping(value = "/{id:\\d+}/update")
    public String updateAccount(@PathVariable Integer id,Model model){
        //将当前角色,所有角色,当前账号都传到前端去
        Account account = accountService.selectById(id);

        //所有角色
        List<Roles> rolesList = rolePermissionService.selectAllRoles();

        //当前账户对应的角色
        List<Roles> accountRolesList = accountService.selectRolesByAccountId(id);

        model.addAttribute("account",account);
        model.addAttribute("rolesList",rolesList);
        model.addAttribute("accountRolesList",accountRolesList);

        return "manage/account/updateAccount";
    }

    @PostMapping(value = "/{id:\\d+}/update")
    public String updateAccount(Account account, Integer[] rolesId, RedirectAttributes redirectAttributes){

        accountService.updateAccount(account,rolesId);

        redirectAttributes.addFlashAttribute("message","更新成功");
        //跳转到home 页面去
        return "redirect:/manage/account";
    }

}
