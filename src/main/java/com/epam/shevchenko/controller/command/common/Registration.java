package com.epam.shevchenko.controller.command.common;

import java.util.Map;

import com.epam.shevchenko.constant.ReqRespMapping;
import com.epam.shevchenko.controller.command.BaseCommand;
import com.epam.shevchenko.service.ClientService;
import com.epam.shevchenko.service.ClientServiceImpl;
import com.epam.shevchenko.service.exception.ServiceException;

public class Registration extends BaseCommand {

	@Override
	public String execute(Map<String, String> requestParams) {
		String login = requestParams.get(ReqRespMapping.USER_LOGIN);
		String password = requestParams.get(ReqRespMapping.USER_PASSWORD);
		String telephone = requestParams.get(ReqRespMapping.USER_TELEPHONE);
		
		ClientService clientService = new ClientServiceImpl();
		boolean registrationSucceed = false;

		try {
			registrationSucceed = clientService.register(login, password, telephone);
		} catch (ServiceException e) {
			log.error("Problem during registration." + e);
		}

		
		String response = "";
		if (registrationSucceed) {
			String message = "User was created";
			response = createPositiveResponse(message);
		} else {
			String message ="Fail to create user";
			response = createNegativeResponse(message);
		}

		return response;

	}
	
}
