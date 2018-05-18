package com.iw.tms.controller;

import com.iw.tms.service.AccountService;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 首页以及登陆登出的控制器
 */
@Controller
public class HomeController {

    // TODO: 2018/5/15 0015 完成登陆的get请求和post请求

    @Autowired
    private AccountService accountService;



    /**
     * 如果用户请求 / 路径,则跳转到index.jsp页面
     * @return
     */
    @GetMapping("/")
    public String index(){
        return "index";
    }

    /**
     * 完成TMS 登陆功能
     * @param accountMobile         手机号
     * @param accountPassword       密码
     * @param httpServletRequest
     * @param session
     * @return
     */
    @PostMapping("/")
    public String login(String accountMobile, String accountPassword, HttpServletRequest httpServletRequest, HttpServletSession session){

        return null;
    }

}
