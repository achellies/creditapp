package com.wanda.creditapp.user.dao;

import java.util.List;

import com.wanda.creditapp.user.domain.CarIllegalDomain;

public interface CarIllegalMapper {

//	int saveCar(CarIllegalDomain carDomain);

	List<CarIllegalDomain> queryIllegalCar(CarIllegalDomain carDomain);
	
	
//	List<CarIllegalDomain> queryAllCar();

//	CarIllegalDomain queryCarById();

	

	
}
