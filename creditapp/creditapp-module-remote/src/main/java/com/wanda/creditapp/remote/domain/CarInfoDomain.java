package com.wanda.creditapp.remote.domain;

import java.util.Date;

import com.wanda.creditapp.common.domain.BaseDomain;

public class CarInfoDomain extends BaseDomain {
    
	private static final long serialVersionUID = 1L;

	/**
     * 姓名
     */
    private String name;

    /**
     * 身份证号码
     */
    private String cardNo;

    /**
     * 车牌号码
     */
    private String licenseNo;

    /**
     * 号牌种类
     */
    private Integer carType;

    /**
     * 车架号
     */
    private String carCode;

    /**
     * 完整的17位车架号
     */
    private String vin_17;

    /**
     * 初次登记日期
     */
    private Date registerTime;

    /**
     * 车辆状态
     */
    private Integer carStatus;

    /**
     * 状态
     */
    private Integer dataStatus;

    /**
     * 版本
     */
    private Integer dataVersion;

    /**
     * 机动车信息
     */
    private String carDetail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo == null ? null : licenseNo.trim();
    }

    public Integer getCarType() {
        return carType;
    }

    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    public String getCarCode() {
        return carCode;
    }

    public void setCarCode(String carCode) {
        this.carCode = carCode == null ? null : carCode.trim();
    }

    public String getVin_17() {
        return vin_17;
    }

    public void setVin_17(String vin_17) {
        this.vin_17 = vin_17 == null ? null : vin_17.trim();
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Integer getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(Integer carStatus) {
        this.carStatus = carStatus;
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

    public String getCarDetail() {
        return carDetail;
    }

    public void setCarDetail(String carDetail) {
        this.carDetail = carDetail == null ? null : carDetail.trim();
    }
}