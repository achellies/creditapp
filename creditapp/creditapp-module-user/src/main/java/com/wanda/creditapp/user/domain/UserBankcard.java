package com.wanda.creditapp.user.domain;

import com.wanda.creditapp.common.domain.BaseDomain;

public class UserBankcard extends BaseDomain {
	private static final long serialVersionUID = 1L;

	/**
     * 对应app_users的id
     */
    private Integer userId;

    /**
     * uap唯一标识
     */
    private String uapPwid;

    /**
     * 银行卡号
     */
    private String cardNo;

    /**
     * 持卡人姓名
     */
    private String ownerName;

    /**
     * 卡类型
     */
    private Integer cardType;

    /**
     * 银行预留手机号
     */
    private String bankMobileNum;

    /**
     * 发卡行编号
     */
    private String issuingBankCode;

    /**
     * 发卡行名称
     */
    private String issuingBankName;

    /**
     * 有效期(起始)格式:MMYY
     */
    private String validFrom;

    /**
     * 有效期(结束)格式:MMYY
     */
    private String validThru;

    /**
     * 银行卡校验码(卡背面的3位)
     */
    private String checkCode;

    /**
     * 外部编号
     */
    private String externalNumber;

    private Integer dataStatus;

    private Integer dataVersion;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUapPwid() {
        return uapPwid;
    }

    public void setUapPwid(String uapPwid) {
        this.uapPwid = uapPwid == null ? null : uapPwid.trim();
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName == null ? null : ownerName.trim();
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getBankMobileNum() {
        return bankMobileNum;
    }

    public void setBankMobileNum(String bankMobileNum) {
        this.bankMobileNum = bankMobileNum == null ? null : bankMobileNum.trim();
    }

    public String getIssuingBankCode() {
        return issuingBankCode;
    }

    public void setIssuingBankCode(String issuingBankCode) {
        this.issuingBankCode = issuingBankCode == null ? null : issuingBankCode.trim();
    }

    public String getIssuingBankName() {
        return issuingBankName;
    }

    public void setIssuingBankName(String issuingBankName) {
        this.issuingBankName = issuingBankName == null ? null : issuingBankName.trim();
    }

    public String getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom == null ? null : validFrom.trim();
    }

    public String getValidThru() {
        return validThru;
    }

    public void setValidThru(String validThru) {
        this.validThru = validThru == null ? null : validThru.trim();
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode == null ? null : checkCode.trim();
    }

    public String getExternalNumber() {
        return externalNumber;
    }

    public void setExternalNumber(String externalNumber) {
        this.externalNumber = externalNumber == null ? null : externalNumber.trim();
    }

    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }

    public Integer getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(Integer dataVersion) {
        this.dataVersion = dataVersion;
    }
}