package com.epam.shevchenko.controller;

import java.util.HashMap;
import java.util.Map;

import com.epam.shevchenko.bean.User;
import com.epam.shevchenko.controller.command.Command;
import com.epam.shevchenko.controller.util.RequestReader;
import com.epam.shevchenko.enums.UserStatus;

public class FrontController {
	private final CommandProvider provider = new CommandProvider();
	private static Map<String, User> openedSessions = new HashMap<String, User>();


	public static Map<String, User> getOpenedSessions() {
		return openedSessions;
	}

	public static void setOpenedSessions(Map<String, User> openedSessions) {
		FrontController.openedSessions = openedSessions;
	}

		
	public String executeTask(String request) {
		Command executionCommand;
		Map<String, String> requestParams = RequestReader.readParams(request);
		UserStatus userStatus = getUserStatus(requestParams);
		executionCommand = provider.getCommand(requestParams.get("command"), userStatus);
		String response = executionCommand.execute(requestParams);
		return response;
	}


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
