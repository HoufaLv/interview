package com.ksit.tms.controller;

import com.github.pagehelper.PageInfo;
import com.ksit.tms.entity.TicketOutRecord;
import com.ksit.tms.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 财务相关的业务控制器
 */
@Controller()
@RequestMapping("/finance")
public class FinanceController {

    @Autowired
    TicketService ticketService;

    /**
     * 前往售票点缴费首页,
     * @param state
     * @param pageNo
     * @param model
     * @return
     */
    @GetMapping("/ticket")
    public String financeHome(@RequestParam(required = false,defaultValue = "未支付")String state,
                              @RequestParam(required = false,defaultValue = "1",name = "p")Integer pageNo,
                              Model model){
        // TODO: 2018/4/28 0028 给定默认支付状态 ,默认页码,跳转到财务的home页面去,其中页码和支付状态为可选值

        Map<String,Object> queryParam = new HashMap<>();
        queryParam.put("state",state);
        PageInfo<TicketOutRecord> pageInfo = ticketService.selectTicketOutRecordByParam(queryParam,pageNo);

        model.addAttribute("page",pageInfo);
        return "finance/ticket/home";
    }

    /**
     * 下发单支付
     * @return
     */
    @GetMapping("/ticket/{id:\\d+}/pay")
    public String pay(@PathVariable Integer id,Model model){
        // TODO: 2018/5/1 0001 完成售票点缴费,注意@Pathvirable 是获取重写url中的值
        // 去数据库中找到记录,将完整信息传到前端,前端接收到数据进行展示

        TicketOutRecord ticketOutRecord = ticketService.selectticketoutrecordbyId(id);
        model.addAttribute("record",ticketOutRecord);
        return "finance/ticket/pay";
    }

    @PostMapping("/ticket/{id:\\d+}/pay")
    public String pay(Integer id ,String payType){
        // TODO: 2018/5/2 0002 根据传过来的id和支付方式,将支付记录添加到数据库中
        ticketService.payTicketOutRecord(id,payType);
        return "redirect:/finance/ticket";
    }
}
