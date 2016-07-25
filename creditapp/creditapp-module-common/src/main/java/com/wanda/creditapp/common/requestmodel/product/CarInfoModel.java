package com.wanda.creditapp.common.requestmodel.product;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class CarInfoModel{
	
	//车辆名
	@NotBlank(message = "{valid.required}")
	private String name;
	
	//身份证号
	@NotBlank(message = "{valid.required}")
	private String cardNo;
	
	//车牌号
	@NotBlank(message = "{valid.required}")
	private String licenseNo;
	
	//号牌种类
	@NotNull(message = "{valid.required}")
	private Integer carType;
	
	//17位完整车架号
	private String vin_17;
	
	//初次登记日期
	private Date registTime;
	
	//机动车信息
	private String carDetail;
	
	//车辆状态
	private Integer carStatus;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getVin_17() {
		return vin_17;
	}

	public void setVin_17(String vin_17) {
		this.vin_17 = vin_17;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	public String getCarDetail() {
		return carDetail;
	}

	public void setCarDetail(String carDetail) {
		this.carDetail = carDetail;
	}

	public Integer getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(Integer carStatus) {
		this.carStatus = carStatus;
	}

	public Integer getCarType() {
		return carType;
	}

	public void setCarType(Integer carType) {
		this.carType = carType;
	}
	
	
}
