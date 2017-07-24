package com.epam.shevchenko.controller.command.impl;

import java.util.Map;

import com.epam.shevchenko.controller.command.BaseCommand;

public class NotEnoughRights extends BaseCommand{

	@Override
	public String execute(Map<String, String> requestParams) {
		
		String message = "Not enough rights for command";
		String response = createNegativeResponse(message);
		return response;
	}

	

}
