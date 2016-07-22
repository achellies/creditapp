package com.wanda.creditapp.remote.service;

import java.util.Map;

import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.responsemodel.product.ProductResponseModel;

public interface IAccumulationFundQueryService {
	
	public ProductResponseModel queryAccumulationFund(Integer accumulationAccountId) throws CreditAppException;
	
	

}
