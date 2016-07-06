package com.benqcz.account.service;

public interface AccountService {
	boolean transfer(int fromAccountId, int toAccountId, float balance);
}
