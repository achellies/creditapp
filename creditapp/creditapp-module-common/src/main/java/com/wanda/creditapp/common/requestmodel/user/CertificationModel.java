package com.wanda.creditapp.common.requestmodel.user;

import org.hibernate.validator.constraints.NotBlank;

public class CertificationModel {
	/**
	 * 统一账户平台用户唯一标识
	 */
	@NotBlank(message = "{valid.required}")
	private String uapPwid;
	/**
	 * 真实姓名
	 */
	@NotBlank(message = "{valid.required}")
	private String name;
	/**
	 * 身份证号
	 */
	@NotBlank(message = "{valid.required}")
	private String idNumber;

	public String getUapPwid() {
		return uapPwid;
	}

	public void setUapPwid(String uapPwid) {
		this.uapPwid = uapPwid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

}
