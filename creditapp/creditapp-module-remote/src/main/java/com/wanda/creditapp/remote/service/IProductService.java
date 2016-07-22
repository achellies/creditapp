package com.wanda.creditapp.remote.service;

import java.util.Map;

import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.common.requestmodel.product.ProductModel;
import com.wanda.creditapp.common.responsemodel.product.ProductResponseModel;

/**
 * 单个产品调用的服务
 * @author xuxiaobin5
 *
 */
public interface IProductService {
	
	public ProductResponseModel productInvoke(ProductModel productModel) throws CreditAppException;
	
}
