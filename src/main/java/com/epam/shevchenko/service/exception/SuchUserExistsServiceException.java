package com.epam.shevchenko.service.exception;

public class SuchUserExistsServiceException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public SuchUserExistsServiceException() {
		super();
	}

	public SuchUserExistsServiceException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public SuchUserExistsServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		}

	public SuchUserExistsServiceException(String arg0) {
		super(arg0);
		}

	public SuchUserExistsServiceException(Throwable arg0) {
		super(arg0);
		}

	
	
}
