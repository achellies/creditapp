package com.wanda.creditapp.remote.model;

import java.util.Map;

import com.wanda.creditapp.common.requestmodel.product.ProductModel;
import com.wanda.creditapp.remote.dto.AccumulationFundTabs;

public class AccumulationFundModel extends ProductModel{
	
	/**支持公积金查询的区域代码*/
	private String regioncode;
	
	/**公积金网站密码*/
	private String password;
	
	/**公积金表单数据*/
	private Map<String,String> data;
	
	/**公积金表单*/
	private AccumulationFundTabs tabs;
	
	/**交易序列号*/
	private String request_id;

	public String getRegioncode() {
		return regioncode;
	}

	public void setRegioncode(String regioncode) {
		this.regioncode = regioncode;
	}	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public AccumulationFundTabs getTabs() {
		return tabs;
	}

	public void setTabs(AccumulationFundTabs tabs) {
		this.tabs = tabs;
	}

}
