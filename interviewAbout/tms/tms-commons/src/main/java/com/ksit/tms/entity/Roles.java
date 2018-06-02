package com.ksit.tms.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author 
 */
public class Roles implements Serializable {
    private Integer id;

    /**
     * 角色名称
     */
    private String rolesName;

    /**
     * 角色代码
     */
    private String rolesCode;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 在roles 里面封装一个Permission List 选项,用于在页面上显示权限列表
     */
    private List<Permission> permissionList;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", rolesName='" + rolesName + '\'' +
                ", rolesCode='" + rolesCode + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", permissionList=" + permissionList +
                '}';
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolesName() {
        return rolesName;
    }

    public void setRolesName(String rolesName) {
        this.rolesName = rolesName;
    }

    public String getRolesCode() {
        return rolesCode;
    }

    public void setRolesCode(String rolesCode) {
        this.rolesCode = rolesCode;
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
}