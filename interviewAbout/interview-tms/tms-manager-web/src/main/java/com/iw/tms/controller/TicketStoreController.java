package com.iw.tms.controller;

import com.iw.tms.entity.TicketStore;
import com.iw.tms.fileStore.QiniuStore;
import com.iw.tms.service.TicketStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ticketstore")
public class TicketStoreController {

    @Autowired
    private TicketStoreService ticketStoreService;
    @Autowired
    private QiniuStore qiniuStore;

    /**
     * 跳转到售票点管理首页
     * @return
     */
    @GetMapping()
    public String home(){
        return "store/home";
    }

    @GetMapping("/new")
    public String insertTicketStore(Model model){
        String uploadToken = qiniuStore.getUploadToken();
        model.addAttribute("upToken",uploadToken);
        return "store/new";
    }

    @PostMapping("/new")
    public String insertTicketStore(TicketStore ticketStore, RedirectAttributes redirectAttributes){
        ticketStoreService.insertTicketStore(ticketStore);
        redirectAttributes.addFlashAttribute("message","新增成功");

        return "redirect:/ticketstore";
    }
}
