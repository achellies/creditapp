package com.wanda.creditapp.common.response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.wanda.creditapp.common.constant.CommonConstant;
import com.wanda.creditapp.common.constant.ResponseConstant;

public class DataResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fixedDataStruct = "wandacredit";

	private String resultCode;

	private String resultMessage;

	Map<String, Object> dataMap = new HashMap<String, Object>();

	public DataResponse() {

	}

	public DataResponse(ResponseConstant responseConstant) {
		this.resultCode = responseConstant.getType();
		if (responseConstant.getType().equals(CommonConstant.RESPONSE_SUCCESS_CODE)) {
			this.resultMessage = CommonConstant.RESPONSE_SUCCESS_DESC;
		} else if (responseConstant.getType().equals(CommonConstant.RESPONSE_FAIL_CODE)) {
			this.resultMessage = CommonConstant.RESPONSE_FAIL_DESC;
		}
		// 返回失败时
		if (responseConstant.getType().equals(CommonConstant.RESPONSE_FAIL_CODE)) {
			this.dataMap.put(CommonConstant.RESPONSE_ERRORCODE_KEY, responseConstant.getCode());
			this.dataMap.put(CommonConstant.RESPONSE_ERRORMESSAGE_KEY, responseConstant.getMsg());
			this.dataMap.put(CommonConstant.RESPONSE_ERRORDISPLAY_KEY, responseConstant.getDisplay());
			// this.dataMap.put(CommonConstant.RESPONSE_EXTRAINFO_KEY,responseConstant);
		}
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public void put(String key, Object value) {
		this.dataMap.put(key, value);
	}

	public Object get(String key) {
		return this.dataMap.get(key);
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public String getFixedDataStruct() {
		return fixedDataStruct;
	}

	public void setFixedDataStruct(String fixedDataStruct) {
		this.fixedDataStruct = fixedDataStruct;
	}

}
