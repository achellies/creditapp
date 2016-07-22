package com.wanda.creditapp.user.dao;

import com.wanda.creditapp.user.domain.AccumulationAccount;
import java.util.List;

public interface AccumulationAccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccumulationAccount record);

    AccumulationAccount selectByPrimaryKey(Integer id);
    
    List<AccumulationAccount> selectByUserId(Integer userId);

    List<AccumulationAccount> selectAll();

    int updateByPrimaryKey(AccumulationAccount record);
}