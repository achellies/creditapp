package com.wanda.creditapp.common.requestmodel.product;

import com.wanda.creditapp.common.requestmodel.product.ProductModel;

public class CarIllegalInfoModel extends ProductModel{
	
	//车牌号
	private String carNumber;
	
	//车辆类型
	private String carType;
	
	//车架号
	private String carCode;
	
	//发动机号
	private String carEngine;

	
	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
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
