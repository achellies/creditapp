package com.wanda.creditapp.common.requestmodel.user;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class UserRegisterModel {
	
	@NotBlank(message = "{valid.required}")
	@Pattern(regexp = "(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}", message = "{valid.tel}")
	private String userPhone;   //手机号
	
	@NotBlank(message = "{valid.required}")
	private String verifyCode;   //短信验证码
	
	@NotBlank(message = "{valid.required}")
	private String userPassword;  //登录 密码
	
	@NotBlank(message = "{valid.required}")
	private String rePassWord;

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

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getRePassWord() {
		return rePassWord;
	}

	public void setRePassWord(String rePassWord) {
		this.rePassWord = rePassWord;
	}
	
	

}
