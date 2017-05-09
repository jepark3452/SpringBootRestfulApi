package com.jepark.springboot.util;

public class CustomErrorType {
	
	private String message;
	
	public CustomErrorType(String message) {
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
}
