package com.wanda.creditapp.user.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.user.dao.UserDomainMapper;
import com.wanda.creditapp.user.domain.UserDomain;
import com.wanda.creditapp.user.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {
	
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Resource
	private UserDomainMapper userDomainMapper;
	
	
	//用户注册
	public int insert(UserDomain record) {
		int result = -1;
		try {
			result = userDomainMapper.insert(record);
		} catch (Exception e) {
			logger.error("UserServiceImpl.insert---用户注册时异常", e);
			throw new CreditAppException("UserServiceImpl.insert---用户注册时异常", e);
		}
		return result;
	}

	
	//判断手机号是否被注册
	public Boolean queryUserPhoneByPhone(String userPhone) {
		Boolean flag = false;
		try {
			UserDomain u = this.userDomainMapper.queryUserPhoneByPhone(userPhone);
			if(u != null){
				//存在该号
				flag = true;
			}
		} catch (Exception e) {
			logger.error("UserServiceImpl.queryUserPhoneByPhone---查询手机号异常", e);
			throw new CreditAppException("UserServiceImpl.queryUserPhoneByPhone---查询手机号异常", e);
		}
		return flag;
	}

	
	//用户登录
	public UserDomain queryUserByPhoneAndPwd(UserDomain userDomain) {
		UserDomain u = null;
		try {
			u = this.userDomainMapper.queryUserByPhoneAndPwd(userDomain);
		} catch (Exception e) {
			logger.error("UserServiceImpl.queryUserByPhoneAndPwd---用户登录异常", e);
			throw new CreditAppException("UserServiceImpl.queryUserByPhoneAndPwd---用户登录异常", e);
		}
		return u;
	}
	
	
	//根据手机号,重置密码
	public int updatePwdByPhone(UserDomain userDomain) {
		int result = -1;
		try {
			result = this.userDomainMapper.updateByPrimaryKey(userDomain);
		} catch (Exception e) {
			logger.error("UserServiceImpl.updatePwdByPhone---根据手机号重置密码异常", e);
			throw new CreditAppException("UserServiceImpl.updatePwdByPhone---根据手机号重置密码异常", e);
		}
		return result;
	}

	
	//根据原密码,重置密码
	public int updateUserPwdByPwd(UserDomain userDomain) {
		int result = -1;
		try {
			result = this.userDomainMapper.updateByPrimaryKey(userDomain);
		} catch (Exception e) {
			logger.error("UserServiceImpl.updateUserPwdByPwd---根据原密码重置密码异常", e);
			throw new CreditAppException("UserServiceImpl.updateUserPwdByPwd---根据原密码重置密码异常", e);
		}
		return result;
	}


	//判断登录密码是否正确
	public String queryPwdByPhone(String userPhone) {
		String result = "";
		try {
			result = this.userDomainMapper.queryPwdByPhone(userPhone);
		} catch (Exception e) {
			logger.error("UserServiceImpl.queryPwdByPhone---查询登录密码异常", e);
			throw new CreditAppException("UserServiceImpl.queryPwdByPhone---查询登录密码异常", e);
		}
		return result;
	}
	
	
	//通过pwid,判断原密码是否正确
	public String queryOldPwdByPwid(String uapPwid) {
		String result = "";
		try {
			result = this.userDomainMapper.queryOldPwdByPwid(uapPwid);
		} catch (Exception e) {
			logger.error("UserServiceImpl.queryOldPwdByPwid---查询原登录密码异常", e);
			throw new CreditAppException("UserServiceImpl.queryOldPwdByPwid---查询原登录密码异常", e);
		}
		return result;
	}
	
}
