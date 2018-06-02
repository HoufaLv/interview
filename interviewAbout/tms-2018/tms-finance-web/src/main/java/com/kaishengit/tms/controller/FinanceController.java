package com.kaishengit.tms.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.kaishengit.tms.entity.TicketOutRecord;
import com.kaishengit.tms.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/finance")
public class FinanceController {

    @Autowired
    private TicketService ticketService;


    @GetMapping("/ticket")
    public String ticketFinanceHome(@RequestParam(required = false,defaultValue = "未支付")String state,
                                    @RequestParam(required = false,defaultValue = "1",name = "p")Integer pageNO,
                                    Model model){

        Map<String,Object> queryParam = Maps.newHashMap();
        queryParam.put("state",state);

        PageInfo<TicketOutRecord> pageInfo = ticketService.findTicketOutRecordByPageNoAndQueryParam(pageNO,queryParam);
        model.addAttribute("pageInfo",pageInfo);
        return "finance/ticket/home";
    }

    @GetMapping("/ticket/{id:\\d+}/pay")
    public String payTicketFinance(@PathVariable Integer id, Model model){
        TicketOutRecord ticketOutRecord = ticketService.findTicketOutRecordById(id);
        model.addAttribute("record",ticketOutRecord);
        return "finance/ticket/pay";
    }

    @PostMapping("/ticket/{id:\\d+}/pay")
    public String payTicketFinance(Integer id, String payType, RedirectAttributes redirectAttributes){
        ticketService.payTicketOutRecord(id,payType);
        redirectAttributes.addFlashAttribute("message","Congratulations!新增成功!");
        return "redirect:/finance/ticket";
    }
}
