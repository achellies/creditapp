package com.wanda.creditapp.common.requestmodel.user;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

/** 通过短信验证码途径，重置登录密码 */
public class ResetPwdByUserPhoneModel {

	//这个是uap系统必须要输入的参数,目前先设置在请求参数中
	private String pwid;
	
	@NotBlank(message = "{valid.required}")
	@Pattern(regexp = "(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}", message = "{valid.tel}")
	private String userPhone; // 手机号码
	
	@NotBlank(message = "{valid.required}")
	private String userPassword; // 新密码

	@NotBlank(message = "{valid.required}")
	private String verifyCode; // 短信验证码

	
	public String getPwid() {
		return pwid;
	}

	public void setPwid(String pwid) {
		this.pwid = pwid;
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

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

}
