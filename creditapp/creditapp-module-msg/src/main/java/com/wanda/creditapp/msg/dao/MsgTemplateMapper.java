package com.wanda.creditapp.msg.dao;

import com.wanda.creditapp.msg.domain.MsgTemplate;
import java.util.List;

public interface MsgTemplateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MsgTemplate record);

    MsgTemplate selectByPrimaryKey(Integer id);

    List<MsgTemplate> selectAll();

    int updateByPrimaryKey(MsgTemplate record);
}