package com.benqcz.account.service.impl;

public class AccountServiceException extends RuntimeException {

	public AccountServiceException() {
	}

	public AccountServiceException(String message) {
		super(message);
	}

	public AccountServiceException(Throwable cause) {
		super(cause);
	}

	public AccountServiceException(String message, Throwable cause) {
		super(message, cause);
	}

}
