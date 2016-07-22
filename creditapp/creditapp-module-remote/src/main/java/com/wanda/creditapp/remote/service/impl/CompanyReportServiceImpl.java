package com.wanda.creditapp.remote.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.wanda.creditapp.common.constant.ProductConstant;
import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.requestmodel.product.CompanyModel;
import com.wanda.creditapp.common.responsemodel.product.ProductResponseModel;
import com.wanda.creditapp.remote.service.ICompanyReportService;
import com.wanda.creditapp.remote.service.IProductService;

@Service
public class CompanyReportServiceImpl implements ICompanyReportService{
	
	@Autowired
	@Qualifier(ProductConstant.productPBB078Service)
	private IProductService productPBB078Serivce;
	
	@Autowired
	@Qualifier(ProductConstant.productPBB007Service)
	private IProductService productPBB007Service;

	@Override
	public List<ProductResponseModel> queryCompanyReport(CompanyModel companyModel) throws CreditAppException {
		List<ProductResponseModel> modelList = new ArrayList<ProductResponseModel>();
		ProductResponseModel companyBasicResult = productPBB078Serivce.productInvoke(companyModel);
		modelList.add(companyBasicResult);
		ProductResponseModel companyNagetiveResult = productPBB007Service.productInvoke(companyModel);
		modelList.add(companyNagetiveResult);
		return modelList;
	}

}
