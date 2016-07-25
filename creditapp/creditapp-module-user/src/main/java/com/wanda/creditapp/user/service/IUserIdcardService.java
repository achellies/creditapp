package com.wanda.creditapp.user.service;

import com.wanda.creditapp.user.domain.UserIdcard;

public interface IUserIdcardService {
	// 注册
	public int saveOrUpdate(UserIdcard record);

	// 根据pwid获取用户身份证记录
	public UserIdcard queryIdcardbyUserId(int id);
}
