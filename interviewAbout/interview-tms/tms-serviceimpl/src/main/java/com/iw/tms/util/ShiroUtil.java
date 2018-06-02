package com.iw.tms.util;

import com.iw.tms.entity.Account;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

/**
 * shiro 工具类
 *
 */
@Component
public class ShiroUtil {

    /**
     * 返回当前登陆的对象
     * @return
     */
    public Account getCurrentAccount(){
        Subject subject = SecurityUtils.getSubject();
        return (Account) subject.getPrincipal();
    }
}
