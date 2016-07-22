package com.wanda.creditapp.user.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.user.dao.CarInfoMapper;
import com.wanda.creditapp.user.domain.CarInfoDomain;
import com.wanda.creditapp.user.service.ICarInfoService;

@Service
public class CarInfoServiceImpl implements ICarInfoService {

	private static final Logger logger = Logger.getLogger(CarInfoServiceImpl.class);
	
	@Autowired
	private CarInfoMapper carInfoMapper;

	
	
	public int saveCarInfo(CarInfoDomain carInfoDomain) {
		int result = -1;
		try {
			result = this.carInfoMapper.saveCarInfo(carInfoDomain);
		} catch (Exception e) {
			logger.error("CarInfoServiceImpl.saveCarInfo---新增车辆信息异常", e);
			throw new CreditAppException("CarInfoServiceImpl.saveCarInfo---新增车辆信息异常", e);
		}
		return result;
	}
	
	
	public List<CarInfoDomain> queryCarInfo(CarInfoDomain carInfoDomain) {
		List<CarInfoDomain> list = null;
		try {
			list = this.carInfoMapper.queryCarInfo(carInfoDomain);
		} catch (Exception e) {
			logger.error("CarInfoServiceImpl.queryCarInfo---车辆信息查询异常", e);
			throw new CreditAppException("CarInfoServiceImpl.queryCarInfo---车辆信息查询异常", e);
		}
		return list;
	}


	
	
}
