package com.wanda.creditapp.msg.dao;

import com.wanda.creditapp.msg.domain.MsgDetail;
import java.util.List;

public interface MsgDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MsgDetail record);

    MsgDetail selectByPrimaryKey(Integer id);

    List<MsgDetail> selectAll();

    int updateByPrimaryKey(MsgDetail record);
}