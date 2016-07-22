package com.wanda.creditapp.msg.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.wanda.creditapp.msg.dao.VerifyConfigMapper;
import com.wanda.creditapp.msg.domain.VerifyConfig;
import com.wanda.creditapp.msg.service.IVerifyConfigService;

@Service("verifyConfigService")
public class VerifyConfigServiceImpl implements IVerifyConfigService {
	private static final Logger logger = Logger.getLogger(VerifyConfigServiceImpl.class);
	@Resource
	VerifyConfigMapper verifyConfigMapper;

	@Override
	public VerifyConfig getByVerifyType(Integer verifyType) {
		VerifyConfig verifyConfig = null;
		try {
			verifyConfig = verifyConfigMapper.getByVerifyType(verifyType);
		} catch (Exception e) {
			logger.error("根据主题和消息类型获取VerifyConfig异常", e);
		}
		return verifyConfig;
	}

}
