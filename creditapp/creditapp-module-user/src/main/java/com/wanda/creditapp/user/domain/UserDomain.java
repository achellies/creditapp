package com.wanda.creditapp.user.domain;

import com.wanda.creditapp.common.domain.BaseDomain;

public class UserDomain extends BaseDomain {
	private static final long serialVersionUID = 1L;

	/**
	 * 统一账户平台用户唯一标识
	 */
	private String uapPwid;

	/**
	 * 手机号
	 */
	private String userPhone;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 密码
	 */
	private String userPassword;

	/**
	 * 真实姓名
	 */
	private String userRelname;

	/**
	 * 性别
	 */
	private Integer userGender;

	/**
	 * 身份证号
	 */
	private String userIdNumber;

	/**
	 * 用户类型
	 */
	private Integer userType;

	/**
	 * 昵称
	 */
	private String userNickname;

	/**
	 * 邮箱
	 */
	private String userEmail;

	/**
	 * 头像访问的URL
	 */
	private String userImgurl;

	/**
	 * 头像保存路径
	 */
	private String userImgpath;

	/**
	 * 预留信息
	 */
	private String userMessage;

	/**
	 * 状态
	 */
	private Integer dataStatus;

	/**
	 * 版本
	 */
	private Integer dataVersion;

	public String getUapPwid() {
		return uapPwid;
	}

	public void setUapPwid(String uapPwid) {
		this.uapPwid = uapPwid == null ? null : uapPwid.trim();
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone == null ? null : userPhone.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword == null ? null : userPassword.trim();
	}

	public String getUserRelname() {
		return userRelname;
	}

	public void setUserRelname(String userRelname) {
		this.userRelname = userRelname == null ? null : userRelname.trim();
	}

	public Integer getUserGender() {
		return userGender;
	}

	public void setUserGender(Integer userGender) {
		this.userGender = userGender;
	}

	public String getUserIdNumber() {
		return userIdNumber;
	}

	public void setUserIdNumber(String userIdNumber) {
		this.userIdNumber = userIdNumber == null ? null : userIdNumber.trim();
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname == null ? null : userNickname.trim();
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail == null ? null : userEmail.trim();
	}

	public String getUserImgurl() {
		return userImgurl;
	}

	public void setUserImgurl(String userImgurl) {
		this.userImgurl = userImgurl == null ? null : userImgurl.trim();
	}

	public String getUserImgpath() {
		return userImgpath;
	}

	public void setUserImgpath(String userImgpath) {
		this.userImgpath = userImgpath == null ? null : userImgpath.trim();
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage == null ? null : userMessage.trim();
	}

	public Integer getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(Integer dataStatus) {
		this.dataStatus = dataStatus;
	}

	public Integer getDataVersion() {
		return dataVersion;
	}

	public void setDataVersion(Integer dataVersion) {
		this.dataVersion = dataVersion;
	}
}
