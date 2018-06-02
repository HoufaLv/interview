package com.ksit.tms.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author
 */
public class Account implements Serializable {

    public static final String ACCOUNT_NORMAL = "正常";
    public static final String ACCOUNT_LOCKED = "锁定";
    public static final String ACCOUNT_FORBIDDEN = "禁用";
    public static final String PAASWORD = "000000";


    private Integer id;

    /**
     * name of account
     */
    private String accountName;

    /**
     * mobile of account
     */
    private String accountMobile;

    /**
     * password of account
     */
    private String accountPassword;

    private Date createTime;

    private Date updateTime;

    private String accountState;

    private List<Roles> rolesList;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountName='" + accountName + '\'' +
                ", accountMobile='" + accountMobile + '\'' +
                ", accountPassword='" + accountPassword + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", accountState='" + accountState + '\'' +
                ", rolesList=" + rolesList +
                '}';
    }

    private static final long serialVersionUID = 1L;

    public List<Roles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList) {
        this.rolesList = rolesList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountMobile() {
        return accountMobile;
    }

    public void setAccountMobile(String accountMobile) {
        this.accountMobile = accountMobile;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAccountState() {
        return accountState;
    }

    public void setAccountState(String accountState) {
        this.accountState = accountState;
    }
}