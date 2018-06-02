package com.ksit.tms.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class RolesPermissionKey implements Serializable {
    private Integer tRolesId;

    private Integer tPermissionId;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "RolesPermissionKey{" +
                "tRolesId=" + tRolesId +
                ", tPermissionId=" + tPermissionId +
                '}';
    }

    public Integer gettRolesId() {
        return tRolesId;
    }

    public void settRolesId(Integer tRolesId) {
        this.tRolesId = tRolesId;
    }

    public Integer gettPermissionId() {
        return tPermissionId;
    }

    public void settPermissionId(Integer tPermissionId) {
        this.tPermissionId = tPermissionId;
    }
}