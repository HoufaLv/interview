package com.ksit.tms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class AccountLoginLog implements Serializable {
    private Long id;

    /**
     * ip of login
     */
    private String loginIp;

    /**
     * time of login
     */
    private Date loginTime;

    /**
     * FKR of account/account_login_log
     */
    private Integer accountId;

    private static final long serialVersionUID = 1L;

    public AccountLoginLog() { }

    public AccountLoginLog(String loginIp, Date loginTime, Integer accountId) {
        this.loginIp = loginIp;
        this.loginTime = loginTime;
        this.accountId = accountId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}