package com.wanda.creditapp.remote.dao;

import java.util.List;

import com.wanda.creditapp.remote.domain.CarIllegalDomain;


public interface CarIllegalMapper {

	int deleteByPrimaryKey(Integer id);

    int insert(CarIllegalDomain record);

    CarIllegalDomain selectByPrimaryKey(Integer id);

    List<CarIllegalDomain> selectAll();

    int updateByPrimaryKey(CarIllegalDomain record);
	
	
}
