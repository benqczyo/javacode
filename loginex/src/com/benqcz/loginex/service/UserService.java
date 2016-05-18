package com.benqcz.loginex.service;

import com.benqcz.loginex.domain.UserBean;

public interface UserService {
	boolean login(UserBean user);
	boolean register(UserBean user);
}
