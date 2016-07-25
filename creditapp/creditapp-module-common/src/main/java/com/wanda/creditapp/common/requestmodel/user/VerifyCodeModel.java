package com.wanda.creditapp.common.requestmodel.user;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class VerifyCodeModel {
	
	@Pattern(regexp = "(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}", message = "{valid.tel}")
	private String mobileNo;
	
	@NotBlank(message = "{valid.required}")
	private String type;//验证码类型：1-短信注册2-短信重置密码 3-注册图片

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
