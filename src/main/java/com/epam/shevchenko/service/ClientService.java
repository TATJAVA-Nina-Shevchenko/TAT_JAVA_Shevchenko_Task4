package com.epam.shevchenko.service;

import com.epam.shevchenko.bean.User;
import com.epam.shevchenko.service.exception.ServiceException;

public interface ClientService {

	User login(String login, String password) throws ServiceException;

}
