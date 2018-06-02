package com.kaishengit.tms.controller;

import com.kaishengit.tms.service.AccountService;
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

/**
 *首页及登录 登出控制器
 * @author drm
 * @date 2018/4/13
 */
@Controller
public class HomeController {
    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private AccountService accountService;

    /**  
     *系统登录页面
     * @date 2018/4/13
     * @param
     * @return java.lang.String  
     */
    @GetMapping("/")
    public String index(){
        System.out.println("--------------------------------");
        Subject subject = SecurityUtils.getSubject();
        System.out.println("subject.isAuthenticated()"+subject.isAuthenticated());
        System.out.println("subject.isRemembered()"+subject.isRemembered());

        //判断当前是否有已经认证的账号，如果有，则退出该账号
        if (subject.isAuthenticated()){
            subject.logout();
        }

        if (subject.isRemembered()){
            //如果当前为被记住（通过rememberMe认证），则直接跳转到登录成功页面 home
            return "redirect:/home";
        }

        return "index";
    }


    /**  
     *系统登录
     * @date 2018/4/13
     * @param
     * @return java.lang.String  
     */
    @PostMapping("/")
    public String login(String accountMobile,
                        String password,
                        String rememberMe,
                        HttpServletRequest request,
                        RedirectAttributes redirectAttributes){

        //创建Subject对象
        Subject subject = SecurityUtils.getSubject();
        //根据账号密码进行登录
        String requestIp = request.getRemoteAddr();
        UsernamePasswordToken usernamePasswordToken =
                new UsernamePasswordToken(accountMobile, DigestUtils.md5Hex(password),rememberMe!=null,requestIp);

        try {
            subject.login(usernamePasswordToken);
            if (subject.hasRole("finance") || subject.hasRole("store")){

                //登录后跳转目标的判断
                SavedRequest savedRequest = WebUtils.getSavedRequest(request);
                String url = "/home";
                if (savedRequest!=null){
                    url = savedRequest.getRequestUrl();
                }

                return "redirect:"+url;
            }else{
                logger.info("{}没有权限登录该系统",accountMobile);
                redirectAttributes.addFlashAttribute("message","没有登录该系统的权限");
            }


        } catch (UnknownAccountException|IncorrectCredentialsException ex){
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("message","账号或密码错误");
        } catch (LockedAccountException ex){
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("message","账号被锁定");
        } catch (AuthenticationException ex){
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("message","账号或密码错误");
        }

        return "redirect:/";


       /* //获取登录的ip地址
        String requestIp = request.getRemoteAddr();
        try {
            Account account = accountService.login(accountMobile,password,requestIp);
            //将登录成功的对象放入session
            session.setAttribute("current_Account",account);
            //进入home页面
            return "redirect:/home";
        } catch (ServiceException ex){
            redirectAttributes.addFlashAttribute("phone",accountMobile);
            redirectAttributes.addFlashAttribute("message",ex.getMessage());
            return "redirect:/";
        }*/


    }


    /**  
     *登录后首页
     * @date 2018/4/13
     * @param
     * @return java.lang.String  
     */
    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/401")
    public String unauthorizedUrl(){
        return "error/401";
    }

  /*  @GetMapping("/logout")
    public String logout(RedirectAttributes redirectAttributes){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        redirectAttributes.addFlashAttribute("message","你已安全退出系统");
        return "redirect:/";
    }*/


}
