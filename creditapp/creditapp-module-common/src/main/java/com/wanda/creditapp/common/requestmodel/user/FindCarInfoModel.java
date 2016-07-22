package com.wanda.creditapp.common.requestmodel.user;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class FindCarInfoModel {
	
	@NotBlank(message = "{valid.required}")
	private String name;
	
	@NotBlank(message = "{valid.required}")
	private String cardNo;
	
	@NotBlank(message = "{valid.required}")
	private String licenseNo;
	
	@NotNull(message = "{valid.required}")
	private Integer carType;

	private String vin_17;
	
	private Date registTime;
	
	private String carDetail;
	
	private Integer carStatus ;

	
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

	public Integer getCarType() {
		return carType;
	}

	public void setCarType(Integer carType) {
		this.carType = carType;
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
	
	
	
	
	
}
