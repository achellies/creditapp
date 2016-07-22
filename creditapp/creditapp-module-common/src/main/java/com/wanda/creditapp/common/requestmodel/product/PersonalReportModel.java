package com.wanda.creditapp.common.requestmodel.product;

import java.util.List;

/**
 * 个人征信产品的请求参数
 * @author xuxiaobin5
 *
 */
public class PersonalReportModel extends ProductModel{
	
	/**个人征信产品的产品ID列表*/
	private List<String> productIdList;
	
	/**姓名*/
	private String name;
	
	/**身份证号*/
	private String cardNo;
	
	/**手机号*/
	private String mobile;
	
	/**准驾车型*/
	private String carModels;
	
	/**初次领证日期,yyyy-MM-dd*/
	private String firstGetDocDate;
	
	/**档案编号*/
	private String archviesNo;
	
	/**驾照状态,如要核查驾驶证驾照状态,此项传入Y*/
	private String status;
	
	/**车牌号码*/
	private String licenseNo;
	
	/**号牌种类*/
	private String carType;
	
	/**完整的17位车架号*/
	private String vin;
	
	/**初次等级日期,格式为yyyy-MM-dd*/
	private String registTime;
	
	/**机动车信息(若要查询传入'Y',否则不传)*/
	private String carDetail;
	
	/**车辆状态(若要查询传入'Y',否则不传)*/
	private String carStatus;

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCarModels() {
		return carModels;
	}

	public void setCarModels(String carModels) {
		this.carModels = carModels;
	}

	public String getFirstGetDocDate() {
		return firstGetDocDate;
	}

	public void setFirstGetDocDate(String firstGetDocDate) {
		this.firstGetDocDate = firstGetDocDate;
	}

	public String getArchviesNo() {
		return archviesNo;
	}

	public void setArchviesNo(String archviesNo) {
		this.archviesNo = archviesNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public List<String> getProductIdList() {
		return productIdList;
	}

	public void setProductIdList(List<String> productIdList) {
		this.productIdList = productIdList;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getRegistTime() {
		return registTime;
	}

	public void setRegistTime(String registTime) {
		this.registTime = registTime;
	}

	public String getCarDetail() {
		return carDetail;
	}

	public void setCarDetail(String carDetail) {
		this.carDetail = carDetail;
	}

	public String getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(String carStatus) {
		this.carStatus = carStatus;
	}
	

}
