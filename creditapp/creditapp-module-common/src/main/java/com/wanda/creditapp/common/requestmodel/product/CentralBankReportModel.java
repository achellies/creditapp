package com.wanda.creditapp.common.requestmodel.product;


public class CentralBankReportModel extends ProductModel{

	/**
	 * 中国人民银行征信中心网站注册账号
	 */
	private String account;
	/**
	 * 中国人民银行征信中心网站注册密码
	 */
	private String password;
	/**
	 * 短信验证码
	 */
	private String captcha;
	/**
	 * 申请人姓名
	 */
	private String name;
	/**
	 * 申请人身份证号
	 */
	private String cardNo;
	/**
	 * 申请人手机号
	 */
	private String mobile;
	/**
	 * 交易序列号
	 */
	private String request_id;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

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

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	
	

}
