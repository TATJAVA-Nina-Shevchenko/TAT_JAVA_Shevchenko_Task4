package com.epam.shevchenko.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.epam.shevchenko.dao.exception.ConnectionFailDAOException;
import com.epam.shevchenko.dao.exception.DAOException;

public class ConnectionManager {
	private Connection connection;
	
	private final static ResourceBundle resBundle = ResourceBundle.getBundle("db");
	
	private String driver;
	private String url;
	private String dbLogin;
	private String dbPass;
	
	
	public Connection createConnection() throws DAOException {
		
		try{
			Class.forName(driver);
			connection = DriverManager.getConnection(url, dbLogin, dbPass);
			
		}catch(ClassNotFoundException e){
			//TODO
			throw new ConnectionFailDAOException("", e);
		}catch(SQLException e){
			//TODO
			throw new ConnectionFailDAOException("", e);
		}
		
		
		return connection;
	}

}
