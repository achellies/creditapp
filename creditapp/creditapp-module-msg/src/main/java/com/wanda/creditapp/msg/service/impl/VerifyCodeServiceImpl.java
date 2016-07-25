package com.wanda.creditapp.msg.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.msg.dao.VerifyCodeMapper;
import com.wanda.creditapp.msg.domain.VerifyCode;
import com.wanda.creditapp.msg.service.IVerifyCodeService;

@Service("verifyCodeService")
public class VerifyCodeServiceImpl implements IVerifyCodeService {

	private static final Logger logger = Logger.getLogger(VerifyCodeServiceImpl.class);

	@Resource
	VerifyCodeMapper verifyCodeMapper;

	@Override
	public int insert(VerifyCode verifyCode) {
		int result = -1;
		try {
			// 插入之前确保库中没有有效的记录
			verifyCodeMapper.invalidateVerifyCode(verifyCode.getVerifyReceiver(), verifyCode.getVerifyType());
			result = verifyCodeMapper.saveVerifyCode(verifyCode);
		} catch (Exception e) {
			logger.error("VerifyCodeServiceImpl.insert-新增验证码异常", e);
			throw new CreditAppException("VerifyCodeServiceImpl.insert-新增验证码异常", e);
		}
		return result;
	}

	@Override
	public boolean checkVerifyCode(String verifyReceiver,String verifyCode,int verifyType) {
		int result = verifyCodeMapper.updateValidVerifyCode(verifyReceiver,verifyCode,verifyType);
		if (result == 1) {//当且仅当更新到一条的时候，表示验证通过
			return true;
		}
		return false;
	}

	@Override
	public VerifyCode selectValidVerifyCode(String verifyReceiver, int verifyType) {
		// TODO Auto-generated method stub
		return null;
	}

}
