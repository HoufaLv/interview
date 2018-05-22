package com.iw.crm.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class AccountDeptKey implements Serializable {
    private Integer accountId;

    private Integer deptId;

    private static final long serialVersionUID = 1L;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
}