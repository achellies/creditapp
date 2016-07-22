package com.wanda.creditapp.msg.domain;

import com.wanda.creditapp.common.domain.BaseDomain;
import java.util.Date;

public class TsmMessages extends BaseDomain {
	
	private static final long serialVersionUID = 1L;

	/**
     * 消息ID,UUID
     */
    private String messageid;

    /**
     * 接受者rtx号或电话号码
     */
    private String target;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 消息类型1-RTX 2-SMS 3-OA
     */
    private Integer messagetype;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 调度时间
     */
    private Date targettime;

    /**
     * 发送时间
     */
    private Date sendtime;

    /**
     * 发送状态0-未处理 1-成功 2-失败
     */
    private Integer status;

    /**
     * 发送次数
     */
    private Integer trytimes;

    /**
     * 错误信息
     */
    private String errorinfo;

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid == null ? null : messageid.trim();
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getMessagetype() {
        return messagetype;
    }

    public void setMessagetype(Integer messagetype) {
        this.messagetype = messagetype;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getTargettime() {
        return targettime;
    }

    public void setTargettime(Date targettime) {
        this.targettime = targettime;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTrytimes() {
        return trytimes;
    }

    public void setTrytimes(Integer trytimes) {
        this.trytimes = trytimes;
    }

    public String getErrorinfo() {
        return errorinfo;
    }

    public void setErrorinfo(String errorinfo) {
        this.errorinfo = errorinfo == null ? null : errorinfo.trim();
    }
}