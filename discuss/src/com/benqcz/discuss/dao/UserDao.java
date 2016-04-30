package com.benqcz.discuss.dao;

import com.benqcz.discuss.domain.User;

public interface UserDao {
	/**
	 * 保存用户信息到xml数据库
	 * @param user User对象
	 * @return true成功 false失败
	 */
	public abstract boolean addUser(User user);
	
	/**
	 * 根据用户名查找用户
	 * @param name 用户名
	 * @return 查找到的User对象没有找到返回null
	 */
	public abstract User findUserByName(String userName);
	
	/**
	 * 根据用户名密码查找用户
	 * @param name 用户名
	 * @param password 密码
	 * @return 查找到的User对象没有找到返回null
	 */
	public abstract User findUser(String userName, String password);
}
