package com.wanda.creditapp.remote.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanda.creditapp.common.constant.ExceptionConstant;
import com.wanda.creditapp.common.constant.ProductConstant;
import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.requestmodel.product.CompanyModel;
import com.wanda.creditapp.common.requestmodel.product.ProductModel;
import com.wanda.creditapp.common.util.StringUtils;
import com.wanda.creditapp.remote.service.ICallCreditRemoteService;
import com.wanda.creditapp.remote.util.ValidateUtil;

/**
 * 企业工商信息查询
 * province(省份代码),corpName(企业名称)必填，busiLicNo(营业执照号)和creditNo(社会统一信用号)二选一
 * @author xuxiaobin5
 *
 */
@Service(ProductConstant.productPBB078Service)
public class ProductPBB078ServiceImpl extends AbstractProductService{
	
	@Autowired
	private ICallCreditRemoteService callCreditRemoteService;
	
	private String prod_id = "P_B_B078";
	
	private String inf_id = "P_B_B078";

	@Override
	protected String validateBussinessParam(Map<String,String> param) {
		return ValidateUtil.validateProductModel(param, ProductConstant.province,ProductConstant.corpName);
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
	protected Map<String, String> buildBussinessParam(ProductModel productModel) {
		if(!(productModel instanceof CompanyModel)){
			throw new CreditAppException(ExceptionConstant.parameterTypeMissmatch);
		}
		CompanyModel model = (CompanyModel)productModel;
		Map<String,String> param = new HashMap<String,String>();
		param.put(ProductConstant.province, model.getProvince());
		param.put(ProductConstant.corpName, model.getName());
		if(!StringUtils.isNullOrEmpty(model.getBusiLicNo())){
			param.put(ProductConstant.busiLicNo, model.getBusiLicNo());
		}
		if(!StringUtils.isNullOrEmpty(model.getCreditNo())){
			param.put(ProductConstant.creditNo, model.getCreditNo());
		}
		param.put(ProductConstant.prod_id, prod_id);
		return param;
	}

	@Override
	protected ICallCreditRemoteService getCallCreditRemoteService() {
		return this.callCreditRemoteService;
	}

}
