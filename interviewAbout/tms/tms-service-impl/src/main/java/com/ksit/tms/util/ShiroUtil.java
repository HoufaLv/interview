package com.ksit.tms.util;

import com.ksit.tms.entity.Account;
import com.ksit.tms.service.AccountService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShiroUtil {

    @Autowired
    private AccountService accountService;

    public static Account getCurrAccount(){
        Subject subject = SecurityUtils.getSubject();
        return (Account) subject.getPrincipal();
    }
}
