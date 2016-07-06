package com.benqcz.database.assistant.exception;

public class AssistantException extends RuntimeException {

	public AssistantException() {
	}

	public AssistantException(String message) {
		super(message);
	}

	public AssistantException(Throwable cause) {
		super(cause);
	}

	public AssistantException(String message, Throwable cause) {
		super(message, cause);
	}

}
