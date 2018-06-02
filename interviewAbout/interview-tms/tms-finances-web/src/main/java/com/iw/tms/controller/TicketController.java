package com.iw.tms.controller;

import com.iw.tms.entity.TicketInRecord;
import com.iw.tms.service.TicketService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    // TODO: 2018/5/29 0029 完成年票入库,就是将一条ticketInRecord 记录保存到数据库中去


    @Autowired
    private TicketService ticketService;

    /**
     * 年票入库首页
     * @return
     */
    @GetMapping("/storage")
    public String ticketIn() {
        return "ticket/storage/home";
    }

    /**
     * 新增入库
     * @return
     */
    @GetMapping("/storage/new")
    public String newTicketStorage(Model model) {
        //年票入库需要遵从服务器时间,不能使用客户端时间
        //在后台将时间设置好,传到前端去
        String today = DateTime.now().toString("YYYY-MM-dd");
        model.addAttribute("today",today);
        return "ticket/storage/new";
    }

    /**
     * 新增年票入库
     * @param ticketInRecord
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/storage/new")
    public String newTicketStorage(TicketInRecord ticketInRecord, RedirectAttributes redirectAttributes) {
        ticketService.saveTicketInRecord(ticketInRecord);

        redirectAttributes.addFlashAttribute("message","新增成功");
        return "redirect:/ticket/storage";
    }
}
