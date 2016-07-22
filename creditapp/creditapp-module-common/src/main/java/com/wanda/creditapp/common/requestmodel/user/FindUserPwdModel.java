package com.wanda.creditapp.common.requestmodel.user;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class FindUserPwdModel {
	
	@NotBlank(message = "{valid.required}")
	@Pattern(regexp = "^(13[0-9]|14[0-9]|15[0-9]|18[0-9])d{8}$", message = "{valid.tel}")
	private String userPhone;
	
	@NotBlank(message = "{valid.required}")
	private String verifyCode;
	
	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}


}
