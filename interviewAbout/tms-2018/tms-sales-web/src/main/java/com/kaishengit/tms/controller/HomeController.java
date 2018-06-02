package com.kaishengit.tms.controller;

import com.kaishengit.tms.entity.TicketStore;
import com.kaishengit.tms.service.AccountService;
import com.kaishengit.tms.service.TicketService;
import com.kaishengit.tms.shiro.ShiroUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 首页及登录、登出的控制器
 * @author fankay
 */
@Controller
public class HomeController {

    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private TicketService ticketService;
    @Autowired
    private ShiroUtil shiroUtil;

    /**
     * 系统登录页面
     * @return
     */
    @GetMapping("/")
    public String index() {
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

    /**
     * 系统登录
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

    /**
     * 登录后的首页
     * @return
     */
    @GetMapping("/home")
    public String home(Model model) {
        TicketStore ticketStore = shiroUtil.getCurrentAccount();
        //查询当前售票点库存年票数量和已售出年票数量
        Map<String,Long> countResult = ticketService.countTicketByStateAndStoreAccountId(ticketStore.getId());

        model.addAttribute("resultMap",countResult);
        return "home";
    }

    @GetMapping("/401")
    public String unauthorizedUrl() {
        return "error/401";
    }

}
