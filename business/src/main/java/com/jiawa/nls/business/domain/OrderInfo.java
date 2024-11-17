package com.jiawa.nls.business.domain;

import java.math.BigDecimal;
import java.util.Date;

public class OrderInfo {
    private Long id;

    private String orderNo;

    private Date orderAt;

    private String orderType;

    private String info;

    private Long memberId;

    private BigDecimal amount;

    private Date payAt;

    private String channel;

    private Date channelAt;

    private String status;

    private String desc;

    private Date createdAt;

    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderAt() {
        return orderAt;
    }

    public void setOrderAt(Date orderAt) {
        this.orderAt = orderAt;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getPayAt() {
        return payAt;
    }

    public void setPayAt(Date payAt) {
        this.payAt = payAt;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Date getChannelAt() {
        return channelAt;
    }

    public void setChannelAt(Date channelAt) {
        this.channelAt = channelAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", orderAt=").append(orderAt);
        sb.append(", orderType=").append(orderType);
        sb.append(", info=").append(info);
        sb.append(", memberId=").append(memberId);
        sb.append(", amount=").append(amount);
        sb.append(", payAt=").append(payAt);
        sb.append(", channel=").append(channel);
        sb.append(", channelAt=").append(channelAt);
        sb.append(", status=").append(status);
        sb.append(", desc=").append(desc);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append("]");
        return sb.toString();
    }
}