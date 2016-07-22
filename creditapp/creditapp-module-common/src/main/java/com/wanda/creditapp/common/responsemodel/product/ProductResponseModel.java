package com.wanda.creditapp.common.responsemodel.product;

import java.io.Serializable;
import java.util.Map;

/**
 * 这是针对业务网关返回结果的json转换成的Object
 * @author xuxiaobin5
 *
 */
public class ProductResponseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6537716178903961455L;
	
	private String req_sn;
	
	private String inf_id;
	
	private String retcode;
	
	private String retmsg;
	
	private String version;
	
	private Long retdate;
	
	private Map<String,Object> retdata;
	
	private String prod_id;
	
	private String response_sn;

	public String getResponse_sn() {
		return response_sn;
	}

	public void setResponse_sn(String response_sn) {
		this.response_sn = response_sn;
	}

	public String getReq_sn() {
		return req_sn;
	}

	public void setReq_sn(String req_sn) {
		this.req_sn = req_sn;
	}

	public String getInf_id() {
		return inf_id;
	}

	public void setInf_id(String inf_id) {
		this.inf_id = inf_id;
	}

	public String getRetcode() {
		return retcode;
	}

	public void setRetcode(String retcode) {
		this.retcode = retcode;
	}

	public String getRetmsg() {
		return retmsg;
	}

	public void setRetmsg(String retmsg) {
		this.retmsg = retmsg;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Long getRetdate() {
		return retdate;
	}

	public void setRetdate(Long retdate) {
		this.retdate = retdate;
	}

	public Map<String, Object> getRetdata() {
		return retdata;
	}

	public void setRetdata(Map<String, Object> retdata) {
		this.retdata = retdata;
	}

	public String getProd_id() {
		return prod_id;
	}

	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	
	

}
