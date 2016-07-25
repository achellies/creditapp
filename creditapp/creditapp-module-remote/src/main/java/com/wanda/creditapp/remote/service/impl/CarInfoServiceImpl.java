package com.wanda.creditapp.remote.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.remote.dao.CarInfoMapper;
import com.wanda.creditapp.remote.domain.CarInfoDomain;
import com.wanda.creditapp.remote.service.ICarInfoService;


@Service
public class CarInfoServiceImpl implements ICarInfoService{

	private static final Logger logger = Logger.getLogger(CarInfoServiceImpl.class);
	
	@Autowired
	private CarInfoMapper carInfoMapper;
	
	public void saveCarInfo(CarInfoDomain carInfoDomain) {
		try {
			//TODO 待删除
			carInfoDomain.setCreateUser("SYS");
			carInfoDomain.setUpdateUser("SYS");
			this.carInfoMapper.saveCarInfo(carInfoDomain);
		} catch (Exception e) {
			logger.error("CarInfoServiceImpl.saveCarInfo---新增车辆异常!", e);
			throw new CreditAppException("CarInfoServiceImpl.saveCarInfo---新增车辆异常!", e);
		}
	}

   
	
}
