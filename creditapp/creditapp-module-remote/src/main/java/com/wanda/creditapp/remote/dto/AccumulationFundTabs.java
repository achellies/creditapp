package com.wanda.creditapp.remote.dto;

import java.util.List;

/**
 * 公积金登陆表单,将转换为JSON存储于数据库表APP_ACCUMULATION_ACCOUNT中,正式提交公积金查询请求的时候从数据库读出该JSON再转换为AccumulationFundTabs
 * @author xuxiaobin5
 *
 */
public class AccumulationFundTabs {
	
	private String website;
	
	private String name;
	
	private String type;
	
	private String sort;
	
	private String descript;
	
	private List<AccumulationFundField> fieldList;

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public List<AccumulationFundField> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<AccumulationFundField> fieldList) {
		this.fieldList = fieldList;
	}

}
