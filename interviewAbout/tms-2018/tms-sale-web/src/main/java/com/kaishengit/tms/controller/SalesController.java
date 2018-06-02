package com.kaishengit.tms.controller;

import com.kaishengit.tms.entity.Customer;
import com.kaishengit.tms.entity.TicketStore;
import com.kaishengit.tms.exception.ServiceException;
import com.kaishengit.tms.fileStore.FastDfsUtil;
import com.kaishengit.tms.service.TicketService;
import com.kaishengit.tms.shiro.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

@Controller
@RequestMapping("/ticket")
public class SalesController {

    @Autowired
    private ShiroUtil shiroUtil;
    @Autowired
    private TicketService ticketService;

    /**
     * 年票办理
     * @return
     */
    @GetMapping("/sales/new")
    public String salesNewTicket() {
        return "ticket/sales";
    }

    @PostMapping("/sales/new")
    public String salesNewTicket(Customer customer, String ticketNum, BigDecimal price,
                                 RedirectAttributes redirectAttributes) {

        //获取当前的售票点
        TicketStore ticketStore = shiroUtil.getCurrentAccount();

        try {
            //销售年票
            ticketService.salesTicket(customer,ticketNum,ticketStore,price);
            redirectAttributes.addFlashAttribute("message","开卡成功");

        } catch (ServiceException ex) {

            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("message",ex.getMessage());
        }


        return "redirect:/ticket/sales/new";
    }
}
