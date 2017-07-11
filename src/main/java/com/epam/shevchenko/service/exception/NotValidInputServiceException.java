package com.epam.shevchenko.service.exception;

public class NotValidInputServiceException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public NotValidInputServiceException() {
		super();
	}

	public NotValidInputServiceException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public NotValidInputServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public NotValidInputServiceException(String arg0) {
		super(arg0);
	}

	public NotValidInputServiceException(Throwable arg0) {
		super(arg0);
	}

}
