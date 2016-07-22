package com.wanda.creditapp.remote.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wanda.creditapp.common.constant.ExceptionConstant;
import com.wanda.creditapp.common.constant.ProductConstant;
import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.requestmodel.product.CompanyModel;
import com.wanda.creditapp.common.requestmodel.product.ProductModel;
import com.wanda.creditapp.remote.service.ICallCreditRemoteService;
import com.wanda.creditapp.remote.util.ValidateUtil;

@Service(ProductConstant.productPBB001Service)
public class ProductPBB001ServiceImpl extends AbstractProductService{

	private final String prod_id = "P_B_B001";
	
	private final String inf_id = "P_B_B001";
	
	private ICallCreditRemoteService callCreditRemoteService;
	
	@Override
	protected String validateBussinessParam(Map<String, String> datas) {
		return ValidateUtil.validateProductModel(datas, ProductConstant.regicode,ProductConstant.keyType);
	}

	@Override
	protected Map<String, String> buildSystemParam() {
		Map<String,String> systemParams = new HashMap<String,String>();
		systemParams.put(ProductConstant.prod_id, prod_id);
		systemParams.put(ProductConstant.inf_id, inf_id);
		return systemParams;
	}

	@Override
	protected Map<String, String> buildBussinessParam(ProductModel productModel) {
		if(!(productModel instanceof CompanyModel)){
			throw new CreditAppException(ExceptionConstant.parameterTypeMissmatch);
		}
		CompanyModel model = (CompanyModel)productModel;
		Map<String,String> datas = new HashMap<String,String>();
		datas.put(ProductConstant.regicode, model.getName());
		datas.put(ProductConstant.keyType, model.getKeyType());
		datas.put(ProductConstant.prod_id, prod_id);
		return null;
	}

	@Override
	protected ICallCreditRemoteService getCallCreditRemoteService() {
		return this.callCreditRemoteService;
	}

}
