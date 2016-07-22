package com.wanda.creditapp.user.dao;

import com.wanda.creditapp.user.domain.QueryRecord;
import java.util.List;

public interface QueryRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QueryRecord record);

    QueryRecord selectByPrimaryKey(Integer id);

    List<QueryRecord> selectAll();

    int updateByPrimaryKey(QueryRecord record);
}