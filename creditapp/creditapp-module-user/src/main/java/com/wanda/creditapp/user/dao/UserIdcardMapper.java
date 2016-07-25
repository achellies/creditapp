package com.wanda.creditapp.user.dao;

import com.wanda.creditapp.user.domain.UserIdcard;
import java.util.List;

public interface UserIdcardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserIdcard record);

    UserIdcard selectByPrimaryKey(Integer id);

    List<UserIdcard> selectAll();

    int updateByPrimaryKey(UserIdcard record);
    
    
    UserIdcard selectByUserId(Integer id);
}