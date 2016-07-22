package com.wanda.creditapp.controller.product;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanda.creditapp.common.constant.CommonConstant;
import com.wanda.creditapp.common.constant.ProductConstant;
import com.wanda.creditapp.common.constant.ResponseConstant;
import com.wanda.creditapp.common.controller.BaseController;
import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.requestmodel.product.CompanyModel;
import com.wanda.creditapp.common.response.DataResponse;
import com.wanda.creditapp.common.responsemodel.product.ProductResponseModel;
import com.wanda.creditapp.remote.service.ICompanyReportService;

@Controller
@RequestMapping("/company")
public class CompanyReportController extends BaseController{
	
	private static final Logger log = Logger.getLogger(CompanyReportController.class);
	
	@Autowired
	private ICompanyReportService companyReportService;
	
	@ResponseBody
	@RequestMapping("/report")
	public DataResponse queryCompanyReportService(CompanyModel companyModel){
		DataResponse response = new DataResponse();
		try{
			List<ProductResponseModel> responseModelList = companyReportService.queryCompanyReport(companyModel);
			response.setResultCode(ResponseConstant.RESPONSE_SUCCESS.getCode());
			response.setResultMessage(ResponseConstant.RESPONSE_SUCCESS.getMsg());
			response.getDataMap().put(ProductConstant.responseList, responseModelList);
		}catch(CreditAppException e){
			response.setResultCode(ResponseConstant.RESPONSE_FAIL.getCode());
			response.setResultMessage(ResponseConstant.RESPONSE_SUCCESS.getMsg());
			response.getDataMap().put(CommonConstant.RESPONSE_ERRORCODE_KEY, e.getErrCode());
			response.getDataMap().put(CommonConstant.RESPONSE_ERRORMESSAGE_KEY, e.getErrMsg());
			response.getDataMap().put(CommonConstant.RESPONSE_ERRORDISPLAY_KEY, 1);
		}
		return response;
	}
	

}
