package com.wanda.creditapp.remote.domain;

import com.wanda.creditapp.common.domain.BaseDomain;

public class CarIllegalDomain extends BaseDomain {
    
	private static final long serialVersionUID = 1L;

	/**
     * 车牌号
     */
    private String carNumber;

    /**
     * 车辆类型
     */
    private Integer carType;

    /**
     * 车架号
     */
    private String carCode;

    /**
     * 发动机号
     */
    private String carEngine;

    /**
     * 查询车辆后的状态
     */
    private String carStatus;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 状态
     */
    private Integer dataStatus;

    /**
     * 版本
     */
    private Integer dataVersion;

    /**
     * 违章信息
     */
    private String carIllegals;

    
    
    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber == null ? null : carNumber.trim();
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

    public String getCarEngine() {
        return carEngine;
    }

    public void setCarEngine(String carEngine) {
        this.carEngine = carEngine == null ? null : carEngine.trim();
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus == null ? null : carStatus.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getCarIllegals() {
        return carIllegals;
    }

    public void setCarIllegals(String carIllegals) {
        this.carIllegals = carIllegals == null ? null : carIllegals.trim();
    }
}