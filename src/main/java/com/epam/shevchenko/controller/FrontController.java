package com.epam.shevchenko.controller;

import java.util.HashMap;
import java.util.Map;

import com.epam.shevchenko.bean.User;
import com.epam.shevchenko.constant.UserStatus;
import com.epam.shevchenko.controller.command.Command;
import com.epam.shevchenko.controller.util.RequestReader;

public class FrontController {
	private final CommandProvider provider = new CommandProvider();
	private static Map<String, User> openedSessions = new HashMap<String, User>();

	public static Map<String, User> getOpenedSessions() {
		return openedSessions;
	}

	public static void setOpenedSessions(Map<String, User> openedSessions) {
		FrontController.openedSessions = openedSessions;
	}

	// main method that takes request and according to it's params executes
	// appropriate command. As a result it returns String response

	public String executeTask(String request) {
		Command executionCommand;

		// reads params from String to list
		Map<String, String> requestParams = RequestReader.readParams(request);

		// gets user status from list of params
		UserStatus userStatus = getUserStatus(requestParams);

		// ask a command provider for appropriate command according to user
		// rights
		executionCommand = provider.getCommand(requestParams.get("command"), userStatus);

		// execute command and get a response as a string
		String response = executionCommand.execute(requestParams);
		return response;
	}

	// Check authorized user rights according to saved sessions or gives him
	// common rights
	private UserStatus getUserStatus(Map<String, String> requestParams) {
		if ((requestParams != null) && (requestParams.containsKey("sessionId"))) {
			String sessionId = requestParams.get("sessionId");
			if ((openedSessions != null) && (openedSessions.containsKey(sessionId))) {
				return openedSessions.get(sessionId).getUserStatus();
			}
			return UserStatus.COMMON;
		}
		return UserStatus.COMMON;
	}

}
