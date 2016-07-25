package com.wanda.creditapp.controller.product;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanda.creditapp.common.constant.CommonConstant;
import com.wanda.creditapp.common.constant.ProductConstant;
import com.wanda.creditapp.common.constant.ResponseConstant;
import com.wanda.creditapp.common.controller.BaseController;
import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.requestmodel.product.ProductModel;
import com.wanda.creditapp.common.response.DataResponse;
import com.wanda.creditapp.common.responsemodel.product.ProductResponseModel;
import com.wanda.creditapp.common.sercurity.User;
import com.wanda.creditapp.common.util.JsonUtil;
import com.wanda.creditapp.remote.dto.AccumulationFundTabs;
import com.wanda.creditapp.remote.model.AccumulationFundModel;
import com.wanda.creditapp.remote.service.IAccumulationFundQueryService;
import com.wanda.creditapp.remote.service.IProductService;
import com.wanda.creditapp.user.domain.AccumulationAccount;
import com.wanda.creditapp.user.service.IAccumulationService;
import com.wanda.creditapp.user.util.UserContext;

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
	
	@Autowired
	private IAccumulationFundQueryService queryService;
	
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
	
	@RequestMapping(value="/tab",method=RequestMethod.POST)
	@ResponseBody
	public DataResponse queryAccumulationFundTabs(@RequestBody AccumulationFundModel accumulationFundModel){		
		DataResponse response = new DataResponse();
		try{
			ProductResponseModel model = productPCB261Service.productInvoke(accumulationFundModel);
			response.setResultCode(ResponseConstant.RESPONSE_SUCCESS.getCode());
			response.setResultMessage(ResponseConstant.RESPONSE_SUCCESS.getMsg());
			response.getDataMap().put(ProductConstant.response, model);
		}catch(CreditAppException e){
			response.setResultCode(ResponseConstant.RESPONSE_FAIL.getCode());
			response.setResultMessage(ResponseConstant.RESPONSE_FAIL.getMsg());
			response.getDataMap().put(CommonConstant.RESPONSE_ERRORCODE_KEY, e.getErrCode());
			response.getDataMap().put(CommonConstant.RESPONSE_ERRORMESSAGE_KEY, e.getErrMsg());
			response.getDataMap().put(CommonConstant.RESPONSE_ERRORDISPLAY_KEY, 1);
		}
		return response;
	}
	
	/**
	 * 添加公积金账户,用{@code Map}接收,其中,key有固定的"tab","password";
	 * "tab"表示用户选择的公积金登陆表单,以json格式传入,格式和{@code AccumulationFundTabs}保持一致;
	 * "password"表示用户的公积金账户密码;
	 * 其他项表示{@code AccumulationFundTabs}中{@code List<AccumulationFundField>}中要求传入的参数;
	 * @param param
	 * @return
	 */
	@RequestMapping(value="/addAccumulationAccount",method=RequestMethod.POST)
	@ResponseBody
	public DataResponse addAccumulationAccount(@RequestBody Map<String,String> param){
		DataResponse response = new DataResponse();
		AccumulationAccount account = buildAccumulationAccount(param);
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
	
	@ResponseBody
	@RequestMapping(value="/detail",method=RequestMethod.POST)
	public DataResponse queryAccumulationFundDetail(@RequestBody AccumulationAccount account){
		DataResponse response = new DataResponse();
		User user = UserContext.getCurrentUser();
		if(user==null){
			response.setResultCode(ResponseConstant.RESPONSE_FAIL.getCode());
			response.setResultMessage(ResponseConstant.RESPONSE_FAIL.getMsg());
			response.getDataMap().put(CommonConstant.RESPONSE_ERRORCODE_KEY, "");//TODO 若获取不到当前登录用户,如何处理
			response.getDataMap().put(CommonConstant.RESPONSE_ERRORMESSAGE_KEY, "");
			response.getDataMap().put(CommonConstant.RESPONSE_ERRORDISPLAY_KEY, 1);
			return response;
		}
		try{
			ProductResponseModel responseModel = queryService.queryAccumulationFund(account.getId(), user.getId());
			response.setResultCode(ResponseConstant.RESPONSE_SUCCESS.getCode());
			response.setResultMessage(ResponseConstant.RESPONSE_SUCCESS.getMsg());
			response.getDataMap().put(ProductConstant.response, responseModel);
		}catch(CreditAppException e){
			response.setResultCode(ResponseConstant.RESPONSE_FAIL.getCode());
			response.setResultMessage(ResponseConstant.RESPONSE_FAIL.getMsg());
			response.getDataMap().put(CommonConstant.RESPONSE_ERRORCODE_KEY, e.getErrCode());
			response.getDataMap().put(CommonConstant.RESPONSE_ERRORMESSAGE_KEY, e.getErrMsg());
			response.getDataMap().put(CommonConstant.RESPONSE_ERRORDISPLAY_KEY, 1);
		}
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public DataResponse deleteAcccumulationAccount(@RequestBody AccumulationAccount account){
		DataResponse response = new DataResponse();
		User user = UserContext.getCurrentUser();
		if(user==null){
			response.setResultCode(ResponseConstant.RESPONSE_FAIL.getCode());
			response.setResultMessage(ResponseConstant.RESPONSE_FAIL.getMsg());
			response.getDataMap().put(CommonConstant.RESPONSE_ERRORCODE_KEY, "");//TODO 若获取不到当前登录用户,如何处理
			response.getDataMap().put(CommonConstant.RESPONSE_ERRORMESSAGE_KEY, "");
			response.getDataMap().put(CommonConstant.RESPONSE_ERRORDISPLAY_KEY, 1);
			return response;
		}
		try{
			accumulationService.deleteAccumulationAccount(account.getId(), user.getId());
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
	
	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public DataResponse queryAccountList(){
		DataResponse response = new DataResponse();
		User user = UserContext.getCurrentUser();
		//TODO 若user==null如何处理
		List<AccumulationAccount> accountList = accumulationService.queryAccountByUserId(user.getId());
		response.setResultCode(ResponseConstant.RESPONSE_SUCCESS.getCode());
		response.setResultMessage(ResponseConstant.RESPONSE_SUCCESS.getMsg());
		response.getDataMap().put(ProductConstant.responseList, accountList);
		return response;
	}
	
	
	
	/**
	 * 从前端接收添加公积金账户的相关信息,并转换为数据库中公积金账户的实体类
	 * @param param
	 * @return
	 */
	private AccumulationAccount buildAccumulationAccount(Map<String,String> param){
		User user = UserContext.getCurrentUser();
		AccumulationAccount account = new AccumulationAccount();
		String tabsJson = param.get(ProductConstant.tab);
		param.remove(ProductConstant.tab);
		String password = param.get(ProductConstant.password);
		param.remove(ProductConstant.password);
		account.setTabs(tabsJson);
		account.setAccountPassword(password);
		account.setDatas(JsonUtil.buildNonNullBinder().toJson(param));
		account.setUserId(user.getId());
		return account;
	}
}
