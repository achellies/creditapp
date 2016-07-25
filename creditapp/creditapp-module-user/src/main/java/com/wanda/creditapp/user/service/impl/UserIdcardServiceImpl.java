package com.wanda.creditapp.user.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.util.BeanUtil;
import com.wanda.creditapp.user.dao.UserIdcardMapper;
import com.wanda.creditapp.user.domain.UserIdcard;
import com.wanda.creditapp.user.service.IUserIdcardService;
@Service("userIdcardService")
public class UserIdcardServiceImpl implements IUserIdcardService {
	private static final Logger logger = Logger.getLogger(UserIdcardServiceImpl.class);
	@Resource
	private UserIdcardMapper userIdcardMapper;

	@Override
	public UserIdcard queryIdcardbyUserId(int userId) {
		UserIdcard userIdcard = null;
		try {
			userIdcard = userIdcardMapper.selectByUserId(userId);
		} catch (Exception e) {
			logger.error("UserIdcardServiceImpl.queryIdcardbyUserId--查用户身份证记录异常", e);
			throw new CreditAppException("UserIdcardServiceImpl.queryIdcardbyUserId---更新实名信息异常异常", e);
		}
		return userIdcard;
	}

	/* 
	 * 有就更新，没有就保存
	 */
	@Override
	public int saveOrUpdate(UserIdcard record) {
		int result = -1;
		UserIdcard userIdcardInDb=userIdcardMapper.selectByPrimaryKey(record.getId());
		if(null==userIdcardInDb){
			try {
				result = userIdcardMapper.insert(record);
			} catch (Exception e) {
				logger.error("UserIdcardServiceImpl.insert---新增身份证异常", e);
				throw new CreditAppException("UserIdcardServiceImpl.insert---新增身份证异常", e);
			}
		}else{
			try {
				BeanUtil.copyAllPropertysNotNull(userIdcardInDb, record);
				result = userIdcardMapper.updateByPrimaryKey(record);
			} catch (Exception e) {
				logger.error("UserIdcardServiceImpl.saveOrUpdate---拷贝属性异常", e);
				throw new CreditAppException("UserIdcardServiceImpl.saveOrUpdate---拷贝属性异常", e);
			}
		}
		return result;
	}

}
