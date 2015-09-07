package com.fhp.mvctest.service;

public class UserExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserExistsException() {
		super();
	}
	
	public UserExistsException(String message) {
		super(message);
	}
	
	public UserExistsException(String message, Throwable cause) {
        super(message, cause);
    }
	
	public UserExistsException(Throwable cause) {
        super(cause);
    }
	
	protected UserExistsException(String message, Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
}
}
