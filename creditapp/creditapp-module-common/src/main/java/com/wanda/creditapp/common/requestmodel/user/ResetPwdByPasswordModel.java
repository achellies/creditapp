package com.wanda.creditapp.common.requestmodel.user;

import org.hibernate.validator.constraints.NotBlank;

/**通过原登录密码,重置登录密码*/
public class ResetPwdByPasswordModel {
	
	//@NotBlank(message = "{valid.required}")
	private String PWID;   //统一账户编号
	
	@NotBlank(message = "{valid.required}")
	private String userPassword;  //新密码
	
	@NotBlank(message = "{valid.required}")
	private String oldPwd;  //原密码

	
	public String getPWID() {
		return PWID;
	}

	public void setPWID(String pWID) {
		PWID = pWID;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	
	

	
	
	
}
