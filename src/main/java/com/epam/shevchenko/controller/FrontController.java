package com.epam.shevchenko.controller;

import java.util.HashMap;
import java.util.Map;

import com.epam.shevchenko.controller.command.Command;
import com.epam.shevchenko.controller.util.RequestReader;
import com.epam.shevchenko.enums.UserStatus;

public class FrontController {
	private final CommandProvider provider = new CommandProvider();
	private Map<String, UserStatus> openedSessions = new HashMap<String, UserStatus>();


	public Map<String, UserStatus> getOpenedSessions() {
		return openedSessions;
	}

	public void setOpenedSessions(Map<String, UserStatus> openedSessions) {
		this.openedSessions = openedSessions;
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
				return openedSessions.get(sessionId);
			}
			return UserStatus.COMMON;
		}
		return UserStatus.COMMON;
	}
	
	
}
