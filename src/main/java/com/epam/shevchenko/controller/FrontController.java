package com.epam.shevchenko.controller;

import java.util.HashMap;
import java.util.Map;

import com.epam.shevchenko.bean.User;
import com.epam.shevchenko.constant.ReqRespMapping;
import com.epam.shevchenko.constant.UserStatus;
import com.epam.shevchenko.controller.command.Command;
import com.epam.shevchenko.controller.util.RequestReader;

public class FrontController {
	private static FrontController instance;
//	private final static CommandProvider provider = new CommandProvider();
	
	private final static  String XML_URL = "src/main/java/resources/library.xml";
	private final static CommandProvider provider = new CommandProvider(XML_URL);
	private static Map<String, User> openedSessions = new HashMap<String, User>();
	
	public static FrontController getInstance(){
		if(instance == null){
			instance = new FrontController();
		}
		return instance;
	}

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
		executionCommand = provider.getCommand(requestParams.get(ReqRespMapping.COMMAND), userStatus);

		// execute command and get a response as a string
		String response = executionCommand.execute(requestParams);
		return response;
	}

	// Check authorized user rights according to saved sessions or gives him
	// common rights
	private UserStatus getUserStatus(Map<String, String> requestParams) {
		if ((requestParams != null) && (requestParams.containsKey(ReqRespMapping.SESSION_ID))) {
			String sessionId = requestParams.get(ReqRespMapping.SESSION_ID);
			if ((openedSessions != null) && (openedSessions.containsKey(sessionId))) {
				return openedSessions.get(sessionId).getUserStatus();
			}
			return UserStatus.COMMON;
		}
		return UserStatus.COMMON;
	}

}
