package com.wanda.creditapp.msg.dao;

import com.wanda.creditapp.msg.domain.VerifyConfig;
import java.util.List;

public interface VerifyConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VerifyConfig record);

    VerifyConfig selectByPrimaryKey(Integer id);
    
    VerifyConfig getByVerifyType(Integer verifyType);

    List<VerifyConfig> selectAll();

    int updateByPrimaryKey(VerifyConfig record);
}