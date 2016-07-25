package com.wanda.creditapp.user.dao;

import com.wanda.creditapp.user.domain.UserBankcard;
import java.util.List;

public interface UserBankcardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserBankcard record);

    UserBankcard selectByPrimaryKey(Integer id);

    List<UserBankcard> selectAll();

    int updateByPrimaryKey(UserBankcard record);
}