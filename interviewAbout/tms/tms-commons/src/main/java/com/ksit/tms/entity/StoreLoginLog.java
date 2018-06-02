package com.ksit.tms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class StoreLoginLog implements Serializable {
    private Integer id;

    /**
     * 登陆的ip
     */
    private String logIp;

    /**
     * 登陆时间
     */
    private Date logTime;

    private String storeLoginLogcol;

    private Integer storeAccountId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogIp() {
        return logIp;
    }

    public void setLogIp(String logIp) {
        this.logIp = logIp;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public String getStoreLoginLogcol() {
        return storeLoginLogcol;
    }

    public void setStoreLoginLogcol(String storeLoginLogcol) {
        this.storeLoginLogcol = storeLoginLogcol;
    }

    public Integer getStoreAccountId() {
        return storeAccountId;
    }

    public void setStoreAccountId(Integer storeAccountId) {
        this.storeAccountId = storeAccountId;
    }
}