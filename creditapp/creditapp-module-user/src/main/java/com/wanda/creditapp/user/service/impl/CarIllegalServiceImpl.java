package com.wanda.creditapp.user.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.user.dao.CarIllegalMapper;
import com.wanda.creditapp.user.domain.CarIllegalDomain;
import com.wanda.creditapp.user.service.ICarIllegalService;

@Service("carIllegalService")
public class CarIllegalServiceImpl implements ICarIllegalService {

	private static final Logger logger = Logger.getLogger(CarIllegalServiceImpl.class);
	
	@Autowired
	private CarIllegalMapper carIllegalMapper;
	
	
	public List<CarIllegalDomain> queryIllegalCar(CarIllegalDomain carDomain) {
		List<CarIllegalDomain> list = null;
		try {
			list = this.carIllegalMapper.queryIllegalCar(carDomain);
		} catch (Exception e) {
			logger.error("CarIllegalServiceImpl.queryIllegalCar---车辆违章信息查询 异常", e);
			throw new CreditAppException("CarIllegalServiceImpl.queryIllegalCar---车辆违章信息查询 异常", e);
		}
		return list;
	}


	
	
}
