package com.wanda.creditapp.user.dao;

import java.util.List;

import com.wanda.creditapp.user.domain.CarInfoDomain;

public interface CarInfoMapper {

	int saveCarInfo(CarInfoDomain carInfoDomain);
	
	List<CarInfoDomain> queryCarInfo(CarInfoDomain carInfoDomain);
	

}
