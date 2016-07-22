package com.wanda.creditapp.remote.service;

import java.util.List;
import java.util.Map;

import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.requestmodel.product.PersonalReportModel;
import com.wanda.creditapp.common.responsemodel.product.ProductResponseModel;

public interface IPersonalReportService {
	
	public List<ProductResponseModel> queryPersonalReport(PersonalReportModel model) throws CreditAppException;

}
