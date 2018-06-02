package com.iw.tms.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.iw.tms.controller.exception.ServiceException;
import com.iw.tms.entity.StoreAccount;
import com.iw.tms.entity.TicketStore;
import com.iw.tms.fileStore.QiniuStore;
import com.iw.tms.service.TicketStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/ticketstore")
public class TicketStoreController {
    // TODO: 2018/5/28 0028 完成售票点营业执照和法人身份证使用7牛存储
    // TODO: 2018/5/28 0028 完成7牛存储的回显
    // TODO: 2018/5/28 0028 完成新增售票点功能
    // TODO: 2018/5/28 0028 完成售票点首页显示功能
    // TODO: 2018/5/28 0028 完成售票点动态搜索功能
    // TODO: 2018/5/28 0028 完成售票点编辑功能
    // TODO: 2018/5/29 0029 完成售票点查看功能,调用7牛的远程服务
    // TODO: 2018/5/29 0029 完成售票点编辑功能

    
    @Autowired
    private TicketStoreService ticketStoreService;
    @Autowired
    private QiniuStore qiniuStore;

    /**
     * 跳转到售票点管理首页
     * @return
     */
    @GetMapping()
    public String home(Model model,
                       @RequestParam(name = "p",required = false,defaultValue = "1") Integer pageNo,
                       @RequestParam(required = false,defaultValue = "") String storeName,
                       @RequestParam(required = false,defaultValue = "") String storeManager,
                       @RequestParam(required = false,defaultValue = "") String storeTel){

        //完成动态搜索功能,可选值: 1.售票点名称 2.售票点电话 3,售票点法人名字
        Map<String,Object> queryParam = Maps.newHashMap();
        queryParam.put("storeName",storeName);
        queryParam.put("storeManager",storeManager);
        queryParam.put("storeTel",storeTel);

        //根据动态查询参数和页号去查询
        PageInfo<TicketStore> pageInfo = ticketStoreService.selectAllTicketStoreByPage(pageNo,queryParam);

        model.addAttribute("pageInfo",pageInfo);

        return "store/home";
    }

    /**
     * 新增售票点
     * @param model
     * @return
     */
    @GetMapping("/new")
    public String insertTicketStore(Model model){
        String uploadToken = qiniuStore.getUploadToken();
        model.addAttribute("upToken",uploadToken);
        return "store/new";
    }

    /**
     * 添加售票点
     * @param ticketStore
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/new")
    public String insertTicketStore(TicketStore ticketStore, RedirectAttributes redirectAttributes){
        ticketStoreService.insertTicketStore(ticketStore);
        redirectAttributes.addFlashAttribute("message","新增成功");

        return "redirect:/ticketstore";
    }

    /**
     * 查看售票点信息
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{id:\\d+}")
    public String showTicketStore(@PathVariable Integer id,
                                  Model model){
        //如果售票点存在的话
        TicketStore ticketStore = ticketStoreService.selectByStoreAccountId(id);

        if (ticketStore==null){
            throw new ServiceException("账号不存在");
        }

        //根据售票点id 查找对应的售票点账号,传到前端去
        StoreAccount storeAccount = ticketStoreService.selectStoreAccountByTicketStoreId(ticketStore.getId());
        model.addAttribute("storeAccount",storeAccount);
        model.addAttribute("ticketStore",ticketStore);
        return "store/info";
    }

    /**
     * 跳转到修改账户首页,携带数据
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{id:\\d+}/edit")
    public String updateTicketStore(@PathVariable Integer id,
                                    Model model){
        TicketStore ticketStore = ticketStoreService.selectTicketStoreById(id);
        if (ticketStore==null){
            throw new ServiceException("账号不存在");
        }
        //获取七牛上传的Token
        String uploadToken = qiniuStore.getUploadToken();

        model.addAttribute("uploadToken",uploadToken);
        model.addAttribute("ticketStore",ticketStore);
        return "store/edit";
    }

    @PostMapping("/{id:\\d+}/edit")
    public String updateTicketStore(TicketStore ticketStore,
                                    RedirectAttributes redirectAttributes){

        ticketStoreService.updateTicketStore(ticketStore);
        redirectAttributes.addFlashAttribute("message","修改成功");
        return "redirect:/ticketstore";
    }
}
