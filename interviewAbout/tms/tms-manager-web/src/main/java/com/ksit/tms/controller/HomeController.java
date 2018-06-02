package com.ksit.tms.controller;

import com.ksit.tms.service.AccountService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lvhoufa
 */
@Controller
public class HomeController {

    @Autowired
    private AccountService accountService;

    /**
     * 跳转到登陆页
     * @return
     */
    @GetMapping(value = "/")
    public String index() {
        //用户访问到登陆页面,就是要退出并切换账号了.
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            subject.logout();
        }
        if (subject.isRemembered()){
            return "redirect:/home";
        }
        return "account/login";
    }

    @PostMapping(value = "/")
    public String login(String mobile,
                        String passWord,
                        String rememberMe,
                        HttpServletRequest httpServletRequest,
                        RedirectAttributes redirectAttributes) {

        String requestHost = httpServletRequest.getRemoteAddr();

        //可以直接获取Subject 对象而不用 配置SecurityManager ,因为在配置文件中已经做了相关定义
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(mobile, DigestUtils.md5Hex(passWord), rememberMe != null, requestHost);
        try {


            subject.login(usernamePasswordToken);

            //获取请求路径,如果有的话,跳转到提出之前得页面
            String requestUrl = "home";
            SavedRequest request = WebUtils.getSavedRequest(httpServletRequest);

            if (request!=null){
                requestUrl = request.getRequestUrl();
                return "redirect:" + requestUrl;
            }

            //登陆成功,跳到home页面去
            return "home";
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

        //如果登陆不成功,则跳转到/ 页面去,重新去登陆
        return "redirect:/";
    }


    /**
     * 登陆成功后的页面
     * @return
     */
    @GetMapping(value = "/home")
    public String home(){
        return "home";
    }

    /**
     * 未授权页面
     * @return
     */
    @GetMapping(value = "/401")
    public String unauthorizedUrl(){
        return "exception/401";
    }


}
