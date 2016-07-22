package com.wanda.creditapp.msg.domain;

import com.wanda.creditapp.common.domain.BaseDomain;

public class VerifyConfig extends BaseDomain {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 名称
     */
    private String configName;

    /**
     * 对应使用的消息模板的code
     */
    private String templateCode;

    /**
     * 验证码有效时间。默认1800，单位秒
     */
    private Integer verifycodeValidtime;

    /**
     * 验证码类型：1-短信注册2-短信重置密码 3-注册图片 4-登录图片
     */
    private Integer verifyType;

    /**
     * 生成的验证码长度
     */
    private Integer verifyLength;

    /**
     * 验证码的字符来源。
     */
    private String verifyChararray;

    /**
     * 状态
     */
    private Integer dataStatus;

    /**
     * 版本
     */
    private Integer dataVersion;

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName == null ? null : configName.trim();
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode == null ? null : templateCode.trim();
    }

    public Integer getVerifycodeValidtime() {
        return verifycodeValidtime;
    }

    public void setVerifycodeValidtime(Integer verifycodeValidtime) {
        this.verifycodeValidtime = verifycodeValidtime;
    }

    public Integer getVerifyType() {
        return verifyType;
    }

    public void setVerifyType(Integer verifyType) {
        this.verifyType = verifyType;
    }

    public Integer getVerifyLength() {
        return verifyLength;
    }

    public void setVerifyLength(Integer verifyLength) {
        this.verifyLength = verifyLength;
    }

    public String getVerifyChararray() {
        return verifyChararray;
    }

    public void setVerifyChararray(String verifyChararray) {
        this.verifyChararray = verifyChararray == null ? null : verifyChararray.trim();
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