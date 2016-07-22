package com.wanda.creditapp.msg.dao;

import com.wanda.creditapp.msg.domain.VerifyCode;
import java.util.List;

public interface VerifyCodeMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(VerifyCode record);

	VerifyCode selectByPrimaryKey(Integer id);

	List<VerifyCode> selectAll();

	int updateByPrimaryKey(VerifyCode record);

	VerifyCode selectValidVerifyCode(String verifyReceiver);

	/**
	 * 使指定接收者指定类型的验证码失效
	 * @param verifyReceiver
	 * @param type
	 * @return
	 */
	int invalidateVerifyCode(String verifyReceiver,int type);
	
	/**
	 * 更新有效的验证码，更新到了，标识验证成功，没更新到标识失败
	 */
	int updateValidVerifyCode(String verifyReceiver,String verifyCode,int verifyType);
}
