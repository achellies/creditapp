package com.wanda.creditapp.msg.service;

import com.wanda.creditapp.msg.common.MessageParam;
import com.wanda.creditapp.msg.domain.VerifyCode;


public interface IMsgDetailService {
	public void insertMsgDetail(String templateCode,VerifyCode verifyCode,MessageParam messageParam);
}
