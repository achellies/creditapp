package com.wanda.creditapp.user.domain;

import com.wanda.creditapp.common.domain.BaseDomain;
import java.util.Date;

public class UserIdcard extends BaseDomain {
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
     * 姓名
     */
    private String name;

    /**
     * 身份证号
     */
    private String idnumber;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 民族
     */
    private String nation;

    /**
     * 地址
     */
    private String address;

    /**
     * 签发机关
     */
    private String issuingAuthority;

    /**
     * 有效期限(起始)格式:yyyyMMdd
     */
    private Date validFrom;

    /**
     * 有效期限(结束)格式:yyyyMMdd
     */
    private Date validThru;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber == null ? null : idnumber.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getIssuingAuthority() {
        return issuingAuthority;
    }

    public void setIssuingAuthority(String issuingAuthority) {
        this.issuingAuthority = issuingAuthority == null ? null : issuingAuthority.trim();
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidThru() {
        return validThru;
    }

    public void setValidThru(Date validThru) {
        this.validThru = validThru;
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