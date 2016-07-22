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
import com.wanda.creditapp.common.util.StringUtils;
import com.wanda.creditapp.remote.service.ICallCreditRemoteService;
import com.wanda.creditapp.remote.util.ValidateUtil;

@Service(ProductConstant.productPCB111Service)
public class ProductPCB111ServiceImpl extends AbstractProductService{
	
	@Autowired
	private ICallCreditRemoteService callCreditRemoteService;
	
	private String prod_id = "P_C_B111";
	
	private String inf_id = "P_C_B111";

	@Override
	protected String validateBussinessParam(Map<String,String> param) {
		return ValidateUtil.validateProductModel(param, ProductConstant.name,ProductConstant.cardNo,ProductConstant.licenseNo,ProductConstant.carType);
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
		bussinessParam.put(ProductConstant.licenseNo, personalModel.getLicenseNo());
		bussinessParam.put(ProductConstant.carType, personalModel.getCarType());
		if(!StringUtils.isNullOrEmpty(personalModel.getVin())){
			bussinessParam.put(ProductConstant.vin, personalModel.getVin());
		}
		if(!StringUtils.isNullOrEmpty(personalModel.getRegistTime())){
			bussinessParam.put(ProductConstant.registTime, personalModel.getRegistTime());
		}
		if(!StringUtils.isNullOrEmpty(personalModel.getCarDetail())){
			 bussinessParam.put(ProductConstant.carDetail, personalModel.getCarDetail());
		}
		if(!StringUtils.isNullOrEmpty(personalModel.getCarStatus())){
			bussinessParam.put(ProductConstant.carStatus, personalModel.getCarStatus());
		}
		bussinessParam.put(ProductConstant.prod_id, prod_id);
		return bussinessParam;
	}

	@Override
	protected ICallCreditRemoteService getCallCreditRemoteService() {
		return this.callCreditRemoteService;
	}

}
