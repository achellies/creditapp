package com.wanda.creditapp.user.domain;

import com.wanda.creditapp.common.domain.BaseDomain;

public class DissentDomain extends BaseDomain {
   
	private static final long serialVersionUID = 1L;

	 /**异议内容*/
    private String dissentContent;
	
	/**提出异议的用户ID*/
    private Integer userId;

    /**对应查询记录的ID*/
    private Integer recordId;

    /**数据状态*/
    private Integer dataStatus;

    /**记录版本号*/
    private Integer dataVersion;

   

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
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

    public String getDissentContent() {
        return dissentContent;
    }

    public void setDissentContent(String dissentContent) {
        this.dissentContent = dissentContent == null ? null : dissentContent.trim();
    }
}