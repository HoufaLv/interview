package com.kaishengit.tms.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 
 */
public class TicketOrder implements Serializable {

    public static final String ORDER_TYPE_NEW = "新办订单";
    public static final String ORDER_TYPE_RENEW = "续费订单";
    public static final String ORDER_TYPE_REPLACE = "补办订单";

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 流水号
     */
    private String ticketOrderNum;

    /**
     * 价格
     */
    private BigDecimal ticketOrderPrice;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 流水类型  新办 | 续费 | 补卡
     */
    private String ticketOrderType;

    /**
     * 售票点账户ID
     */
    private Integer storeAccountId;

    /**
     * 年票ID
     */
    private Long ticketId;

    /**
     * 顾客(年票购买者)ID
     */
    private Long customerId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTicketOrderNum() {
        return ticketOrderNum;
    }

    public void setTicketOrderNum(String ticketOrderNum) {
        this.ticketOrderNum = ticketOrderNum;
    }

    public BigDecimal getTicketOrderPrice() {
        return ticketOrderPrice;
    }

    public void setTicketOrderPrice(BigDecimal ticketOrderPrice) {
        this.ticketOrderPrice = ticketOrderPrice;
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

    public String getTicketOrderType() {
        return ticketOrderType;
    }

    public void setTicketOrderType(String ticketOrderType) {
        this.ticketOrderType = ticketOrderType;
    }

    public Integer getStoreAccountId() {
        return storeAccountId;
    }

    public void setStoreAccountId(Integer storeAccountId) {
        this.storeAccountId = storeAccountId;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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
        TicketOrder other = (TicketOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTicketOrderNum() == null ? other.getTicketOrderNum() == null : this.getTicketOrderNum().equals(other.getTicketOrderNum()))
            && (this.getTicketOrderPrice() == null ? other.getTicketOrderPrice() == null : this.getTicketOrderPrice().equals(other.getTicketOrderPrice()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getTicketOrderType() == null ? other.getTicketOrderType() == null : this.getTicketOrderType().equals(other.getTicketOrderType()))
            && (this.getStoreAccountId() == null ? other.getStoreAccountId() == null : this.getStoreAccountId().equals(other.getStoreAccountId()))
            && (this.getTicketId() == null ? other.getTicketId() == null : this.getTicketId().equals(other.getTicketId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTicketOrderNum() == null) ? 0 : getTicketOrderNum().hashCode());
        result = prime * result + ((getTicketOrderPrice() == null) ? 0 : getTicketOrderPrice().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getTicketOrderType() == null) ? 0 : getTicketOrderType().hashCode());
        result = prime * result + ((getStoreAccountId() == null) ? 0 : getStoreAccountId().hashCode());
        result = prime * result + ((getTicketId() == null) ? 0 : getTicketId().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ticketOrderNum=").append(ticketOrderNum);
        sb.append(", ticketOrderPrice=").append(ticketOrderPrice);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", ticketOrderType=").append(ticketOrderType);
        sb.append(", storeAccountId=").append(storeAccountId);
        sb.append(", ticketId=").append(ticketId);
        sb.append(", customerId=").append(customerId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}