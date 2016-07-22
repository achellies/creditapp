package com.wanda.creditapp.common.exception;

import com.wanda.creditapp.common.constant.ExceptionConstant;


public class CreditAppException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String errCode;
	private String errMsg;

	public CreditAppException() {
		super();
	}

	public CreditAppException(String message, Throwable cause) {
		super(message, cause);
		this.errCode = "操作失败";
		this.errMsg = message;
	}

	/*public CreditAppException(String message) {
		super(message);
		this.errCode = "操作失败";
		this.errMsg = message;
	}*/

	public CreditAppException(Throwable cause) {
		super(cause);
	}

	public CreditAppException(String errCode, String errMsg) {
		super(errCode + ":" + errMsg);
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	
	/**
	 * 根据传入的异常常量类型封装异常
	 * @param exceptionConstant
	 */
	public CreditAppException(ExceptionConstant exceptionConstant) {
		super(exceptionConstant.getErrorCode() + ":" + exceptionConstant.getErrorMessage());
		this.errCode = exceptionConstant.getErrorCode();
		this.errMsg = exceptionConstant.getErrorMessage();
	}

	public CreditAppException(String errCode, String errMsg, Throwable cause) {
		super(errCode + ":" + errMsg, cause);
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public CreditAppException(String errCode, String errMsg, String logMsg) {
		super(errCode + ":" + errMsg);
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	
	public CreditAppException(String errCode){
		this.errCode = errCode;
	}

	public String getErrCode() {
		return this.errCode;
	}

	public String getErrMsg() {
		return this.errMsg;
	}

}
