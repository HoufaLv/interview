package com.iw.tms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Ticket implements Serializable {
    private Integer id;

    private String ticketNum;

    private Date ticketInTime;

    private String ticketState;

    private Date createTime;

    private Date updateTime;

    private String ticketOutTime;

    private Integer storeAccountId;

    private Date ticketValidityEnd;

    private Long customerId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(String ticketNum) {
        this.ticketNum = ticketNum;
    }

    public Date getTicketInTime() {
        return ticketInTime;
    }

    public void setTicketInTime(Date ticketInTime) {
        this.ticketInTime = ticketInTime;
    }

    public String getTicketState() {
        return ticketState;
    }

    public void setTicketState(String ticketState) {
        this.ticketState = ticketState;
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

    public String getTicketOutTime() {
        return ticketOutTime;
    }

    public void setTicketOutTime(String ticketOutTime) {
        this.ticketOutTime = ticketOutTime;
    }

    public Integer getStoreAccountId() {
        return storeAccountId;
    }

    public void setStoreAccountId(Integer storeAccountId) {
        this.storeAccountId = storeAccountId;
    }

    public Date getTicketValidityEnd() {
        return ticketValidityEnd;
    }

    public void setTicketValidityEnd(Date ticketValidityEnd) {
        this.ticketValidityEnd = ticketValidityEnd;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}