package com.kaishengit.tms.controller;


import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.kaishengit.tms.controller.exception.NotFoundException;
import com.kaishengit.tms.entity.StoreAccount;
import com.kaishengit.tms.entity.TicketStore;
import com.kaishengit.tms.fileStore.QiniuStore;
import com.kaishengit.tms.service.TicketStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;


/**
 * 售票点管理控制器
 * @author drm
 * @date 2018/4/13
 */
@Controller
@RequestMapping("/ticketstore")
public class TicketStoreController {

    @Autowired
    private TicketStoreService ticketStoreService;
    @Autowired
    private QiniuStore qiniuStore;

    /**
     * 售票点首页，查看售票点列表
     * @date 2018/4/15
     * @param
     * @return java.lang.String
     */
    @GetMapping
    public String home(Model model,
                       @RequestParam(name = "p",required = false,defaultValue = "1") Integer pageNo,
                       @RequestParam(required = false,defaultValue = "") String storeName,
                       @RequestParam(required = false,defaultValue = "") String storeManager,
                       @RequestParam(required = false,defaultValue = "") String storeTel){
        Map<String,Object> queryParam = Maps.newHashMap();
        queryParam.put("storeName",storeName);
        queryParam.put("storeManager",storeManager);
        queryParam.put("storeTel",storeTel);

        PageInfo<TicketStore> pageInfo = ticketStoreService.findAllTicketStoreByPage(pageNo,queryParam);
        model.addAttribute("pageInfo",pageInfo);
        return "store/home";
    }

    /**
     * 新增售票点
     * @date 2018/4/15
     * @param model
     * @return java.lang.String  
     */
    @GetMapping("/new")
    public String newStore(Model model){
        //获取七牛文件上传token
        String upToken = qiniuStore.getUploadToken();
        model.addAttribute("upToken",upToken);
        return "store/new";
    }

    @PostMapping("/new")
    public String newStore(TicketStore ticketStore,RedirectAttributes redirectAttributes){
        ticketStoreService.saveNewTicketStore(ticketStore);
        //刷新Shiro权限
        //customerFilterChainDefinition.updateUrlPermission();
        redirectAttributes.addFlashAttribute("message","新增成功");
        return "redirect:/ticketstore";
    }

    @GetMapping("/{id:\\d+}/info")
    public String infoStore(@PathVariable Integer id,Model model){
        TicketStore ticketStore = ticketStoreService.findTicketStoreById(id);
        if (ticketStore==null){
            throw new NotFoundException();
        }

        StoreAccount storeAccount = ticketStoreService.findStoreAccountById(ticketStore.getStoreAccountId());
        model.addAttribute("ticketStore",ticketStore);
        model.addAttribute("storeAccount",storeAccount);
        return  "store/info";
    }

    @GetMapping("/{id:\\d+}/edit")
    public String updateStore(@PathVariable Integer id,Model model){
        TicketStore ticketStore = ticketStoreService.findTicketStoreById(id);
        if (ticketStore==null){
            throw new NotFoundException();
        }
        //获取七牛上传的token
        String uploadToken = qiniuStore.getUploadToken();
        model.addAttribute("ticketStore",ticketStore);
        model.addAttribute("uploadToken",uploadToken);
        return  "store/edit";
    }

    @PostMapping("/{id:\\d+}/edit")
    public String updateStore(TicketStore ticketStore,RedirectAttributes redirectAttributes){


        ticketStoreService.updateTicketStore(ticketStore);
        redirectAttributes.addFlashAttribute("message","修改成功");
        return  "redirect:/ticketStore";
    }


    @GetMapping ("/{id:\\d+}/del")
    public String updateStore(@PathVariable Integer id,RedirectAttributes redirectAttributes){

        ticketStoreService.delTicketStoreById(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return  "redirect:/ticketstore";
    }
    /*
    *//**
     * 删除角色
     * @date 2018/4/16
     * @param id 角色id
     * @return com.kaishengit.tms.dto.ResponseBean
     *//*
    @GetMapping("/{id:\\d+}/del")
    public @ResponseBody ResponseBean deldeteStore(@PathVariable Integer id){
        try{
            rolePermissionService.delRolesById(id);
            //刷新Shiro权限
            customerFilterChainDefinition.updateUrlPermission();
            return ResponseBean.success();
        }catch(ServiceException ex){
            return ResponseBean.error(ex.getMessage());
        }
    }


    *//**
     * 修改角色
     * @date 2018/4/16
     * @param id, model]
     * @return java.lang.String
     *//*
    @GetMapping("/{id:\\d+}/edit")
    public String updateStore(@PathVariable Integer id,Model model){
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
    public String updateStore(Roles roles,Integer[] permissionId,
                              RedirectAttributes redirectAttributes){
        rolePermissionService.updateRoles(roles,permissionId);
        //刷新Shiro权限
        customerFilterChainDefinition.updateUrlPermission();
        redirectAttributes.addFlashAttribute("message","角色修改成功");
        return "redirect:/manage/roles";
    }

    *//**
     * 判断当前权限列表的复选框是否应该被checked
     * @date 2018/4/16
     * @param rolesPermissionList 当前角色拥有权限, permissionList 权限列表
     * @return java.util.Map<com.kaishengit.tms.entity.Permission,java.lang.Boolean>
     *     权限列表的有序map集合，如果权限列表中的权限被角色所拥有，那么boolean为true
     *//*
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
*/


}
