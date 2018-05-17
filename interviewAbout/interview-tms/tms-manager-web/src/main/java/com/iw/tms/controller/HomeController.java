package com.iw.tms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 首页以及登陆登出的控制器
 */
@Controller
public class HomeController {

    // TODO: 2018/5/15 0015 完成登陆的get请求和post请求

    /**
     * 如果用户请求 / 路径,则跳转到index.jsp页面
     * @return
     */
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/")
    public String login(){
        return null;
    }

}
