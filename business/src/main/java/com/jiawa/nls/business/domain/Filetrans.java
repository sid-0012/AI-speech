package com.jiawa.nls.business.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Filetrans {
    private Long id;

    private Long memberId;

    private String name;

    private Integer second;

    private BigDecimal amount;

    private String audio;

    private String fileSign;

    private String payStatus;

    private String status;

    private String lang;

    private String vod;

    private String taskId;

    private Integer transStatusCode;

    private String transStatusText;

    private Date transTime;

    private Date solveTime;

    private Date createdAt;

    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getFileSign() {
        return fileSign;
    }

    public void setFileSign(String fileSign) {
        this.fileSign = fileSign;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getVod() {
        return vod;
    }

    public void setVod(String vod) {
        this.vod = vod;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Integer getTransStatusCode() {
        return transStatusCode;
    }

    public void setTransStatusCode(Integer transStatusCode) {
        this.transStatusCode = transStatusCode;
    }

    public String getTransStatusText() {
        return transStatusText;
    }

    public void setTransStatusText(String transStatusText) {
        this.transStatusText = transStatusText;
    }

    public Date getTransTime() {
        return transTime;
    }

    public void setTransTime(Date transTime) {
        this.transTime = transTime;
    }

    public Date getSolveTime() {
        return solveTime;
    }

    public void setSolveTime(Date solveTime) {
        this.solveTime = solveTime;
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
        sb.append(", memberId=").append(memberId);
        sb.append(", name=").append(name);
        sb.append(", second=").append(second);
        sb.append(", amount=").append(amount);
        sb.append(", audio=").append(audio);
        sb.append(", fileSign=").append(fileSign);
        sb.append(", payStatus=").append(payStatus);
        sb.append(", status=").append(status);
        sb.append(", lang=").append(lang);
        sb.append(", vod=").append(vod);
        sb.append(", taskId=").append(taskId);
        sb.append(", transStatusCode=").append(transStatusCode);
        sb.append(", transStatusText=").append(transStatusText);
        sb.append(", transTime=").append(transTime);
        sb.append(", solveTime=").append(solveTime);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append("]");
        return sb.toString();
    }
}