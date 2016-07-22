package com.wanda.creditapp.remote.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.wanda.creditapp.common.constant.ProductConstant;
import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.requestmodel.product.AccumulationFundModel;
import com.wanda.creditapp.common.responsemodel.product.ProductResponseModel;
import com.wanda.creditapp.common.util.JsonUtil;
import com.wanda.creditapp.remote.dto.AccumulationFundField;
import com.wanda.creditapp.remote.dto.AccumulationFundTabs;
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
	public ProductResponseModel queryAccumulationFund(Integer accumulationAccountId) throws CreditAppException {
		AccumulationAccount account = accumulationService.queryAccumulationAccountById(accumulationAccountId);
		AccumulationFundTabs tabs = JsonUtil.buildNonNullBinder().getJsonToObject(account.getTabs(), AccumulationFundTabs.class);
//		Map<String,String>
		return null;
	}

	/**
	 * 根据获取的指定城市的公积金登陆方式结果及本地数据组装提交采集公积金信息请求的参数
	 * @param retdata 用regioncode获取的指定城市的公积金登陆方式
	 * @param domain 本地存储的公积金账户信息
	 * @return
	 */
	private AccumulationFundModel buildGatherRequestParam(Map<String,Object> retdata,AccumulationAccount account){/*
		AccumulationFundModel model = new AccumulationFundModel();
		model.setName(domain.getRealName());
//		model.set
		return null;
	*/
		return null;}

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
