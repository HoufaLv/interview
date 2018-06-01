package com.iw.crm.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class Dept implements Serializable {

    public static final String TOP_DEPT = "1";
    private Integer id;

    private String deptName;

    private Integer pId;

    private static final long serialVersionUID = 1L;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }
}