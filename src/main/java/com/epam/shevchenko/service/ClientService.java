package com.epam.shevchenko.service;

import java.util.Map;

import com.epam.shevchenko.bean.User;
import com.epam.shevchenko.service.exception.ServiceException;

public interface ClientService {

	User login(Map<String, String> requestParams) throws ServiceException;

	boolean register(Map<String, String> requestParams) throws ServiceException;

}
