package com.wanda.creditapp.user.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanda.creditapp.common.exception.CreditAppException;
import com.wanda.creditapp.user.dao.DissentMapper;
import com.wanda.creditapp.user.domain.DissentDomain;
import com.wanda.creditapp.user.service.IDissentService;

@Service("dissentService")
public class DissentServiceImpl implements IDissentService {

	private static final Logger logger = Logger.getLogger(DissentServiceImpl.class);
	
	@Autowired
	private DissentMapper dissentMapper;
	
	
	//新增异议
	public void saveDissent(DissentDomain dissentDomain) {
		try {
			this.dissentMapper.saveDissent(dissentDomain);
		} catch (Exception e) {
			logger.error("DissentServiceImpl.saveDissent---新增异议异常", e);
			throw new CreditAppException("DissentServiceImpl.saveDissent---新增异议异常", e);
		}
	}
	
	//查看所有异议
	public List<DissentDomain> queryAllCarDissent() {
		List<DissentDomain> list = null;
		try {
			list = this.dissentMapper.queryAllCarDissent();
		} catch (Exception e) {
			logger.error("DissentServiceImpl.queryAllCarDissent---查看所有异议异常", e);
			throw new CreditAppException("DissentServiceImpl.queryAllCarDissent---查看所有异议异常", e);
		}
		return list;
	}

	
	//根据recordId,查看异议详情(查看原报告)
	public DissentDomain queryCarDissentByRecordId(Integer recordId) {
		DissentDomain dissentDomain = null;
		try {
			dissentDomain = this.dissentMapper.queryCarDissentByRecordId(recordId);
		} catch (Exception e) {
			logger.error("DissentServiceImpl.queryCarDissentByRecordId---查看异议详情异常", e);
			throw new CreditAppException("DissentServiceImpl.queryCarDissentByRecordId---查看异议详情异常", e);
		}
		return dissentDomain;
	}

	
	
}
