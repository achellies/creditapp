package com.wanda.creditapp.user.service;

import java.util.List;

import com.wanda.creditapp.user.domain.DissentDomain;

public interface IDissentService {

	//新增异议
	void saveDissent(DissentDomain dissentDomain);
	
	//查看所有异议
	List<DissentDomain> queryAllCarDissent();

	//查看异议详情(查看原报告)
	DissentDomain queryCarDissentByRecordId(Integer recordId);


}
