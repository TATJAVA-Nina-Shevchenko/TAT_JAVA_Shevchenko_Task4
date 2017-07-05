package com.epam.shevchenko.controller.command;

import java.util.Map;

public interface Command {
	
	String execute(Map<String, String> requestParams);
	
//	CommandName getCommandName();

}
