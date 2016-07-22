package com.wanda.creditapp.user.dao;

import java.util.List;

import com.wanda.creditapp.user.domain.DissentDomain;

public interface DissentMapper {

	void saveDissent(DissentDomain dissentDomain);

	List<DissentDomain> queryAllCarDissent();

	DissentDomain queryCarDissentByRecordId(Integer recordId);
	
}
