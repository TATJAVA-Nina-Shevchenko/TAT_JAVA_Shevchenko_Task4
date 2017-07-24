package com.epam.shevchenko.controller.command.impl;

import java.util.Map;

import com.epam.shevchenko.bean.User;
import com.epam.shevchenko.constant.ReqRespMapping;
import com.epam.shevchenko.controller.FrontController;
import com.epam.shevchenko.controller.command.BaseCommand;
import com.epam.shevchenko.controller.util.DataEncryptor;
import com.epam.shevchenko.controller.util.SessionIdentifierGenerator;
import com.epam.shevchenko.service.ClientService;
import com.epam.shevchenko.service.ClientServiceImpl;
import com.epam.shevchenko.service.exception.ServiceException;

public class Login extends BaseCommand {

	public String execute(Map<String, String> requestParams) {
		String login = requestParams.get(ReqRespMapping.USER_LOGIN);
		String password = requestParams.get(ReqRespMapping.USER_PASSWORD);

		if (password != null && !password.isEmpty()) {
			password = DataEncryptor.getPasswordHashCode(password);
		}

		ClientService clientService = new ClientServiceImpl();
		User user = null;
		try {
			user = clientService.login(login, password);
		} catch (ServiceException e) {
			log.error("Problem during login." + e);
		}

		String response = "";
		if (user != null) {
			String sessionId = addUserIntoSession(user);
			response = createPositiveResponse(user, sessionId);
		} else {
			String message = "Fail login";
			response = createNegativeResponse(message);
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

	

}
