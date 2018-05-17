package com.iw.tms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class TicketStore implements Serializable {
    private Integer id;

    /**
     * 售票点名称
     */
    private String storeName;

    /**
     * 售票点地址
     */
    private String storeAddress;

    /**
     * 售票点电话
     */
    private String storeTel;

    /**
     * 售票点法人
     */
    private String storeManager;

    /**
     * 售票点注册时间
     */
    private Date storeRegisterTime;

    /**
     * 售票点注销时间
     */
    private Date storeCloseTime;

    /**
     * 售票点营业执照
     */
    private String storeIdentity;

    /**
     * 售票点法人身份证
     */
    private String storeManagerIdentity;

    /**
     * 售票点经度
     */
    private String storeLongitude;

    /**
     * 售票点纬度
     */
    private String storeLatitude;

    private Integer storeAccountId;

    private static final long serialVersionUID = 1L;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreTel() {
        return storeTel;
    }

    public void setStoreTel(String storeTel) {
        this.storeTel = storeTel;
    }

    public String getStoreManager() {
        return storeManager;
    }

    public void setStoreManager(String storeManager) {
        this.storeManager = storeManager;
    }

    public Date getStoreRegisterTime() {
        return storeRegisterTime;
    }

    public void setStoreRegisterTime(Date storeRegisterTime) {
        this.storeRegisterTime = storeRegisterTime;
    }

    public Date getStoreCloseTime() {
        return storeCloseTime;
    }

    public void setStoreCloseTime(Date storeCloseTime) {
        this.storeCloseTime = storeCloseTime;
    }

    public String getStoreIdentity() {
        return storeIdentity;
    }

    public void setStoreIdentity(String storeIdentity) {
        this.storeIdentity = storeIdentity;
    }

    public String getStoreManagerIdentity() {
        return storeManagerIdentity;
    }

    public void setStoreManagerIdentity(String storeManagerIdentity) {
        this.storeManagerIdentity = storeManagerIdentity;
    }

    public String getStoreLongitude() {
        return storeLongitude;
    }

    public void setStoreLongitude(String storeLongitude) {
        this.storeLongitude = storeLongitude;
    }

    public String getStoreLatitude() {
        return storeLatitude;
    }

    public void setStoreLatitude(String storeLatitude) {
        this.storeLatitude = storeLatitude;
    }

    public Integer getStoreAccountId() {
        return storeAccountId;
    }

    public void setStoreAccountId(Integer storeAccountId) {
        this.storeAccountId = storeAccountId;
    }
}