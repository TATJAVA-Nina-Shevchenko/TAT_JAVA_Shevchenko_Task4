package com.epam.shevchenko.service;

import com.epam.shevchenko.bean.User;
import com.epam.shevchenko.dao.UserDAO;
import com.epam.shevchenko.dao.exception.DAOException;
import com.epam.shevchenko.dao.impl.SQLUserDAO;
import com.epam.shevchenko.service.exception.ServiceException;

public class ClientServiceImpl implements ClientService {

	public User login(String login, String password) throws ServiceException {
		User user = null;
		UserDAO userDAO = new SQLUserDAO();
		if ((login == null) || (password == null)) {
			// TODO Auto-generated catch block
			throw new ServiceException();
		}
		try {
			user = userDAO.getUser(login, password);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			throw new ServiceException();
		}

		// TODO Auto-generated method stub
		return user;
	}

}
