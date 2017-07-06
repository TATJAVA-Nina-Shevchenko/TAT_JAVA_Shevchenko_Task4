package com.epam.shevchenko.dao;

import com.epam.shevchenko.bean.User;
import com.epam.shevchenko.dao.exception.DAOException;

public interface UserDAO extends BaseDAO<User> {

	User getUser(String login,String password) throws DAOException;
	
	
}
