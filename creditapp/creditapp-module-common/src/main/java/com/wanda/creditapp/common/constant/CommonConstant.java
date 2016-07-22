package com.wanda.creditapp.common.constant;

public enum CommonConstant {
	INSTANCE;
	public static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static String UAP_SUCCESS_CODE = "UAP-10000";
	public static String PWID = "PWID";
	

	public static String PAGE_INFO = "pageInfo";//响应数据时默认key值
	public static String RESPONSE_SUCCESS_CODE = "00000";
	public static String RESPONSE_FAIL_CODE = "10000";
	public static String RESPONSE_SUCCESS_DESC = "处理成功";
	public static String RESPONSE_FAIL_DESC = "处理失败";
	//处理失败时,需要返回的字段
	public static String RESPONSE_ERRORCODE_KEY = "errorCode";
	public static String RESPONSE_ERRORMESSAGE_KEY = "errorMessage"; 
	public static String RESPONSE_ERRORDISPLAY_KEY = "errorDisplay";
//	public static String RESPONSE_EXTRAINFO_KEY = "extraInfo" ;
	
	public static String ACCESS_TOKEN_KEY = "accessToken";
	public static String REFRESH_TOKEN_KEY = "refreshToken";
	public static String ACCESS_USER_KEY = "accessUser";
	public static String VERIFY_CODE_KEY = "VERIFY_CODE";//验证码放入redis时的前缀
	
}
