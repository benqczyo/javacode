package com.benqcz.fourm.service;

import com.benqcz.fourm.domain.UserBean;

public interface UserService {
	public abstract UserBean register(UserBean user);
	public abstract UserBean login(String username, String password);
	
}
