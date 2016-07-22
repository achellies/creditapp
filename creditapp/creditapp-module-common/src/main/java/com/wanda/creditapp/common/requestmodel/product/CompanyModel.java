package com.wanda.creditapp.common.requestmodel.product;

public class CompanyModel extends ProductModel{
	
	/**企业名称*/
	private String name;
	/**组织机构代码*/
	private String orgCode;
	/**省份代码(数据字典见接口文档)*/
	private String province;
	/**营业执照号*/
	private String busiLicNo;
	/**社会统一信用号*/
	private String creditNo;
	/**P_B_B001中的输入参数，2表示企业名称全称认证，3表示企业执照号码认证，5表示组织机构代码查询*/
	private String keyType;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getBusiLicNo() {
		return busiLicNo;
	}
	public void setBusiLicNo(String busiLicNo) {
		this.busiLicNo = busiLicNo;
	}
	public String getCreditNo() {
		return creditNo;
	}
	public void setCreditNo(String creditNo) {
		this.creditNo = creditNo;
	}
	public String getKeyType() {
		return keyType;
	}
	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

}
