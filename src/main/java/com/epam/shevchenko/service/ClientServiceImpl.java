package com.epam.shevchenko.service;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.epam.shevchenko.bean.User;
import com.epam.shevchenko.controller.util.DataEncryptor;
import com.epam.shevchenko.dao.UserDAO;
import com.epam.shevchenko.dao.exception.DAOException;
import com.epam.shevchenko.dao.impl.SQLUserDAO;
import com.epam.shevchenko.service.exception.NotValidInputServiceException;
import com.epam.shevchenko.service.exception.ServiceException;
import com.epam.shevchenko.service.exception.SuchUserExistsServiceException;

public class ClientServiceImpl implements ClientService {

	private static final String LOGIN_PATTERN = "^\\w{3,20}$"; // latin letters,
																// digitals,
																// under-scope
																// from 3 till
																// 20 symbols
	private static final String PASSWORD_PATTERN = "^\\w{5,20}$"; // latin
																	// letters,
																	// digitals,
																	// under-scope
																	// from 5
																	// till 20
																	// symbols
	private static final String TELEPHONE_PATTERN = "^\\+\\d{3}\\s\\d{2}\\s\\d{7}$"; // digitals
																						// in
																						// format
																						// <+ddd
																						// dd
																						// ddddddd>

	public User login(Map<String, String> requestParams) throws ServiceException {

		String login = requestParams.get("login");
		String password = requestParams.get("password");
		if (password != null && !password.isEmpty()) {
			password = DataEncryptor.getPasswordHashCode(password);
		}

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

	@Override
	public boolean register(Map<String, String> requestParams) throws ServiceException {
		String login = requestParams.get("login");
		String password = requestParams.get("password");
		String telephone = requestParams.get("telephone");

		if (!isValidInput(login, password, telephone)) {
			throw new NotValidInputServiceException();
		}
		
		password = DataEncryptor.getPasswordHashCode(password);

		UserDAO userDAO = new SQLUserDAO();
		User user = new User(login, password, telephone);

		try {
			User existingUser = userDAO.getUser(login, password);
			if (existingUser != null) {
				throw new SuchUserExistsServiceException(
						"User with such parameters has already existed. Login:" + login);
			}
			userDAO.add(user);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			throw new ServiceException();
		}

		return true;
	}

	private boolean isValidInput(String login, String password, String telephone) {
		if ((login == null) || !isValidLogin(login)) {
			return false;
		}
		if ((password == null) || !isValidPassword(password)) {
			return false;
		}
		
		if ((telephone != null) && !isValidTelephone(telephone)) {
			return false;
		}
			
		return true;
	}

	private boolean isValidTelephone(String telephone) {
		Pattern p = Pattern.compile(TELEPHONE_PATTERN);
		Matcher m = p.matcher(telephone);
		return m.matches();
	}

	private boolean isValidPassword(String password) {
		Pattern p = Pattern.compile(PASSWORD_PATTERN);
		Matcher m = p.matcher(password);
		return m.matches();
	}

	private boolean isValidLogin(String login) {
		Pattern p = Pattern.compile(LOGIN_PATTERN);
		Matcher m = p.matcher(login);
		return m.matches();
	}

}
