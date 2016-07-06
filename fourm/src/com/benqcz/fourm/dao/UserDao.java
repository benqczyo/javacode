package com.benqcz.fourm.dao;

import com.benqcz.fourm.domain.UserBean;

public interface UserDao {
	/**
	 * 添加用户
	 * @param user 添加用户的UserBean对象
	 * @return 添加成功返回添加用户的UserBean对象，失败返回null
	 * @exception 抛出RuntimeException
	 */
	public abstract UserBean addUser(UserBean user);
	/**
	 * 根据用户名查找用户
	 * @param username 用户名
	 * @return 用户存在返回用户UserBean对象，失败返回null
	 * @exception 抛出RuntimeException
	 */
	public abstract UserBean findUserByName(String username);
	/**
	 * 根据用户名和密码查找用户
	 * @param username 用户名
	 * @param password 密码
	 * @return 用户存在返回用户UserBean对象，失败返回null
	 * @exception 抛出RuntimeException
	 */
	public abstract UserBean findUser(String username, String password);
}
