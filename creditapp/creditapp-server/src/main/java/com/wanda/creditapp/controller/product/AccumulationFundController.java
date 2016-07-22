package com.wanda.creditapp.controller.product;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanda.creditapp.common.constant.CommonConstant;
import com.wanda.creditapp.common.constant.ProductConstant;
import com.wanda.creditapp.common.constant.ResponseConstant;
import com.wanda.creditapp.common.controller.BaseController;
import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.requestmodel.product.AccumulationFundModel;
import com.wanda.creditapp.common.requestmodel.product.ProductModel;
import com.wanda.creditapp.common.response.DataResponse;
import com.wanda.creditapp.common.responsemodel.product.ProductResponseModel;
import com.wanda.creditapp.common.util.JsonUtil;
import com.wanda.creditapp.remote.dto.AccumulationFundTabs;
import com.wanda.creditapp.remote.service.IProductService;
import com.wanda.creditapp.user.domain.AccumulationAccount;
import com.wanda.creditapp.user.service.IAccumulationService;

@Controller
@RequestMapping("accumulationFund")
public class AccumulationFundController extends BaseController{
	
	private static final Logger log = Logger.getLogger(AccumulationFundController.class);
	
	@Autowired
	@Qualifier(ProductConstant.productPCB260Service)
	private IProductService productPCB260Service;
	
	@Autowired
	@Qualifier(ProductConstant.productPCB261Service)
	private IProductService productPCB261Service;
	
	@Autowired
	private IAccumulationService accumulationService;
	
	@RequestMapping(value="/supportedCities",method=RequestMethod.POST)
	@ResponseBody
	public DataResponse querySupportedCities(){
		DataResponse response = new DataResponse();
		ProductModel model = new AccumulationFundModel();
		try{
			ProductResponseModel result = productPCB260Service.productInvoke(model);
			response.getDataMap().put(ProductConstant.response, result);
			response.setResultCode(ResponseConstant.RESPONSE_SUCCESS.getCode());
			response.setResultMessage(ResponseConstant.RESPONSE_SUCCESS.getMsg());
		}catch(CreditAppException e){
			log.error("query supported cities occur an CreditAppException:", e);
			response.setResultCode(ResponseConstant.RESPONSE_FAIL.getCode());
			response.setResultMessage(ResponseConstant.RESPONSE_ERRORCODE_PWD_FAIL.getMsg());
			response.getDataMap().put(CommonConstant.RESPONSE_ERRORCODE_KEY, e.getErrCode());
			response.getDataMap().put(CommonConstant.RESPONSE_ERRORMESSAGE_KEY, e.getErrMsg());
			response.getDataMap().put(CommonConstant.RESPONSE_ERRORDISPLAY_KEY, 1);
		}
		return response;
	}
	
	@RequestMapping(value="/addAccumulationAccount",method=RequestMethod.POST)
	@ResponseBody
	public DataResponse addAccumulationAccount(Map<String,String> datas,AccumulationFundTabs tabs){
		DataResponse response = new DataResponse();
		AccumulationAccount account = buildAccumulationAccount(datas,tabs);
		try{
			accumulationService.addAccumulationAccount(account);
			response.setResultCode(ResponseConstant.RESPONSE_SUCCESS.getCode());
			response.setResultMessage(ResponseConstant.RESPONSE_SUCCESS.getMsg());
		}catch(CreditAppException e){
			response.setResultCode(ResponseConstant.RESPONSE_FAIL.getCode());
			response.setResultMessage(ResponseConstant.RESPONSE_FAIL.getMsg());
			response.getDataMap().put(CommonConstant.RESPONSE_ERRORCODE_KEY, e.getErrCode());
			response.getDataMap().put(CommonConstant.RESPONSE_ERRORMESSAGE_KEY, e.getErrMsg());
			response.getDataMap().put(CommonConstant.RESPONSE_ERRORDISPLAY_KEY, 1);
		}
		return response;
	}
	
	private AccumulationAccount buildAccumulationAccount(Map<String,String> datas,AccumulationFundTabs tabs){
		AccumulationAccount account = new AccumulationAccount();
		account.setTabs(JsonUtil.buildNonNullBinder().toJson(tabs));
		account.setDatas(JsonUtil.buildNonNullBinder().toJson(datas));
		account.setUserId(1);
		return account;
	}
}
