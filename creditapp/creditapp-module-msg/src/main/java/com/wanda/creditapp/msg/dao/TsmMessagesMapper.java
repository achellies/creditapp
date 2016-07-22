package com.wanda.creditapp.msg.dao;

import com.wanda.creditapp.msg.domain.TsmMessages;
import java.util.List;

public interface TsmMessagesMapper {
    int deleteByPrimaryKey(String messageid);

    int insert(TsmMessages record);

    TsmMessages selectByPrimaryKey(String messageid);

    List<TsmMessages> selectAll();

    int updateByPrimaryKey(TsmMessages record);
}