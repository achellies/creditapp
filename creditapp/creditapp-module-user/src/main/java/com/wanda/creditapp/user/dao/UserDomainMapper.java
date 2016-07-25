package com.wanda.creditapp.user.dao;

import com.wanda.creditapp.user.domain.UserDomain;
import java.util.List;

public interface UserDomainMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(UserDomain record);

	UserDomain selectByPrimaryKey(Integer id);

	List<UserDomain> selectAll();

	int updateByPrimaryKey(UserDomain record);

	// 用户注册
	int saveUser(UserDomain record);

	// 根据短信验证码,修改密码
	int updatePWdByPhone(UserDomain record);

	// 根据原密码,修改密码
	int updatePwdByOldPwd(UserDomain userDomain);

	// 登录
	UserDomain queryUserByPhoneAndPwd(UserDomain userDomain);

	UserDomain queryUserByUapPwid(String uapPwid);

	// 判断手机号是否已被注册
	UserDomain queryUserPhoneByPhone(String userPhone);

	// 判断登录密码是否正确
	String queryPwdByPhone(String userPhone);

	// 通过pwid,判断原密码是否正确
	String queryOldPwdByPwid(String uapPwid);

	// 根据短信验证码,修改密码
	int updateCertification(UserDomain userDomain);
}
