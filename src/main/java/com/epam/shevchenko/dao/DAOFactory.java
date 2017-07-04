package com.epam.shevchenko.dao;

import com.epam.shevchenko.dao.impl.SQLBookDAO;
//import com.epam.shevchenko.dao.impl.SQLUserDAO;

public class DAOFactory {

	private static DAOFactory instance;
	private final BookDAO sqlBookImpl = new SQLBookDAO();
//	private final UserDAO sqlUserImpl = new SQLUserDAO();
	
	private DAOFactory() {}
	
	public static DAOFactory getInstance(){
		if(instance == null){
			instance = new DAOFactory();
		}
		return instance;
	}
	
	public BookDAO getBookDAO(){
		return sqlBookImpl;
	}
	
//	public UserDAO getUserDAO(){
//		return sqlUserImpl;
//	}
}
