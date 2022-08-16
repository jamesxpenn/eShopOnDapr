package com.chinasofti.mall.user.dao;

import com.chinasofti.mall.common.dto.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int countByUsername(String username);

    int countByEmail(String email);

    User selectByUsername(String username);
}