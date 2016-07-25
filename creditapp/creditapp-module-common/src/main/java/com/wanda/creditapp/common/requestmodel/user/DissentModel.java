package com.wanda.creditapp.common.requestmodel.user;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class DissentModel {

	
	 /**异议内容*/
	@NotBlank(message = "{valid.required}")
    private String dissentContent;
	
	/**提出异议的用户ID*/
	@NotNull(message = "{valid.required}")
    private Integer userId;

    /**对应查询记录的ID*/
	@NotNull(message = "{valid.required}")
    private Integer recordId;
	

	public String getDissentContent() {
		return dissentContent;
	}

	public void setDissentContent(String dissentContent) {
		this.dissentContent = dissentContent;
	}

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
	
	
	
	
	
}
