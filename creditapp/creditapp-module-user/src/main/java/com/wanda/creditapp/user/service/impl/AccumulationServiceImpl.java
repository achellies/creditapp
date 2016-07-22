package com.wanda.creditapp.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
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
	
	

}
