package com.iw.tms.controller;

import com.iw.tms.entity.Account;
import com.iw.tms.service.AccountService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
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
 * 首页以及登陆登出的控制器
 */
@Controller
public class HomeController {

    // TODO: 2018/5/15 0015 完成登陆的get请求和post请求,完成错误信息,手机号回显
    // TODO: 2018/5/28 0028 完成使用shiro 框架登陆


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
     * @param
     * @return
     */
    @PostMapping("/")
    public String login(String accountMobile,
                        String accountPassword,
                        HttpServletRequest httpServletRequest,
                        String rememberMe,
                        RedirectAttributes redirectAttributes) {

        //获取subject 对象
        Subject subject = SecurityUtils.getSubject();

        //获取请求的ipAddress,ipV6 默认都是0
        String remoteAddr = httpServletRequest.getRemoteAddr();

        //创建UsernamePasswordToken
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(accountMobile,DigestUtils.md5Hex(accountPassword),rememberMe!=null,remoteAddr);

        try{
            //在这一步将前端传过来的账号密码封装成为一个UsernamePasswordToken 传给subject.login(); 方法,在内部会自动去登陆
            subject.login(usernamePasswordToken);
            //将登陆的对象放入session
            Account account = accountService.selectByMobile(accountMobile);

            Session session = subject.getSession();
            session.setAttribute("curr_account",account);

            //跳转到登陆前页面
            SavedRequest savedRequest = WebUtils.getSavedRequest(httpServletRequest);
            String url = "home";
            //如果登陆前有url,则跳转到登陆前的url
            if (savedRequest!=null){
                url = savedRequest.getRequestUrl();
            }

            return "redirect:/" + url;
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
      /*  String remoteAddr = httpServletRequest.getRemoteAddr();
        try {
            Account account = accountService.login(accountMobile, accountPassword,remoteAddr);
            session.setAttribute("currentAccount",account);

            return "redirect:/home";
        } catch (Exception e) {
            // 再页面上回显message 信息和 手机号相关
            redirectAttributes.addFlashAttribute("message",e.getMessage());
            redirectAttributes.addFlashAttribute("accountMobile", accountMobile);
            return "redirect:/";
        }*/
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
