package com.wanda.creditapp.user.dao;

import com.wanda.creditapp.user.domain.QueryRecord;
import java.util.List;

public interface QueryRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QueryRecord record);

    /**
     * 获取指定用户指定id的产品查询记录
     * @param id
     * @param userId
     * @return
     */
    QueryRecord selectByPrimaryKey(Integer id,Integer userId);

    List<QueryRecord> selectAll();

    int updateByPrimaryKey(QueryRecord record);
    
    /**
     * 获取指定用户下的所有产品查询记录
     * @param userId
     * @return
     */
    List<QueryRecord> selectByUserId(Integer userId);

}