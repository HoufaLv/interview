package com.iw.controller;

import com.iw.entity.Account;
import com.iw.mapper.AccountMapper;
import com.iw.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class AccountController {

    // TODO: 2018/5/18 0018 前后端分离完成登陆

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMapper accountMapper;

    @PostMapping
    public String login(@RequestBody  Account account){
        
        return null;
    }

}
