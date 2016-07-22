package com.wanda.creditapp.remote.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.requestmodel.product.CompanyModel;
import com.wanda.creditapp.common.responsemodel.product.ProductResponseModel;

@Service
public interface ICompanyReportService {
	
	public List<ProductResponseModel> queryCompanyReport(CompanyModel companyModel) throws CreditAppException;

}
