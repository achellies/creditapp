package com.wanda.creditapp.controller.product;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanda.creditapp.common.constant.CommonConstant;
import com.wanda.creditapp.common.constant.ProductConstant;
import com.wanda.creditapp.common.constant.ResponseConstant;
import com.wanda.creditapp.common.controller.BaseController;
import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.requestmodel.product.CentralBankReportModel;
import com.wanda.creditapp.common.response.DataResponse;
import com.wanda.creditapp.common.responsemodel.product.ProductResponseModel;
import com.wanda.creditapp.remote.service.ICentralBankReportService;

@Controller
@RequestMapping("centralBankReport")
public class CentralBankReportController extends BaseController{
	
	private static final Logger log = Logger.getLogger(CentralBankReportController.class);
	
	@Autowired
	private ICentralBankReportService centralBankReportService;
	
	@RequestMapping(value="personReport",method={RequestMethod.POST})
	@ResponseBody
	public DataResponse queryPersonalReport(CentralBankReportModel model){
		DataResponse response = new DataResponse();
		try{
			ProductResponseModel result = centralBankReportService.queryPersonalReport(model);
			response.setResultCode(ResponseConstant.RESPONSE_SUCCESS.getCode());
			response.setResultMessage(ResponseConstant.RESPONSE_SUCCESS.getMsg());
			response.getDataMap().put(ProductConstant.response, result);
		}catch(CreditAppException e){
			log.error("fetch central bank personal report occur an exception:", e);
			response.setResultCode(ResponseConstant.RESPONSE_FAIL.getCode());
			response.setResultMessage(e.getErrMsg());
			response.getDataMap().put(CommonConstant.RESPONSE_ERRORCODE_KEY, e.getErrCode());
			response.getDataMap().put(CommonConstant.RESPONSE_ERRORMESSAGE_KEY, e.getErrMsg());
			response.getDataMap().put(CommonConstant.RESPONSE_ERRORDISPLAY_KEY, 1);
		}
		return response;	
	}

}
