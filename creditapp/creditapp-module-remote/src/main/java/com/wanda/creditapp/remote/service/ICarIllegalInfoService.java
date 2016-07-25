package com.wanda.creditapp.remote.service;

import java.util.List;

import com.wanda.creditapp.common.requestmodel.product.CarIllegalInfoModel;
import com.wanda.creditapp.common.responsemodel.product.ProductResponseModel;

public interface ICarIllegalInfoService {

	List<ProductResponseModel> queryCarIllegalInfo(CarIllegalInfoModel carIllegalInfoModel);

}
