package com.wanda.creditapp.remote.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanda.creditapp.common.constant.ExceptionConstant;
import com.wanda.creditapp.common.constant.ProductConstant;
import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.requestmodel.product.CarIllegalInfoModel;
import com.wanda.creditapp.common.requestmodel.product.ProductModel;
import com.wanda.creditapp.remote.service.ICallCreditRemoteService;
import com.wanda.creditapp.remote.util.ValidateUtil;

/**
 * 提供车辆违章信息查询
 *
 */
@Service(ProductConstant.productPCB108Service)
public class ProductPCB108ServiceImpl extends AbstractProductService{

	private final String prod_id = "P_C_B108";
		
    private final String inf_id = "P_C_B108";
	
	@Autowired
	private ICallCreditRemoteService callCreditRemoteService;
	
	//注入调用远程产品的service
	protected ICallCreditRemoteService getCallCreditRemoteService() {
		return this.callCreditRemoteService;
	}
	
	
	//该验证只是为了避免用错误的参数调用征信业务系统，减少交互，返回的错误信息不会给用户看，只会在日志中体现，错误将统一包装成参数错误
	protected String validateBussinessParam(Map<String, String> param) {
		return ValidateUtil.validateProductModel(param, 
				ProductConstant.carNumber,ProductConstant.carType,
				ProductConstant.carCode,ProductConstant.carEngine);
	}

	
	//系统参数,以后接入业务网关会用到
	protected Map<String, String> buildSystemParam() {
		//TODO 可能还有其他参数
		Map<String,String> systemParams = new HashMap<String,String>();
		systemParams.put(ProductConstant.prod_id, prod_id);
		systemParams.put(ProductConstant.inf_id, inf_id);
		return systemParams;
	}

	
	//根据传入的ProductModel构建业务参数,以Map<String,String>返回
	protected Map<String, String> buildBussinessParam(ProductModel model) {
		if(!(model instanceof CarIllegalInfoModel)){
			throw new CreditAppException(ExceptionConstant.parameterTypeMissmatch);
		}
		CarIllegalInfoModel carIllegalInfoModel = (CarIllegalInfoModel)model;
		Map<String,String> param = new HashMap<String,String>();
		param.put(ProductConstant.carNumber, carIllegalInfoModel.getCarNumber());
		param.put(ProductConstant.carType, carIllegalInfoModel.getCarType());
		param.put(ProductConstant.carCode, carIllegalInfoModel.getCarCode());
		param.put(ProductConstant.carEngine, carIllegalInfoModel.getCarEngine());
		param.put(ProductConstant.prod_id, prod_id);
		return param;
	}


	
	
}
