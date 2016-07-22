package com.wanda.creditapp.msg.domain;

import com.wanda.creditapp.common.domain.BaseDomain;

public class MsgDetail extends BaseDomain {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 接收者
     */
    private String msgReceiver;

    /**
     * 标题
     */
    private String msgTitle;

    /**
     * 内容
     */
    private String msgContent;

    /**
     * 消息类型类型1-RTX 2-SMS 3-OA 4-邮件 5-图片
     */
    private Integer msgType;

    /**
     * 关联到验证码表的主键，标识本条消息发送的验证码
     */
    private String verifyCodeId;

    private Integer dataStatus;

    public String getMsgReceiver() {
        return msgReceiver;
    }

    public void setMsgReceiver(String msgReceiver) {
        this.msgReceiver = msgReceiver == null ? null : msgReceiver.trim();
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle == null ? null : msgTitle.trim();
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent == null ? null : msgContent.trim();
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getVerifyCodeId() {
        return verifyCodeId;
    }

    public void setVerifyCodeId(String verifyCodeId) {
        this.verifyCodeId = verifyCodeId == null ? null : verifyCodeId.trim();
    }

    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }
}