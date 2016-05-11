package com.benqcz.account.dao;

import com.benqcz.account.domain.Account;

public interface AccountDao {
	boolean addAccount(Account account);
	Account findAccountById(int id);
	boolean updateAccount(Account account);
}
