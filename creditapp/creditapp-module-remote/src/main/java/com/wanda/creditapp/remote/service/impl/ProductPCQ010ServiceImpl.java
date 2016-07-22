package com.wanda.creditapp.remote.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanda.creditapp.common.constant.ExceptionConstant;
import com.wanda.creditapp.common.constant.ProductConstant;
import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.requestmodel.product.AccumulationFundModel;
import com.wanda.creditapp.common.requestmodel.product.ProductModel;
import com.wanda.creditapp.remote.service.ICallCreditRemoteService;
import com.wanda.creditapp.remote.util.ValidateUtil;

/**
 * 产品P_C_Q010调用服务,根据调用产品P_C_B262获取的交易序列号request_id获取原始的公积金信息
 * @author xuxiaobin5
 *
 */
@Service(ProductConstant.productPCQ010Service)
public class ProductPCQ010ServiceImpl extends AbstractProductService{
	
	@Autowired
	private ICallCreditRemoteService callCreditRemoteService;
	
	private final String prod_id = "P_C_Q010";
	
	private final String inf_id = "P_C_Q010";

	@Override
	protected String validateBussinessParam(Map<String,String> param) {
		return ValidateUtil.validateProductModel(param, ProductConstant.request_id);
	}

	@Override
	protected Map<String, String> buildSystemParam() {
		// TODO Auto-generated method stub
		Map<String,String> systemParams = new HashMap<String,String>();
		systemParams.put(ProductConstant.prod_id, prod_id);
		systemParams.put(ProductConstant.inf_id, inf_id);
		return systemParams;
	}

	@Override
	protected Map<String, String> buildBussinessParam(ProductModel model) {
		if(!(model instanceof AccumulationFundModel)){
			throw new CreditAppException(ExceptionConstant.parameterTypeMissmatch);
		}
		AccumulationFundModel accumulationFundModel = (AccumulationFundModel)model;
		Map<String,String> param = new HashMap<String,String>();
		param.put(ProductConstant.request_id, accumulationFundModel.getRequest_id());
		param.put(ProductConstant.prod_id, prod_id);
		return param;
	}

	@Override
	protected ICallCreditRemoteService getCallCreditRemoteService() {
		return this.callCreditRemoteService;
	}

}