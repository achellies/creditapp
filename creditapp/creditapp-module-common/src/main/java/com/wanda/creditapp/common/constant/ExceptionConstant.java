package com.wanda.creditapp.common.constant;

public enum ExceptionConstant {
	
	errorullPointer("ERR_0001","必要参数为空！"),
	errorValidateFail("ERR_0002","输入参数验证失败"),
	innerException("ERR_0003","内部异常"),
	fetchTokenFail("ERR_0004","获取token失败"),
	addRecordFail("ERR_0005","新增记录失败"),
	parameterTypeMissmatch("ERR_0006","参数类型不匹配"),
	fetchLoginUserFail("ERR_0007","获取登录用户失败"),
	
	/********************公积金相关异常(Accumulation Fund Account aka AFA)**********************/
	afa_empty_account("ERR_AFA_001","该公积金账户不存在"),
	afa_wrong_account("ERR_AFA_002","该公积金账户不属于您"),
	afa_delete_fail("ERR_AFA_003","公积金账户删除失败"),
	
	/********************调用远程服务相关异常(Credit Remote Service aka CRS)**************************/
	crs_empty_result("ERR_CRS_001","系统繁忙")
	
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
