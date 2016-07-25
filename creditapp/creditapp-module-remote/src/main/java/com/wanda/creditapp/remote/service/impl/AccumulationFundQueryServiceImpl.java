package com.wanda.creditapp.remote.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.wanda.creditapp.common.constant.ExceptionConstant;
import com.wanda.creditapp.common.constant.ProductConstant;
import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.responsemodel.product.ProductResponseModel;
import com.wanda.creditapp.common.util.JsonUtil;
import com.wanda.creditapp.remote.dto.AccumulationFundTabs;
import com.wanda.creditapp.remote.model.AccumulationFundModel;
import com.wanda.creditapp.remote.service.IAccumulationFundQueryService;
import com.wanda.creditapp.remote.service.IProductService;
import com.wanda.creditapp.user.domain.AccumulationAccount;
import com.wanda.creditapp.user.service.IAccumulationService;

/**
 * 调用征信业务系统查询公积金
 * @author xuxiaobin5
 *
 */
@Service
public class AccumulationFundQueryServiceImpl implements IAccumulationFundQueryService{

	@Autowired
	private IAccumulationService accumulationService;

	@Autowired
	@Qualifier(ProductConstant.productPCB261Service)
	private IProductService productPCB261Service;

	@Autowired
	@Qualifier(ProductConstant.productPCB262Service)
	private IProductService productPCB262Service;

	@Autowired
	@Qualifier(ProductConstant.productPCQ010Service)
	private IProductService productPCQ010Service;

	@Override
	public ProductResponseModel queryAccumulationFund(Integer accumulationAccountId,Integer userId) throws CreditAppException {
		AccumulationAccount account = accumulationService.queryAccumulationAccountById(accumulationAccountId);
		if(account==null){
			throw new CreditAppException(ExceptionConstant.afa_empty_account);
		}
		if(!account.getUserId().equals(userId)){
			throw new CreditAppException(ExceptionConstant.afa_wrong_account);
		}
		AccumulationFundModel gatherModel = buildGatherRequestParam(account);
		ProductResponseModel gatherResponse = productPCB262Service.productInvoke(gatherModel);
		if(gatherResponse==null || gatherResponse.getRetdata()==null){
			throw new CreditAppException(ExceptionConstant.crs_empty_result);
		}
		AccumulationFundModel model = buildRequestParam(gatherResponse.getRetdata());
		ProductResponseModel response = productPCQ010Service.productInvoke(model);
		return response;
	}

	/**
	 * 根据获取的指定城市的公积金登陆方式结果及本地数据组装提交采集公积金信息请求的参数
	 * @param retdata 用regioncode获取的指定城市的公积金登陆方式
	 * @param domain 本地存储的公积金账户信息
	 * @return
	 */
	private AccumulationFundModel buildGatherRequestParam(AccumulationAccount account){
		AccumulationFundModel model = new AccumulationFundModel();
		model.setData((Map<String,String>)JsonUtil.buildNonNullBinder().getJsonToMap(account.getDatas(), String.class, String.class));
		model.setTabs(JsonUtil.buildNonNullBinder().getJsonToObject(account.getTabs(), AccumulationFundTabs.class));
		model.setPassword(account.getAccountPassword());
		return model;
	}

	/**
	 * 根据发出采集请求获取的request_id构建正式获取结果的参数
	 * @param retdata 发出采集请求获取的retdata
	 * @return
	 */
	private AccumulationFundModel buildRequestParam(Map<String,Object> retdata){
		AccumulationFundModel model = new AccumulationFundModel();
		model.setRequest_id(retdata.get(ProductConstant.request_id).toString());
		return model;
	}

}
