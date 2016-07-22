package com.wanda.creditapp.user.service;

import com.wanda.creditapp.user.domain.UserDomain;

public interface IUserService {
	
	//注册
	public int insert(UserDomain record);

	//根据手机号重设密码
	public int updatePwdByPhone( UserDomain userDomain);

	//根据原密码重设密码
	public int updateUserPwdByPwd(UserDomain userDomain);
	
	//登录
	public UserDomain queryUserByPhoneAndPwd(UserDomain userDomain);

	//判断手机是否存在
	public Boolean queryUserPhoneByPhone(String userPhone);

	//判断登录密码是否正确
	public String queryPwdByPhone(String userPhone);

	//通过pwid判断密码是否正确
	public String queryOldPwdByPwid(String uapPwid);


	
}
