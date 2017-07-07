package com.epam.shevchenko.controller.command.common;

import java.util.Map;

import com.epam.shevchenko.controller.command.Command;

public class NotEnoughRights implements Command {

	@Override
	public String execute(Map<String, String> requestParams) {
		String response = "Not enough rights for command";
		return response;
	}

	

}
