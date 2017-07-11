package com.epam.shevchenko.controller.command.common;

import java.util.Map;

import com.epam.shevchenko.controller.command.BaseCommand;

public class WrongRequest extends BaseCommand {

	@Override
	public String execute(Map<String, String> requestParams) {

		String message = "Wrong request";
		String response = createNegativeResponse(message);
		return response;
	}

	
	

}
