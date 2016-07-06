package com.benqcz.fourm.service;

import com.benqcz.fourm.domain.UserBean;
import com.benqcz.fourm.exception.UserExistException;

public interface UserService {
	/**
	 * 注册用户
	 * @param user 注册用户的UserBean对象
	 * @return 注册成功返回用户UserBean对象，失败抛出UserExistException
	 * @throws UserExistException 
	 */
	public abstract UserBean register(UserBean user) throws UserExistException;
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return 登陆成功返回用户UserBean对象，失败返回null
	 */
	public abstract UserBean login(String username, String password);
	
}
