package com.epam.shevchenko.controller.command.user;

import java.util.Map;

import com.epam.shevchenko.bean.User;
import com.epam.shevchenko.constant.ReqRespMapping;
import com.epam.shevchenko.controller.command.BaseCommand;
import com.epam.shevchenko.service.ClientService;
import com.epam.shevchenko.service.ClientServiceImpl;
import com.epam.shevchenko.service.exception.ServiceException;

public class ShowUserProfile extends BaseCommand {

	@Override
	public String execute(Map<String, String> requestParams) {
		long userId = Long.parseLong(requestParams.get(ReqRespMapping.USER_ID));
		ClientService clientService = new ClientServiceImpl();

		User user = null;

		try {
			user = clientService.getUser(userId);
		} catch (ServiceException e) {
			log.error("Problem during login." + e);
		}

		String response = "";
		if (user != null) {
			response = createPositiveResponse(user);
		} else {
			String message = "Fail showing profile";
			response = createNegativeResponse(message);
		}
		return response;
	}

	
}
