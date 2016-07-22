package com.wanda.creditapp.common.requestmodel.user;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class FindCarIllegalModel {
	
	@NotBlank(message = "{valid.required}")
	private String carNumber;  //车牌号
	
	@NotNull(message = "{valid.required}")
	private Integer carType;   //车辆类型
	
	@NotBlank(message = "{valid.required}")
	private String carCode;   //车架号
	
	@NotBlank(message = "{valid.required}")
	private String carEngine;  //发动机号

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
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
		this.carCode = carCode;
	}

	public String getCarEngine() {
		return carEngine;
	}

	public void setCarEngine(String carEngine) {
		this.carEngine = carEngine;
	}

	
	
	
}
