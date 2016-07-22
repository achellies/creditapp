package com.wanda.creditapp.msg.common;

public enum MsgConstant {
	INSTANCE;
	public static String SMS_VERIFY_CODE_PREFIX="SMS_VERIFY_CODE";
	public static String VERIFYCODE_KEY="verifyCode";//验证码(具体的值)
	public static String VERIFYCODEID_KEY="verifyCodeId";//验证码对象的id
	public static String MSGRECEIVER_KEY="msgReceiver";
	public static int VERIFYCODE_TYPE_1=1;//验证码类型1，用户注册
	public static int VERIFYCODE_TYPE_2=2;//验证码类型2，修改密码
	public static int VERIFYCODE_TYPE_3=3;//验证码类型2，修改密码
}
