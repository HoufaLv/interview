package com.iw.tms.controller;

import com.iw.tms.entity.Account;
import com.iw.tms.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 首页以及登陆登出的控制器
 */
@Controller
public class HomeController {

    // TODO: 2018/5/15 0015 完成登陆的get请求和post请求,完成错误信息,手机号回显

    @Autowired
    private AccountService accountService;

    /**
     * 如果用户请求 / 路径,则跳转到index.jsp页面
     *
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "index";
    }

    /**
     * 综合管理系统-登陆
     * @param accountMobile      手机号
     * @param accountPassword    密码
     * @param httpServletRequest
     * @param session
     * @return
     */
    @PostMapping("/")
    public String login(String accountMobile,
                        String accountPassword,
                        HttpServletRequest httpServletRequest,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {

        String remoteAddr = httpServletRequest.getRemoteAddr();
        try {
            Account account = accountService.login(accountMobile, accountPassword,remoteAddr);
            session.setAttribute("currentAccount",account);

            return "redirect:/home";
        } catch (Exception e) {
            // 再页面上回显message 信息和 手机号相关
            redirectAttributes.addFlashAttribute("message",e.getMessage());
            redirectAttributes.addFlashAttribute("accountMobile", accountMobile);
            return "redirect:/";
        }
    }

    /**
     * goto home page
     * @return
     */
    @GetMapping("/home")
    public String method() {
        return "home";
    }
}
