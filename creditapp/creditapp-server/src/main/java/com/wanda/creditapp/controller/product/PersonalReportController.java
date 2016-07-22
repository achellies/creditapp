package com.wanda.creditapp.controller.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanda.creditapp.common.constant.CommonConstant;
import com.wanda.creditapp.common.constant.ProductConstant;
import com.wanda.creditapp.common.constant.ResponseConstant;
import com.wanda.creditapp.common.controller.BaseController;
import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.requestmodel.product.PersonalReportModel;
import com.wanda.creditapp.common.response.DataResponse;
import com.wanda.creditapp.common.responsemodel.product.ProductResponseModel;
import com.wanda.creditapp.remote.service.IPersonalReportService;

@Controller
@RequestMapping("personalReport")
public class PersonalReportController extends BaseController{
	
	private static final Logger log = Logger.getLogger(PersonalReportController.class);
	
	@Autowired
	private IPersonalReportService personalReportService;
	
	@RequestMapping(value="detail",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public DataResponse queryPersonalReport(@Validated PersonalReportModel model,BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return buildRspWithErrors(bindingResult);
		}
		DataResponse response = new DataResponse();
		Map<String,String> params = new HashMap<String,String>();
		params.put(ProductConstant.name, model.getName());
		params.put(ProductConstant.cardNo, model.getCardNo());
		try{
			List<ProductResponseModel> responseList = personalReportService.queryPersonalReport(model);
			response.setResultCode(ResponseConstant.RESPONSE_SUCCESS.getCode());
			response.setResultMessage(ResponseConstant.RESPONSE_SUCCESS.getMsg());
			response.getDataMap().put(ProductConstant.responseList, responseList);
		}catch(CreditAppException e){
			log.error("query personal report occur an CreditAppException:", e);
			response.setResultCode(ResponseConstant.RESPONSE_FAIL.getCode());
			response.setResultMessage(ResponseConstant.RESPONSE_FAIL.getMsg());
			response.getDataMap().put(CommonConstant.RESPONSE_ERRORCODE_KEY, e.getErrCode());
			response.getDataMap().put(CommonConstant.RESPONSE_ERRORMESSAGE_KEY, e.getErrMsg());
			response.getDataMap().put(CommonConstant.RESPONSE_ERRORDISPLAY_KEY, 1);
		}
		return response;
	}

}
