package com.wanda.creditapp.remote.dao;

import java.util.List;

import com.wanda.creditapp.remote.domain.CarInfoDomain;


public interface CarInfoMapper {

	int deleteByPrimaryKey(Integer id);

    int insert(CarInfoDomain record);

    CarInfoDomain selectByPrimaryKey(Integer id);

    List<CarInfoDomain> selectAll();

    int updateByPrimaryKey(CarInfoDomain record);

	void saveCarInfo(CarInfoDomain carInfoDomain);
	

}
