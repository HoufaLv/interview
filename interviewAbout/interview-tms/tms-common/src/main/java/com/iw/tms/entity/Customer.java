package com.iw.tms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Customer implements Serializable {
    private Integer id;

    private String customerName;

    /**
     * 身份证号
     */
    private String customerIdCard;

    private String customerIdCardPhoto;

    /**
     * 身份证背面照片
     */
    private String customerIdCardBack;

    private Date createTime;

    private Date updateTime;

    /**
     * 客户的一寸照片
     */
    private String customerPhoto;

    private String customerTel;

    /**
     * 客户的地址
     */
    private String customerAddress;

    /**
     * 年票id
     */
    private Long ticketId;

    private Integer customerAge;

    /**
     * “”性别
     */
    private String customerGender;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerIdCard() {
        return customerIdCard;
    }

    public void setCustomerIdCard(String customerIdCard) {
        this.customerIdCard = customerIdCard;
    }

    public String getCustomerIdCardPhoto() {
        return customerIdCardPhoto;
    }

    public void setCustomerIdCardPhoto(String customerIdCardPhoto) {
        this.customerIdCardPhoto = customerIdCardPhoto;
    }

    public String getCustomerIdCardBack() {
        return customerIdCardBack;
    }

    public void setCustomerIdCardBack(String customerIdCardBack) {
        this.customerIdCardBack = customerIdCardBack;
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

    public String getCustomerPhoto() {
        return customerPhoto;
    }

    public void setCustomerPhoto(String customerPhoto) {
        this.customerPhoto = customerPhoto;
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(Integer customerAge) {
        this.customerAge = customerAge;
    }

    public String getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(String customerGender) {
        this.customerGender = customerGender;
    }
}