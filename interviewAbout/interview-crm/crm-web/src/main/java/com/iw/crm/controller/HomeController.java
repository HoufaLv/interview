package com.iw.crm.controller;

import com.iw.crm.exception.AuthenticationException;
import com.iw.crm.service.AccountService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    // TODO: 2018/6/1 0001 使用shiro 框架完成登陆验证
    // TODO: 2018/6/1 0001 完成用户主动回到登陆页面踢出认证


    @Autowired
    private AccountService accountService;


    /**
     * 来到登陆的首页
     *
     * @return
     */
    @GetMapping("/")
    public String home() {
        //如果用户来到这个页面,并且携带认证信息,则退出该账号,如果用户点击了记住我,则登陆成功直接跳转到home页面
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }

        if (subject.isRemembered()) {
            return "redirect:/";
        }

        return "index";
    }


    /**
     * 用户登陆
     *
     * @param accountMobile
     * @param accountPassword
     * @param rememberMe
     * @param httpServletRequest
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/")
    public String home(String accountMobile,
                       String accountPassword,
                       String rememberMe,
                       HttpServletRequest httpServletRequest,
                       RedirectAttributes redirectAttributes) {

        //登陆成功之后,跳转到home页面去
        String url = "/home";
        Subject subject = SecurityUtils.getSubject();

        //如果需要记录日志的话,可以在这里通过 httpServletRequest.getRemoteAddr(); 来获取登陆的ip记录日志
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(accountMobile, DigestUtils.md5Hex(accountPassword), rememberMe != null);

        //登陆
        try {
            subject.login(usernamePasswordToken);

            //如果登陆前有访问url,跳转到登陆前的url,否则跳转到/home去
            SavedRequest savedRequest = WebUtils.getSavedRequest(httpServletRequest);
            if (savedRequest != null) {
                url = savedRequest.getRequestURI();
            }

            return "redirect:" + url;
        } catch (UnknownAccountException | IncorrectCredentialsException ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "账号或密码错误");
        } catch (LockedAccountException ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "账号被锁定");
        } catch (AuthenticationException ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "账号或密码错误");
        }

        //登陆失败,跳转到home页面去
        return "redirect:/";
    }

    /**
     * 登陆成功之后跳转的页面
     * @return
     */
    @GetMapping("/home")
    public String shiroSuccessUrl(){
        return "home";
    }

    @GetMapping("/401")
    public String errorPage(){
        return "error/401";
    }
}
