package com.wanda.creditapp.remote.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanda.creditapp.common.constant.ExceptionConstant;
import com.wanda.creditapp.common.constant.ProductConstant;
import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.requestmodel.product.CompanyModel;
import com.wanda.creditapp.common.requestmodel.product.PersonalReportModel;
import com.wanda.creditapp.common.requestmodel.product.ProductModel;
import com.wanda.creditapp.common.util.StringUtils;
import com.wanda.creditapp.remote.service.ICallCreditRemoteService;
import com.wanda.creditapp.remote.util.ValidateUtil;

/**
 * 
 * @author xuxiaobin5
 *
 */
@Service(ProductConstant.productPBB007Service)
public class ProductPBB007ServiceImpl extends AbstractProductService{
	
	private final String prod_id = "P_B_B007";
	
	private final String inf_id = "P_B_B007";
	
	private final String companyFlag = "b";
	
	private final String personalFlag = "c";
	
	@Autowired
	private ICallCreditRemoteService callCreditRemoteService;

	@Override
	protected String validateBussinessParam(Map<String,String> param) {
		if(StringUtils.isNullOrEmpty(param.get(ProductConstant.flag))){
			return "flag is empty";
		}else{
			if(param.get(ProductConstant.flag).equalsIgnoreCase(personalFlag)){
				return ValidateUtil.validateProductModel(param, ProductConstant.name,ProductConstant.idCard,ProductConstant.currentPage,ProductConstant.pageSize,ProductConstant.sourcet);
			}else if(param.get(ProductConstant.flag).equalsIgnoreCase(companyFlag)){
				return ValidateUtil.validateProductModel(param, ProductConstant.name,ProductConstant.currentPage,ProductConstant.pageSize,ProductConstant.sourcet);
			}
			return null;
		}
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
		CompanyModel companyModel;
		PersonalReportModel personalModel;
		if(productModel instanceof CompanyModel){
			companyModel = (CompanyModel)productModel;
			Map<String,String> companyParam = new HashMap<String,String>();
			companyParam.put(ProductConstant.name, companyModel.getName());
			companyParam.put(ProductConstant.idCard, companyModel.getName());
			if(companyModel.getPageSize()<5){
				companyModel.setPageSize(ProductConstant.default_min_pageSize);
			}else if(companyModel.getPageSize()>100){
				companyModel.setPageSize(ProductConstant.default_max_pageSize);
			}
			if(companyModel.getCurrentPage()<=0){
				companyModel.setCurrentPage(1);
			}
			companyParam.put(ProductConstant.currentPage, String.valueOf(companyModel.getCurrentPage()));
			companyParam.put(ProductConstant.pageSize, String.valueOf(companyModel.getPageSize()));
			companyParam.put(ProductConstant.sourcet, companyModel.getSourcet());
			companyParam.put(ProductConstant.flag, companyFlag);
			companyParam.put(ProductConstant.prod_id, prod_id);	
			return companyParam;
		}else if(productModel instanceof PersonalReportModel){
			personalModel = (PersonalReportModel)productModel;
			Map<String,String> personalParam = new HashMap<String,String>();
			personalParam.put(ProductConstant.name, personalModel.getName());
			personalParam.put(ProductConstant.idCard, personalModel.getCardNo());
			personalParam.put(ProductConstant.currentPage, String.valueOf(personalModel.getCurrentPage()));
			if(personalModel.getPageSize()<5){
				personalModel.setPageSize(ProductConstant.default_min_pageSize);
			}else if(personalModel.getPageSize()>100){
				personalModel.setPageSize(ProductConstant.default_max_pageSize);
			}
			if(personalModel.getCurrentPage()<=0){
				personalModel.setCurrentPage(1);
			}
			personalParam.put(ProductConstant.pageSize, String.valueOf(personalModel.getPageSize()));
			personalParam.put(ProductConstant.sourcet, personalModel.getSourcet());
			personalParam.put(ProductConstant.flag, personalFlag);
			return personalParam;
		}else{
			throw new CreditAppException(ExceptionConstant.parameterTypeMissmatch);
		}
	}

	@Override
	protected ICallCreditRemoteService getCallCreditRemoteService() {
		return this.callCreditRemoteService;
	}

}
