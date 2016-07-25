package com.wanda.creditapp.user.domain;

import com.wanda.creditapp.common.domain.BaseDomain;

public class UserDomain extends BaseDomain {
	private static final long serialVersionUID = 1L;

	/**
     * uap唯一标识
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
     * 昵称
     */
    private String userNickname;

    /**
     * 性别
     */
    private Integer userGender;

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
     * 状态1-默认
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

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname == null ? null : userNickname.trim();
    }

    public Integer getUserGender() {
        return userGender;
    }

    public void setUserGender(Integer userGender) {
        this.userGender = userGender;
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