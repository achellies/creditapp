package com.wanda.creditapp.user.service;

import java.util.List;

import com.wanda.creditapp.user.domain.CarIllegalDomain;

public interface ICarIllegalService {

	List<CarIllegalDomain> queryIllegalCar(CarIllegalDomain carDomain);
	

	

}
