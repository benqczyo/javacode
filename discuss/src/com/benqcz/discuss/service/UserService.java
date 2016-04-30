package com.benqcz.discuss.service;

import com.benqcz.discuss.domain.User;
import com.benqcz.discuss.exception.UserExistException;

public interface UserService {
	/**
	 * 用户注册
	 * @param user 要注册的User对象
	 * @return 注册成功返回注册User对象，失败抛出异常
	 */
	public abstract User register(User user) throws UserExistException;
	
	/**
	 * 用户登录
	 * @param userName 登陆用户名
	 * @param password 登陆用户密码
	 * @return 登陆成功返回登陆User对象，失败返回null
	 */
	public abstract User login(String userName, String password);
}
