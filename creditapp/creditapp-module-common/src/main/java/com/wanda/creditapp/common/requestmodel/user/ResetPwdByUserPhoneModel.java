package com.wanda.creditapp.common.requestmodel.user;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

/** 通过短信验证码途径，重置登录密码 */
public class ResetPwdByUserPhoneModel {

	@NotBlank(message = "{valid.required}")
	private String userPassword; // 新密码

	@NotBlank(message = "{valid.required}")
	private String verifyCode; // 短信验证码

	@NotBlank(message = "{valid.required}")
	@Pattern(regexp = "^(13[0-9]|14[0-9]|15[0-9]|18[0-9])d{8}$", message = "{valid.tel}")
	private String userPhone; // 手机号码

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

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

}
