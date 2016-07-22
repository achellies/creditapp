package com.wanda.creditapp.remote.common;

public enum UapRemoteConstant {
	INSTANCE;
	public static final String UAP_REQ_HEADER_KEY = "reqHeader";
	public static final String UAP_RESP_HEADER_KEY = "respHeader";
	public static final String UAP_LOG_COLLECTION_KEY = "logCollection";

	// 线上注册
	public static final String API_PATH_ONLINE_REGISTRATION = "/account/registration/online";

	// 用户登录
	public static final String API_PATH_LOGIN = "/auth/login/password";
	
	// 通过短信验证码途径，重置登录密码
	public static final String API_PATH_RESETPWDBYPHONE = "/credential/pwd/reset/by_sms";

	// 通过原登录密码,重置登录密码
	public static final String API_PATH_RESETPWDBYPASSWORD = "/credential/pwd/reset/by_oldpwd";

	//车辆违章信息查询
	public static final String API_PATH_QUERY_CAR_ILLEGAL = "";
	
	// 新增车辆
	public static final String API_PATH_SAVE_CAR_INFO = "";
	
	//车辆信息查询
	public static final String API_PATH_QUERY_CAR_INFO = "";
	
}
