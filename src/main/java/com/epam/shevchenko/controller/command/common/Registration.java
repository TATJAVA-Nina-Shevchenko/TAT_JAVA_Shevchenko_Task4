package com.epam.shevchenko.controller.command.common;

import java.util.Map;

import com.epam.shevchenko.controller.command.BaseCommand;
import com.epam.shevchenko.service.ClientService;
import com.epam.shevchenko.service.ClientServiceImpl;
import com.epam.shevchenko.service.exception.ServiceException;

public class Registration extends BaseCommand {

	@Override
	public String execute(Map<String, String> requestParams) {
		ClientService clientService = new ClientServiceImpl();
		boolean registrationSucceed = false;

		try {
			registrationSucceed = clientService.register(requestParams);
		} catch (ServiceException e) {
			log.info("Problem during registration.");
		}

		String response = "";
		if (registrationSucceed) {
			response = createPositiveResponse();
		} else {
			response = createNegativeResponse();
		}

		return response;

	}

	private String createPositiveResponse() {
		String response;
		response = "user created";
		// TODO formatted response
		return response;
	}

	private String createNegativeResponse() {
		String response;
		response = "fail to create user";
		// TODO formatted response
		return response;
	}
}
