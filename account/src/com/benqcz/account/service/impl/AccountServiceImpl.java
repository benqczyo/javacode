package com.benqcz.account.service.impl;

import com.benqcz.account.dao.AccountDao;
import com.benqcz.account.dao.impl.AccountDaoImpl;
import com.benqcz.account.domain.Account;
import com.benqcz.account.service.AccountService;
import com.benqcz.utils.jdbc.C3P0Utils;

public class AccountServiceImpl implements AccountService {
	
	private AccountDao dao = new AccountDaoImpl();

	@Override
	public boolean transfer(int fromAccountId, int toAccountId, float balance) {
		boolean result = false;
		try {
			C3P0Utils.startTransaction();
			Account fromAccount = dao.findAccountById(fromAccountId);
			Account toAccount = dao.findAccountById(toAccountId);
			fromAccount.setBalance(fromAccount.getBalance() - balance);
			toAccount.setBalance(toAccount.getBalance() + balance);
			dao.updateAccount(fromAccount);
			dao.updateAccount(toAccount);
			result = true;
		} catch (Exception e) {
			C3P0Utils.rollback();
			throw new AccountServiceException(e);
		} finally {
			C3P0Utils.commit();
		}
		return result;
	}

}
