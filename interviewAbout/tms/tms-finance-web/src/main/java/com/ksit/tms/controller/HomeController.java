package com.ksit.tms.controller;

import com.ksit.tms.service.AccountService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    private Logger logger = LoggerFactory.getLogger(HomeController.class);


    /**
     * 出现spring 不能注入Bean 的情况,首先考虑配置文件
     */
    @Autowired
    private AccountService accountService;

    /**
     * 系统登录`
     * @return
     */
    @PostMapping("/")
    public String login(String accountMobile,
                        String password,
                        String rememberMe,
                        HttpServletRequest request,
                        RedirectAttributes redirectAttributes) {

        // 创建Subject对象
        Subject subject = SecurityUtils.getSubject();
        // 根据账号和密码进行登录
        String requestIp = request.getRemoteAddr();
        UsernamePasswordToken usernamePasswordToken =
                new UsernamePasswordToken(accountMobile,DigestUtils.md5Hex(password),rememberMe!=null,requestIp);

        try {
            subject.login(usernamePasswordToken);

                //暂时不做权限校验,使用account 账户来登陆

                //登录后跳转目标的判断
                SavedRequest savedRequest = WebUtils.getSavedRequest(request);
                String url = "/home";
                if(savedRequest != null) {
                    url = savedRequest.getRequestUrl();
                }

                return "redirect:"+url;

        } catch (UnknownAccountException | IncorrectCredentialsException ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("message","账号或密码错误");
        } catch (LockedAccountException ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("message","账号被锁定");
        } catch (AuthenticationException ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("message","账号或密码错误");
        }
        return "redirect:/";
    }


    @GetMapping("/")
    public String index(){
        Subject subject = SecurityUtils.getSubject();

        //如果用户想要退出账号的话
        if (subject.isAuthenticated()){
            subject.logout();
        }

        //如果点击了记住我,则直接跳转到home 页面去
        if (subject.isRemembered()){
            return "redirect:/home";
        }

        //跳往index 页面去
        return "index";
    }


    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/401")
    public String unauthorizedUrl() {
        return "error/401";
    }

}
