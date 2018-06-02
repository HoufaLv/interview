package com.iw.tms.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    // TODO: 2018/5/29 0029 完成财务系统登陆


    @GetMapping("/")
    public String index(){
        Subject subject = SecurityUtils.getSubject();

        //判断当前是否有已经认证的账号，如果有，则退出该账号
        if(subject.isAuthenticated()) {
            subject.logout();
        }

        if(subject.isRemembered()) {
            //如果当前为被记住（通过rememberMe认证），则直接跳转到登录成功页面 home
            return "redirect:/home";
        }

        return "index";
    }


    @PostMapping("/")
    public String login(String accountMobile,
                        String password,
                        String rememberMe,
                        HttpServletRequest request,
                        RedirectAttributes redirectAttributes){

        Subject subject = SecurityUtils.getSubject();

        //获取登陆的ip
        String remoteAddr = request.getRemoteAddr();

        //创建usernamePassword Token
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(accountMobile, DigestUtils.md5Hex(password), rememberMe != null,remoteAddr);

        try{
            subject.login(usernamePasswordToken);

            SavedRequest savedRequest = WebUtils.getSavedRequest(request);
            String url = "/home";
            if (savedRequest!=null){
                url=savedRequest.getRequestUrl();
            }

            return "redirect:" + url;
        }catch (UnknownAccountException | IncorrectCredentialsException ex) {
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



    /**
     * 登录后的首页
     * @return
     */
    @GetMapping("/home")
    public String home() {
        return "home";
    }

    /**
     * 错误页
     * @return
     */
    @GetMapping("/401")
    public String unauthorizedUrl() {
        return "error/401";
    }
}
