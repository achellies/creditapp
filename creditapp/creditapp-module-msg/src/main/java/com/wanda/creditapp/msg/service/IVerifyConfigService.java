package com.wanda.creditapp.msg.service;

import com.wanda.creditapp.msg.domain.VerifyConfig;

public interface IVerifyConfigService {
	public VerifyConfig getByVerifyType(Integer verifyType);
}
