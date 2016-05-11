package com.benqcz.account.dao.impl;

public class AccountDaoException extends RuntimeException {

	public AccountDaoException() {
	}

	public AccountDaoException(String message) {
		super(message);
	}

	public AccountDaoException(Throwable cause) {
		super(cause);
	}

	public AccountDaoException(String message, Throwable cause) {
		super(message, cause);
	}

}
