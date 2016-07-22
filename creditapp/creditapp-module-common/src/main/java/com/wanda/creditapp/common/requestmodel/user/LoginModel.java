package com.wanda.creditapp.common.requestmodel.user;

import org.hibernate.validator.constraints.NotBlank;

public class LoginModel {
	
	@NotBlank(message = "{valid.required}")
	private String userPhone; //userPhone
	
	@NotBlank(message = "{valid.required}")
	private String userPassword;  //userPassword

	//@NotBlank(message = "{valid.required}")
	private String verifyCode;

	
	
	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	
	
	
	
	
}
