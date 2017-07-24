package com.epam.shevchenko.controller.command.impl;

import java.util.Map;

import com.epam.shevchenko.bean.User;
import com.epam.shevchenko.constant.ReqRespMapping;
import com.epam.shevchenko.constant.UserStatus;
import com.epam.shevchenko.controller.command.BaseCommand;
import com.epam.shevchenko.service.ClientService;
import com.epam.shevchenko.service.ClientServiceImpl;
import com.epam.shevchenko.service.exception.ServiceException;

public class SetToBan extends BaseCommand{

	@Override
	public String execute(Map<String, String> requestParams) {
		 //id is not available as input for user
			int userId = Integer.parseInt(requestParams.get(ReqRespMapping.USER_ID));
			
			User user = new User();
			user.setId(userId);
			user.setUserStatus(UserStatus.BANNED);
			
			ClientService clientService = new ClientServiceImpl();

			try {
				user = clientService.changeUserStatus(user);
			} catch (ServiceException e) {
				log.error("Problem during updating profile." + e);
			}

			String response = "";
			if (user != null) {
				response = createPositiveResponse(user);
			} else {
				String message = "Fail updating profile";
				response = createNegativeResponse(message);
			}
			return response;
	}
}
