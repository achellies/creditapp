package com.wanda.creditapp.remote.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanda.creditapp.common.constant.ExceptionConstant;
import com.wanda.creditapp.common.constant.ProductConstant;
import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.requestmodel.product.ProductModel;
import com.wanda.creditapp.remote.model.AccumulationFundModel;
import com.wanda.creditapp.remote.service.ICallCreditRemoteService;
import com.wanda.creditapp.remote.util.ValidateUtil;

/**
 * 根据城市编码获取当前城市公积金登录方式及表单
 * @author xuxiaobin5
 *
 */
@Service(ProductConstant.productPCB261Service)
public class ProductPCB261ServiceImpl extends AbstractProductService{
	
	private final String prod_id = "P_C_B261";
	
	private final String inf_id = "P_C_B261";
	
	@Autowired
	private ICallCreditRemoteService callCreditRemoteService;

	@Override
	protected String validateBussinessParam(Map<String,String> param) {
		// TODO Auto-generated method stub
		return ValidateUtil.validateProductModel(param, ProductConstant.regioncode);
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
		param.put(ProductConstant.regioncode, accumulationFundModel.getRegioncode());
		param.put(ProductConstant.prod_id, prod_id);
		return param;
	}

	@Override
	protected ICallCreditRemoteService getCallCreditRemoteService() {
		// TODO Auto-generated method stub
		return this.callCreditRemoteService;
	}

}
