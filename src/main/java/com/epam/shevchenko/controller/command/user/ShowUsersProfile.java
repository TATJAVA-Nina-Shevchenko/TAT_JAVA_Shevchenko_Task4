package com.epam.shevchenko.controller.command.user;

import java.util.Map;

import com.epam.shevchenko.controller.command.BaseCommand;
import com.epam.shevchenko.enums.ReqRespMapping;

public class ShowUsersProfile extends BaseCommand {

	@Override
	public String execute(Map<String, String> requestParams) {
		int id = Integer.parseInt(requestParams.get(ReqRespMapping.USER_ID));
		return null;
	}

}
