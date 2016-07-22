package com.wanda.creditapp.remote.dto;

/**
 * 公积金输入参数
 * @author xuxiaobin5
 *
 */
public class AccumulationFundField {
	
	private String parameter_name;
	
	private String parameter_code;
	
	private String grab_type;

	public String getParameter_name() {
		return parameter_name;
	}

	public void setParameter_name(String parameter_name) {
		this.parameter_name = parameter_name;
	}

	public String getParameter_code() {
		return parameter_code;
	}

	public void setParameter_code(String parameter_code) {
		this.parameter_code = parameter_code;
	}

	public String getGrab_type() {
		return grab_type;
	}

	public void setGrab_type(String grab_type) {
		this.grab_type = grab_type;
	}

}
