package com.epam.shevchenko.controller.command.common;

import java.util.Map;

import com.epam.shevchenko.controller.command.Command;

public class WrongRequest implements Command {

	@Override
	public String execute(Map<String, String> requestParams) {
		// TODO Auto-generated method stub
		return "wrong request";
	}

	
	

}
