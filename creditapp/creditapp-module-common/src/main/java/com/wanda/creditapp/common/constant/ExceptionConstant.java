package com.wanda.creditapp.common.constant;

public enum ExceptionConstant {
	
	errorullPointer("ERR_0001","必要参数为空！"),
	errorValidateFail("ERR_0002","输入参数验证失败"),
	innerException("ERR_0003","内部异常"),
	fetchTokenFail("ERR_0004","获取token失败"),
	addRecordFail("ERR_0005","新增记录失败"),
	parameterTypeMissmatch("ERR_0006","参数类型不匹配"),
	fetchLoginUserFail("ERR_0007","获取登录用户失败")
	;
	
	private String errorCode;
	
	private String errorMessage;
		
	private ExceptionConstant(String errorCode,String errorMessage){
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public String getErrorCode(){
		return this.errorCode;
	}
	
	public String getErrorMessage(){
		return this.errorMessage;
	}
	

}
