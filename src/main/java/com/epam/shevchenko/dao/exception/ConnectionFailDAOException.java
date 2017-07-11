package com.epam.shevchenko.dao.exception;

public class ConnectionFailDAOException extends DAOException {

	private static final long serialVersionUID = 1L;

	public ConnectionFailDAOException() {
		super();
	}

	public ConnectionFailDAOException(Exception arg0) {
		super(arg0);
		}

	public ConnectionFailDAOException(String arg0, Exception arg1) {
		super(arg0, arg1);
	}

	public ConnectionFailDAOException(String arg0) {
		super(arg0);
		}

	
	
}
