package com.wanda.creditapp.user.service;

import java.util.List;

import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.user.domain.AccumulationAccount;

public interface IAccumulationService {
	
	public void addAccumulationAccount(AccumulationAccount domain) throws CreditAppException;
	
	public List<AccumulationAccount> queryAccumulationAccountByUserId(Integer userId);
	
	public AccumulationAccount queryAccumulationAccountById(Integer id);
	
	public void deleteAccumulationAccount(Integer accumulationAccountId,Integer userId) throws CreditAppException;
	
	public List<AccumulationAccount> queryAccountByUserId(Integer userId) throws CreditAppException;
	

}
