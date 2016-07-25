package com.wanda.creditapp.remote.service;

import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.responsemodel.product.ProductResponseModel;

public interface IAccumulationFundQueryService {
	
	public ProductResponseModel queryAccumulationFund(Integer accumulationAccountId,Integer userId) throws CreditAppException;
	
	

}
