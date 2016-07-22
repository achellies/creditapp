package com.wanda.creditapp.msg.service;

import com.wanda.creditapp.msg.domain.VerifyCode;

public interface IVerifyCodeService {
	public int insert(VerifyCode verifyCode);
	
	public boolean checkVerifyCode(String verifyReceiver,String verifyCode,int verifyType);
	
	public VerifyCode selectValidVerifyCode(String verifyReceiver,int verifyType);
}
