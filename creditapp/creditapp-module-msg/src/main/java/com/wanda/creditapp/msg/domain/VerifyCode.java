package com.wanda.creditapp.msg.domain;

import com.wanda.creditapp.common.domain.BaseDomain;
import java.util.Date;

public class VerifyCode extends BaseDomain {
	private static final long serialVersionUID = 1L;

	/**
     * 验证码接受者(根据msgconfig判断是手机号或邮箱)
     */
    private String verifyReceiver;

    /**
     * 验证码
     */
    private String verifyCode;

    /**
     * 失效时间
     */
    private Date expiredTime;

    /**
     * 验证码类型：1-短信注册2-短信重置密码 3-注册图片
     */
    private Integer verifyType;

    /**
     * 状态
     */
    private Integer dataStatus;

    /**
     * 版本
     */
    private Integer dataVersion;

    public String getVerifyReceiver() {
        return verifyReceiver;
    }

    public void setVerifyReceiver(String verifyReceiver) {
        this.verifyReceiver = verifyReceiver == null ? null : verifyReceiver.trim();
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode == null ? null : verifyCode.trim();
    }

    public Date getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }

    public Integer getVerifyType() {
        return verifyType;
    }

    public void setVerifyType(Integer verifyType) {
        this.verifyType = verifyType;
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