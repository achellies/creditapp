package com.wanda.creditapp.remote.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanda.creditapp.common.constant.ExceptionConstant;
import com.wanda.creditapp.common.constant.ProductConstant;
import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.requestmodel.product.PersonalReportModel;
import com.wanda.creditapp.common.requestmodel.product.ProductModel;
import com.wanda.creditapp.remote.service.ICallCreditRemoteService;
import com.wanda.creditapp.remote.util.ValidateUtil;

/**
 * 个人学历查询
 * @author xuxiaobin5
 *
 */
@Service(ProductConstant.productPCB004Service)
public class ProductPCB004ServiceImpl extends AbstractProductService{
	
	@Autowired
	private ICallCreditRemoteService callCreditRemoteService;
	
	private final String prod_id = "P_C_B004";
	
	private final String inf_id = "P_C_B004";
	
	

	@Override
	protected String validateBussinessParam(Map<String,String> param) {
		return ValidateUtil.validateProductModel(param, ProductConstant.name,ProductConstant.cardNo);
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
		if(!(model instanceof PersonalReportModel)){
			throw new CreditAppException(ExceptionConstant.parameterTypeMissmatch);
		}
		PersonalReportModel personalModel = (PersonalReportModel)model;
		Map<String,String> bussinessParam = new HashMap<String,String>();
		bussinessParam.put(ProductConstant.name, personalModel.getName());
		bussinessParam.put(ProductConstant.cardNo, personalModel.getCardNo());
		bussinessParam.put(ProductConstant.prod_id, prod_id);
		return bussinessParam;
	}

	@Override
	protected ICallCreditRemoteService getCallCreditRemoteService() {
		return this.callCreditRemoteService;
	}

}