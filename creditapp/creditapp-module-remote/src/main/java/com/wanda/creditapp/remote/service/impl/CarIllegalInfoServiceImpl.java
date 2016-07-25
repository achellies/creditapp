package com.wanda.creditapp.remote.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.wanda.creditapp.common.constant.ProductConstant;
import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.requestmodel.product.CarIllegalInfoModel;
import com.wanda.creditapp.common.responsemodel.product.ProductResponseModel;
import com.wanda.creditapp.remote.service.ICarIllegalInfoService;
import com.wanda.creditapp.remote.service.IProductService;

@Service("carIllegalInfoService")
public class CarIllegalInfoServiceImpl implements ICarIllegalInfoService {

	
	@Autowired
	@Qualifier(ProductConstant.productPCB108Service)
	private IProductService productPCB108Service;   //车辆违章服务
	
	
	public List<ProductResponseModel> queryCarIllegalInfo(CarIllegalInfoModel carIllegalInfoModel) 
					throws CreditAppException{
		List<ProductResponseModel> modelList = new ArrayList<ProductResponseModel>();
		ProductResponseModel companyBasicResult = productPCB108Service.productInvoke(carIllegalInfoModel);
		modelList.add(companyBasicResult);
		return modelList;
	}

}
