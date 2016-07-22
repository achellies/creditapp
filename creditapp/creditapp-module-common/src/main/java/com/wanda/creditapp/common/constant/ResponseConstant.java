package com.wanda.creditapp.common.constant;

/**
 * Title：ResponseConstant <br/>
 * Description：前台响应用 <br/>
 * Company：wanda <br/>
 */
public enum ResponseConstant {

	RESPONSE_SUCCESS("00000","处理成功",4,CommonConstant.RESPONSE_SUCCESS_CODE),
	RESPONSE_FAIL("10000","处理失败",1,CommonConstant.RESPONSE_FAIL_CODE),
	
	RESPONSE_ERRORCODE_INPUTERROR("2000","输入参数有误",1,CommonConstant.RESPONSE_FAIL_CODE),
	RESPONSE_ERRORCODE_VERIFYCODE_FAIL("3000","验证码错误",1,CommonConstant.RESPONSE_FAIL_CODE),
	RESPONSE_ERRORCODE_PWD_FAIL("3001","密码输入不一致",1,CommonConstant.RESPONSE_FAIL_CODE),
	RESPONSE_ERRORCODE_NATIVE_FAIL("3002","本地处理失败",1,CommonConstant.RESPONSE_FAIL_CODE),
	RESPONSE_ERRORCODE_VALIDATE_FAIL("3003","数据校验失败",1,CommonConstant.RESPONSE_FAIL_CODE),
	RESPONSE_ERRORCODE_PHONE_EXIST("3004","该手机号已经被注册",1,CommonConstant.RESPONSE_FAIL_CODE),
	RESPONSE_ERRORCODE_OLDPWD_FAIL("3005","原密码输入错误",1,CommonConstant.RESPONSE_FAIL_CODE),
	
	
	RESPONSE_ACCESSTOKEN_INVALID("5001","ACCESSTOKEN过期,请使用refreshToken更新",1,CommonConstant.RESPONSE_FAIL_CODE),
	RESPONSE_REFRESHTOKEN_INVALID("5002","REFRESHTOKEN无效重新登录",4,CommonConstant.RESPONSE_FAIL_CODE);
	
	/** 代码 */
	private final String code;
	
	/** 描述 */
	private final String msg;
	
	/** 前端提示方式 1-toast 2-dialog 4-统链   8-扩展信息 */
	private final int display;
	
	/** 类型 */
	private final String type;  //成功:00000  ,  失败:10000


	ResponseConstant(String code, String msg ,Integer display, String type) {
		this.code = code;
		this.msg = msg;
		this.display = display;
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public int getDisplay() {
		return display;
	}

	public String getType() {
		return type;
	}

	
	

}
