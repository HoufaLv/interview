package com.ksit.tms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class StoreAccount implements Serializable {

    public static final String ACCOUNT_STATE_NORMAL = "正常";
    public static final String ACCOUNT_STATE_DISABLE = "禁用";

    private Integer id;

    /**
     * 售票点账户
     */
    private String storeAccount;

    /**
     * 售票点账户密码
     */
    private String storePassword;

    private Date createTime;

    /**
     * 创建时间
     */
    private String updateTime;

    /**
     * 账户状态
     */
    private String storeState;

    /**
     * 售票点账户和售票点的对应关系
     */
    private Integer ticketStoreId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStoreAccount() {
        return storeAccount;
    }

    public void setStoreAccount(String storeAccount) {
        this.storeAccount = storeAccount;
    }

    public String getStorePassword() {
        return storePassword;
    }

    public void setStorePassword(String storePassword) {
        this.storePassword = storePassword;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getStoreState() {
        return storeState;
    }

    public void setStoreState(String storeState) {
        this.storeState = storeState;
    }

    public Integer getTicketStoreId() {
        return ticketStoreId;
    }

    public void setTicketStoreId(Integer ticketStoreId) {
        this.ticketStoreId = ticketStoreId;
    }
}