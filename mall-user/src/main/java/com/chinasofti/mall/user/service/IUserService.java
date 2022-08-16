package com.chinasofti.mall.user.service;

import com.chinasofti.mall.common.dto.User;
import com.chinasofti.mall.user.vo.ResponseVo;

/**
 * Created by xuepeng@chinasofti
 */
public interface IUserService {

	/**
	 * 注册
	 */
	ResponseVo<User> register(User user);

	/**
	 * 登录
	 */
	ResponseVo<User> login(String username, String password);

	/**
	 * 获取用户信息
	 */
	ResponseVo<User> getUserByUserID(Integer userId);
}
