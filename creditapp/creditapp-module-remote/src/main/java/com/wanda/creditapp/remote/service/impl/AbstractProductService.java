package com.wanda.creditapp.remote.service.impl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import com.wanda.creditapp.common.constant.ExceptionConstant;
import com.wanda.creditapp.common.constant.ProductConstant;
import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.requestmodel.product.ProductModel;
import com.wanda.creditapp.common.responsemodel.product.ProductResponseModel;
import com.wanda.creditapp.common.util.JsonUtil;
import com.wanda.creditapp.common.util.StringUtils;
import com.wanda.creditapp.remote.service.ICallCreditRemoteService;
import com.wanda.creditapp.remote.service.IProductService;
import com.wanda.creditapp.remote.util.ValidateUtil;

public abstract class AbstractProductService implements IProductService{
	
	private static final Logger log = Logger.getLogger(AbstractProductService.class);
	
	@Value("${acct_id}")
	private String acct_id;
	
	@Override
	public final ProductResponseModel productInvoke(ProductModel productModel) throws CreditAppException{
		Map<String,String> params = buildSystemParam();
		params.put(ProductConstant.acct_id, acct_id);
		Map<String,String> datas = buildBussinessParam(productModel);
		ICallCreditRemoteService callCreditRemoteService = getCallCreditRemoteService();//TODO 调用远程服务的类应该统一放在本类下,但是用@Autowired行不通,会报空指针异常,后续看看如何解决
		String validateResult = validateBussinessParam(datas);
		if(!StringUtils.isNullOrEmpty(validateResult)){
			log.error(validateResult);
			throw new CreditAppException(ExceptionConstant.errorValidateFail.getErrorCode(),validateResult);
		}
		Map<String,Object> result = callCreditRemoteService.callRemoteCredit(params, datas);
		String jsonResult = JsonUtil.buildNonNullBinder().toJson(result);
		ProductResponseModel model = JsonUtil.buildNonNullBinder().getJsonToObject(jsonResult, ProductResponseModel.class);
		return model;
	}
	
	/**
	 * 该验证只是为了避免用错误的参数调用征信业务系统，减少交互，返回的错误信息不会给用户看，只会在日志中体现，错误将统一包装成参数错误
	 * @param productModel
	 * @return
	 */
	protected abstract String validateBussinessParam(Map<String,String> datas);
	
	protected abstract Map<String,String> buildSystemParam();
	
	protected abstract Map<String,String> buildBussinessParam(ProductModel productModel);
	
	protected abstract ICallCreditRemoteService getCallCreditRemoteService();
}
