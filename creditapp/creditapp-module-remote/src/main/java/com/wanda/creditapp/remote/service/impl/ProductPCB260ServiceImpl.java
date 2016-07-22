package com.wanda.creditapp.remote.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanda.creditapp.common.constant.ProductConstant;
import com.wanda.creditapp.common.requestmodel.product.ProductModel;
import com.wanda.creditapp.remote.service.ICallCreditRemoteService;
import com.wanda.creditapp.remote.util.ValidateUtil;

/**
 * 获取目前支持公积金查询的城市信息
 * @author xuxiaobin5
 *
 */
@Service(ProductConstant.productPCB260Service)
public class ProductPCB260ServiceImpl extends AbstractProductService{
	
	private final String prod_id = "P_C_B260";
	
	private final String inf_id = "P_C_B260";
	
	@Autowired
	private ICallCreditRemoteService callCreditRemoteService;

	@Override
	protected String validateBussinessParam(Map<String,String> param) {
		return null;
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
		Map<String,String> param = new HashMap<String,String>();
		param.put(ProductConstant.prod_id, prod_id);
		return param;
	}

	@Override
	protected ICallCreditRemoteService getCallCreditRemoteService() {
		return this.callCreditRemoteService;
	}

}
