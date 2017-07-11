package com.epam.shevchenko.service.exception;

public class SuchUserDoesntExistsServiceException extends Exception {
	private static final long serialVersionUID = 1L;

	public SuchUserDoesntExistsServiceException() {
		super();
	}

	public SuchUserDoesntExistsServiceException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		}

	public SuchUserDoesntExistsServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public SuchUserDoesntExistsServiceException(String arg0) {
		super(arg0);
	}

	public SuchUserDoesntExistsServiceException(Throwable arg0) {
		super(arg0);
	}

	
}
