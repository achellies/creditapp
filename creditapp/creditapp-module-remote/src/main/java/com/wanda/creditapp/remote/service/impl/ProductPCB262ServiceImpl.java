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
 * 产品P_C_B262调用服务,提交公积金采集请求,返回交易序列号,供后续获取公积金结果
 * @author xuxiaobin5
 *
 */
@Service(ProductConstant.productPCB262Service)
public class ProductPCB262ServiceImpl extends AbstractProductService{
	
	@Autowired
	private ICallCreditRemoteService callCreditRemoteService;
	
	private final String prod_id = "P_C_B262";
	
	private final String inf_id = "P_C_B262";

	@Override
	protected String validateBussinessParam(Map<String,String> param) {
		return ValidateUtil.validateProductModel(param, ProductConstant.website_en,ProductConstant.website_sort,ProductConstant.submit_type);
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
		param.put(ProductConstant.website_en, accumulationFundModel.getWebsite_en());
		param.put(ProductConstant.website_sort, accumulationFundModel.getWebsite_sort());
		param.put(ProductConstant.submit_type, accumulationFundModel.getSubmit_type());
		param.put(ProductConstant.id_card_num, accumulationFundModel.getId_card_num());
		param.put(ProductConstant.name, accumulationFundModel.getName());
		param.put(ProductConstant.account, accumulationFundModel.getAccount());
		param.put(ProductConstant.password, accumulationFundModel.getPassword());
		param.put(ProductConstant.cell_phone_num, accumulationFundModel.getCell_phone_num());
		param.put(ProductConstant.prod_id, prod_id);
		return param;
	}

	@Override
	protected ICallCreditRemoteService getCallCreditRemoteService() {
		return this.callCreditRemoteService;
	}

}
