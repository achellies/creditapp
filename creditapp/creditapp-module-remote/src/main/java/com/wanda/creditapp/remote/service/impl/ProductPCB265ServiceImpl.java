package com.wanda.creditapp.remote.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanda.creditapp.common.constant.ExceptionConstant;
import com.wanda.creditapp.common.constant.ProductConstant;
import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.requestmodel.product.CentralBankReportModel;
import com.wanda.creditapp.common.requestmodel.product.ProductModel;
import com.wanda.creditapp.remote.service.ICallCreditRemoteService;
import com.wanda.creditapp.remote.util.ValidateUtil;

/**
 * P_C_B265 产品-人行个人征信报告-提交采集请求 返回request_id,用于正式发起请求获取数据
 * @author xuxiaobin5
 *
 */
@Service(ProductConstant.productPCB265Service)
public class ProductPCB265ServiceImpl extends AbstractProductService{
	
	@Autowired
	private ICallCreditRemoteService callCreditRemoteService;
	
	private final String prod_id = "P_C_B265";

	private final String inf_id = "P_C_B265";
	
	@Override
	protected String validateBussinessParam(Map<String,String> param) {
		return ValidateUtil.validateProductModel(param, ProductConstant.name,ProductConstant.cardNo,ProductConstant.mobile,ProductConstant.account,ProductConstant.password,ProductConstant.captcha);
	}

	@Override
	protected Map<String, String> buildSystemParam() {
		Map<String,String> systemParams = new HashMap<String,String>();
		systemParams.put(ProductConstant.prod_id, prod_id);
		systemParams.put(ProductConstant.inf_id, inf_id);
		return systemParams;
	}

	@Override
	protected Map<String, String> buildBussinessParam(ProductModel model) {
		if(!(model instanceof CentralBankReportModel)){
			throw new CreditAppException(ExceptionConstant.parameterTypeMissmatch);
		}
		CentralBankReportModel centralBankReportModel = (CentralBankReportModel)model;
		Map<String,String> param = new HashMap<String,String>();
		param.put(ProductConstant.name, centralBankReportModel.getName());
		param.put(ProductConstant.cardNo, centralBankReportModel.getCardNo());
		param.put(ProductConstant.mobile, centralBankReportModel.getMobile());
		param.put(ProductConstant.account, centralBankReportModel.getAccount());
		param.put(ProductConstant.password,centralBankReportModel.getPassword());
		param.put(ProductConstant.captcha, centralBankReportModel.getCaptcha());
		param.put(ProductConstant.prod_id, prod_id);
		return param;
	}

	@Override
	protected ICallCreditRemoteService getCallCreditRemoteService() {
		return this.callCreditRemoteService;
	}

}