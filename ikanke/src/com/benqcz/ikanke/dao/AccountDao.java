package com.benqcz.ikanke.dao;

import java.util.List;

import com.benqcz.ikanke.domain.impl.AccountBean;

public interface AccountDao {
	boolean addAccount(AccountBean account);
	//boolean delAccountById(String id);
	boolean delAccountByName(String name);
	boolean updateAccount(AccountBean account);
	List<AccountBean> findAllAccounts();
	//AccountBean findAccountById(String id);
	AccountBean findAccountByName(String name);
	AccountBean findAccount(String name, String password);
}
