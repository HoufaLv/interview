package com.iw.tms.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class AccountRolesKey implements Serializable {
    private Integer tAccountId;

    private Integer tRolesId;

    private static final long serialVersionUID = 1L;

    public Integer gettAccountId() {
        return tAccountId;
    }

    public void settAccountId(Integer tAccountId) {
        this.tAccountId = tAccountId;
    }

    public Integer gettRolesId() {
        return tRolesId;
    }

    public void settRolesId(Integer tRolesId) {
        this.tRolesId = tRolesId;
    }
}