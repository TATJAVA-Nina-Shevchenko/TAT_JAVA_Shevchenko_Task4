package com.epam.shevchenko.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.epam.shevchenko.dao.exception.ConnectionFailDAOException;
import com.epam.shevchenko.dao.exception.DAOException;
import com.epam.shevchenko.dao.exception.InitPropertiesDAOException;

public class ConnectionManager {
	private static ConnectionManager connectionManager;
	private Connection connection;

	private final static ResourceBundle rb = ResourceBundle.getBundle("resources/db");

	private String driver;
	private String url;
	private String dbLogin;
	private String dbPass;

	public static ConnectionManager getInstance() throws DAOException {
		if (connectionManager == null) {
			connectionManager = new ConnectionManager();
		}
		return connectionManager;
	}

	public Connection getConnection() throws DAOException {
		if (connection == null) {
			connection = createConnection();
		}
		return this.connection;
	}

	private ConnectionManager() throws DAOException{
		initDBConnection();
		if (!isPropertiesInit()) {
			throw new InitPropertiesDAOException();
		}
	}

	private boolean isPropertiesInit() {
		if ((driver == null) || (url == null) || (dbLogin == null) || (dbPass == null)) {
			return false;
		}
		return true;
	}

	private void initDBConnection() {
		driver = rb.getString("driver");
		url = rb.getString("url");
		dbLogin = rb.getString("dblogin");
		dbPass = rb.getString("dbpass");
	}

	private Connection createConnection() throws DAOException {
		Connection connection = null;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, dbLogin, dbPass);
		} catch (ClassNotFoundException e) {
			throw new ConnectionFailDAOException("Error getting driver", e);
		} catch (SQLException e) {
			throw new ConnectionFailDAOException("Fail to connect to database", e);
		}
		return connection;
	}
	
	public void closeConnection() throws DAOException{
		if(connection != null){
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DAOException("Fail to close connection", e);
			}
		}
	}
	
	
	

}
