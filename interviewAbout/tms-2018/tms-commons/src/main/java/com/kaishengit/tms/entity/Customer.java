package com.kaishengit.tms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Customer implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 姓名
     */
    private String customerName;

    /**
     * 身份证号
     */
    private String customerIdCard;

    /**
     * 身份证正面照片
     */
    private String customerIdCardPhoto;

    /**
     * 身份证反面照片
     */
    private String customerIdCardPhotoBack;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 一寸免冠照片
     */
    private String customerPhoto;

    /**
     * 电话
     */
    private String customerTel;

    /**
     * 地址
     */
    private String customerAddress;

    /**
     * 年龄
     */
    private Integer customerAge;

    /**
     * 性别
     */
    private String customerGender;

    /**
     * 年票ID
     */
    private Long ticketId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getCustomerIdCardPhotoBack() {
        return customerIdCardPhotoBack;
    }

    public void setCustomerIdCardPhotoBack(String customerIdCardPhotoBack) {
        this.customerIdCardPhotoBack = customerIdCardPhotoBack;
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

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Customer other = (Customer) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCustomerName() == null ? other.getCustomerName() == null : this.getCustomerName().equals(other.getCustomerName()))
            && (this.getCustomerIdCard() == null ? other.getCustomerIdCard() == null : this.getCustomerIdCard().equals(other.getCustomerIdCard()))
            && (this.getCustomerIdCardPhoto() == null ? other.getCustomerIdCardPhoto() == null : this.getCustomerIdCardPhoto().equals(other.getCustomerIdCardPhoto()))
            && (this.getCustomerIdCardPhotoBack() == null ? other.getCustomerIdCardPhotoBack() == null : this.getCustomerIdCardPhotoBack().equals(other.getCustomerIdCardPhotoBack()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCustomerPhoto() == null ? other.getCustomerPhoto() == null : this.getCustomerPhoto().equals(other.getCustomerPhoto()))
            && (this.getCustomerTel() == null ? other.getCustomerTel() == null : this.getCustomerTel().equals(other.getCustomerTel()))
            && (this.getCustomerAddress() == null ? other.getCustomerAddress() == null : this.getCustomerAddress().equals(other.getCustomerAddress()))
            && (this.getCustomerAge() == null ? other.getCustomerAge() == null : this.getCustomerAge().equals(other.getCustomerAge()))
            && (this.getCustomerGender() == null ? other.getCustomerGender() == null : this.getCustomerGender().equals(other.getCustomerGender()))
            && (this.getTicketId() == null ? other.getTicketId() == null : this.getTicketId().equals(other.getTicketId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCustomerName() == null) ? 0 : getCustomerName().hashCode());
        result = prime * result + ((getCustomerIdCard() == null) ? 0 : getCustomerIdCard().hashCode());
        result = prime * result + ((getCustomerIdCardPhoto() == null) ? 0 : getCustomerIdCardPhoto().hashCode());
        result = prime * result + ((getCustomerIdCardPhotoBack() == null) ? 0 : getCustomerIdCardPhotoBack().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCustomerPhoto() == null) ? 0 : getCustomerPhoto().hashCode());
        result = prime * result + ((getCustomerTel() == null) ? 0 : getCustomerTel().hashCode());
        result = prime * result + ((getCustomerAddress() == null) ? 0 : getCustomerAddress().hashCode());
        result = prime * result + ((getCustomerAge() == null) ? 0 : getCustomerAge().hashCode());
        result = prime * result + ((getCustomerGender() == null) ? 0 : getCustomerGender().hashCode());
        result = prime * result + ((getTicketId() == null) ? 0 : getTicketId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", customerName=").append(customerName);
        sb.append(", customerIdCard=").append(customerIdCard);
        sb.append(", customerIdCardPhoto=").append(customerIdCardPhoto);
        sb.append(", customerIdCardPhotoBack=").append(customerIdCardPhotoBack);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", customerPhoto=").append(customerPhoto);
        sb.append(", customerTel=").append(customerTel);
        sb.append(", customerAddress=").append(customerAddress);
        sb.append(", customerAge=").append(customerAge);
        sb.append(", customerGender=").append(customerGender);
        sb.append(", ticketId=").append(ticketId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}