package com.benqcz.fourm.dao;

import com.benqcz.fourm.domain.UserBean;

public interface UserDao {
	/**
	 * 添加用户
	 * @param user 添加用户的UserBean对象
	 * @return 添加成功返回添加用户的UserBean对象，失败返回null
	 */
	public abstract UserBean addUser(UserBean user);
	public abstract UserBean findUserByName(String username);
	public abstract UserBean findUser(String username, String password);
}
