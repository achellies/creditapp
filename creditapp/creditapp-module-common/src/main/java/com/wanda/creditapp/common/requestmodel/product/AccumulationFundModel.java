package com.wanda.creditapp.common.requestmodel.product;

public class AccumulationFundModel extends ProductModel{
	
	/**支持公积金查询的区域代码*/
	private String regioncode;
	
	/**数据源英文缩写*/
	private String website_en;
	
	/**数据源编码*/
	private String website_sort;
	
	/**采集方式*/
	private String submit_type;
	
	/**身份证号*/
	private String id_card_num;
	
	/**姓名*/
	private String name;
	
	/**公积金网站账号*/
	private String account;
	
	/**公积金网站密码*/
	private String password;
	
	/**手机号*/
	private String cell_phone_num;
	
	/**交易序列号*/
	private String request_id;

	public String getRegioncode() {
		return regioncode;
	}

	public void setRegioncode(String regioncode) {
		this.regioncode = regioncode;
	}

	public String getWebsite_en() {
		return website_en;
	}

	public void setWebsite_en(String website_en) {
		this.website_en = website_en;
	}

	public String getWebsite_sort() {
		return website_sort;
	}

	public void setWebsite_sort(String website_sort) {
		this.website_sort = website_sort;
	}

	public String getSubmit_type() {
		return submit_type;
	}

	public void setSubmit_type(String submit_type) {
		this.submit_type = submit_type;
	}

	public String getId_card_num() {
		return id_card_num;
	}

	public void setId_card_num(String id_card_num) {
		this.id_card_num = id_card_num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCell_phone_num() {
		return cell_phone_num;
	}

	public void setCell_phone_num(String cell_phone_num) {
		this.cell_phone_num = cell_phone_num;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

}
