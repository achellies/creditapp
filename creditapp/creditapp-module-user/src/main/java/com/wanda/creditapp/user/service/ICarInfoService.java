package com.wanda.creditapp.user.service;

import java.util.List;

import com.wanda.creditapp.user.domain.CarInfoDomain;

public interface ICarInfoService {

	int saveCarInfo(CarInfoDomain carInfoDomain);

	List<CarInfoDomain> queryCarInfo(CarInfoDomain carInfoDomain);


}
