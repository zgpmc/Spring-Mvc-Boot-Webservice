package com.example.demo.model;

import java.math.BigDecimal;
import java.util.Date;

public class POS_INFO {
    private Integer posId;

    private String posRatio;

    private BigDecimal minAmount;

    private BigDecimal maxAmount;

    private String platForm;

    private String partnerName;

    private String partnerPhone;

    private String posState;

    private Date makedate;

    private Date modifydate;

    private String operator;

    public POS_INFO(Integer posId, String posRatio, BigDecimal minAmount, BigDecimal maxAmount, String platForm, String partnerName, String partnerPhone, String posState, Date makedate, Date modifydate, String operator) {
        this.posId = posId;
        this.posRatio = posRatio;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.platForm = platForm;
        this.partnerName = partnerName;
        this.partnerPhone = partnerPhone;
        this.posState = posState;
        this.makedate = makedate;
        this.modifydate = modifydate;
        this.operator = operator;
    }

    public POS_INFO() {
        super();
    }

    public Integer getPosId() {
        return posId;
    }

    public void setPosId(Integer posId) {
        this.posId = posId;
    }

    public String getPosRatio() {
        return posRatio;
    }

    public void setPosRatio(String posRatio) {
        this.posRatio = posRatio == null ? null : posRatio.trim();
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String getPlatForm() {
        return platForm;
    }

    public void setPlatForm(String platForm) {
        this.platForm = platForm == null ? null : platForm.trim();
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName == null ? null : partnerName.trim();
    }

    public String getPartnerPhone() {
        return partnerPhone;
    }

    public void setPartnerPhone(String partnerPhone) {
        this.partnerPhone = partnerPhone == null ? null : partnerPhone.trim();
    }

    public String getPosState() {
        return posState;
    }

    public void setPosState(String posState) {
        this.posState = posState == null ? null : posState.trim();
    }

    public Date getMakedate() {
        return makedate;
    }

    public void setMakedate(Date makedate) {
        this.makedate = makedate;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }
}