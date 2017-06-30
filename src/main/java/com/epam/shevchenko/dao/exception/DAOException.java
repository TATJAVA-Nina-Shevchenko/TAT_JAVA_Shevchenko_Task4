package com.epam.shevchenko.dao.exception;

public class DAOException extends Exception {
	private static final long serialVersionUID = 1L;

	public DAOException() {
		super();
	}

	public DAOException(String arg0, Exception arg1) {
		super(arg0, arg1);
	}

	public DAOException(String arg0) {
		super(arg0);
		
	}

	public DAOException(Exception arg0) {
		super(arg0);
	}

	
	
}
