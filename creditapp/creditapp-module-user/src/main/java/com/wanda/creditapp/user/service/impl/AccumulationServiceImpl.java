package com.wanda.creditapp.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wanda.creditapp.common.constant.ExceptionConstant;
import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.user.dao.AccumulationAccountMapper;
import com.wanda.creditapp.user.domain.AccumulationAccount;
import com.wanda.creditapp.user.service.IAccumulationService;

/**
 * 公积金账户的本地数据库相关操作
 * @author xuxiaobin5
 *
 */
@Service
public class AccumulationServiceImpl implements IAccumulationService{

	@Resource
	private AccumulationAccountMapper mapper;
	
	@Override
	public void addAccumulationAccount(AccumulationAccount accumulationAccount) throws CreditAppException{
		
		int result = mapper.insert(accumulationAccount);
		
		if(result==0){
			throw new CreditAppException(ExceptionConstant.addRecordFail);
		}
		
	}

	@Override
	public List<AccumulationAccount> queryAccumulationAccountByUserId(Integer userId) {
		return mapper.selectByUserId(userId);
	}

	@Override
	public AccumulationAccount queryAccumulationAccountById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}
	
	/**
	 * 删除公积金账号,需要验证该公积金账号是否属于当前登录用户
	 */
	@Override
	public void deleteAccumulationAccount(Integer accumulationAccountId,Integer userId) throws CreditAppException {
		AccumulationAccount account = mapper.selectByPrimaryKey(accumulationAccountId);
		if(account==null){
			throw new CreditAppException(ExceptionConstant.afa_empty_account);
		}
		if(!account.getUserId().equals(userId)){
			throw new CreditAppException(ExceptionConstant.afa_wrong_account);
		}
		int result = mapper.deleteByPrimaryKey(accumulationAccountId);
		if(result==0){
			throw new CreditAppException(ExceptionConstant.afa_delete_fail);
		}
		
	}
	
	public List<AccumulationAccount> queryAccountByUserId(Integer userId) throws CreditAppException{
		return mapper.selectByUserId(userId);		
	}
	
	

}
