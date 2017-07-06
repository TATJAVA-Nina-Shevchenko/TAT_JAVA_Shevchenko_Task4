package com.epam.shevchenko.controller.command.common;

import java.util.Map;

import com.epam.shevchenko.bean.User;
import com.epam.shevchenko.controller.FrontController;
import com.epam.shevchenko.controller.command.BaseCommand;
import com.epam.shevchenko.controller.util.SessionIdentifierGenerator;
import com.epam.shevchenko.service.ClientService;
import com.epam.shevchenko.service.ClientServiceImpl;
import com.epam.shevchenko.service.exception.ServiceException;

public class Login extends BaseCommand {

	public String execute(Map<String, String> requestParams) {

		ClientService clientService = new ClientServiceImpl();
		User user = null;
		try {
			user = clientService.login(requestParams);
		} catch (ServiceException e) {
			log.info("Problem during login.");
		}

		String response = "";
		if (user != null) {
			String sessionId = addUserIntoSession(user);
			response = createPositiveResponse(user, sessionId);
		} else {
			response = createNegativeResponse(user);
		}
		return response;
	}

	// adds current user to application session with unique id to identify him
	// in future
	private String addUserIntoSession(User user) {

		Map<String, User> openedSessions = FrontController.getOpenedSessions();
		String newSessionId = new SessionIdentifierGenerator().nextSessionId();
		openedSessions.put(newSessionId, user);
		return newSessionId;
	}

	private String createNegativeResponse(User user) {
		String response;
		response = "error log";
		// TODO Auto-generated method stub
		return response;
	}

	private String createPositiveResponse(User user, String sessionId) {
		String response;
		// TODO create response
		response = "successfully logged"; // заглушка
		return response;
	}

}
