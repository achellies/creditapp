package com.wanda.creditapp.msg.domain;

import com.wanda.creditapp.common.domain.BaseDomain;

public class MsgTemplate extends BaseDomain {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 模板名称
     */
    private String templateName;

    /**
     * 消息标题
     */
    private String msgTitle;

    /**
     * 消息类型类型1-RTX 2-SMS 3-OA 4-邮件 5-图片
     */
    private Integer msgType;

    /**
     * 内容模板
     */
    private String msgTemplate;

    /**
     * 数据状态
     */
    private Integer dataStatus;

    /**
     * 版本
     */
    private Integer dataVersion;

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle == null ? null : msgTitle.trim();
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getMsgTemplate() {
        return msgTemplate;
    }

    public void setMsgTemplate(String msgTemplate) {
        this.msgTemplate = msgTemplate == null ? null : msgTemplate.trim();
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