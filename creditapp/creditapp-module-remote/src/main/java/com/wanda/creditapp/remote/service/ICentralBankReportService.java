package com.wanda.creditapp.remote.service;

import java.util.Map;

import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.requestmodel.product.CentralBankReportModel;
import com.wanda.creditapp.common.responsemodel.product.ProductResponseModel;

/**
 * 查询人行个人征信报告,该服务需要连续串行调用P_C_B265,P_C_Q015;
 * @author xuxiaobin5
 *
 */
public interface ICentralBankReportService {
	
	public ProductResponseModel queryPersonalReport(CentralBankReportModel model) throws CreditAppException;

}
