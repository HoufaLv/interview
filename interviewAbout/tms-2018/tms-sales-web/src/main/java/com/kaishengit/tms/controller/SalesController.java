package com.kaishengit.tms.controller;

import com.kaishengit.tms.fileStore.FastDfsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ticket")
public class SalesController {

    @Autowired
    private FastDfsUtil fastDfsUtil;

    /**
     * 年票办理
     * @return
     */
    @GetMapping("/sales/new")
    public String salesNewTicket() {
        return "ticket/sales";
    }

}
