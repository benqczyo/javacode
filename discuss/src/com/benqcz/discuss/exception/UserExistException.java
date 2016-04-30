package com.benqcz.discuss.exception;

public class UserExistException extends Exception {

	public UserExistException() {
		super("用户已经存在");
	}

	public UserExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserExistException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public UserExistException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
