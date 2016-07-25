package com.wanda.creditapp.user.dao;

import java.util.List;

import com.wanda.creditapp.user.domain.DissentDomain;

public interface DissentMapper {

	int deleteByPrimaryKey(Integer id);

    int insert(DissentDomain record);

    DissentDomain selectByPrimaryKey(Integer id);

    List<DissentDomain> selectAll();

    int updateByPrimaryKey(DissentDomain record);
	
	
	void saveDissent(DissentDomain dissentDomain);

	List<DissentDomain> queryAllCarDissent();

	DissentDomain queryCarDissentByRecordId(Integer recordId);
	
}
