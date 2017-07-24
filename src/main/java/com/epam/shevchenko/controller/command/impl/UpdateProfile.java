package com.epam.shevchenko.controller.command.impl;

import java.util.Map;

import com.epam.shevchenko.bean.User;
import com.epam.shevchenko.constant.ReqRespMapping;
import com.epam.shevchenko.controller.command.BaseCommand;
import com.epam.shevchenko.service.ClientService;
import com.epam.shevchenko.service.ClientServiceImpl;
import com.epam.shevchenko.service.exception.ServiceException;

public class UpdateProfile extends BaseCommand {

	@Override
	public String execute(Map<String, String> requestParams) {
			
		int userId = Integer.parseInt(requestParams.get(ReqRespMapping.USER_ID)); //id is not available as input for user
		String login = requestParams.get(ReqRespMapping.USER_LOGIN);
		
		//fields available for editing
		String password = requestParams.get(ReqRespMapping.USER_PASSWORD);
		String telephone = requestParams.get(ReqRespMapping.USER_TELEPHONE);

		User user = new User();
		user.setId(userId);
		user.setLogin(login);;
		user.setPassword(password);
		user.setTelephone(telephone);
		
		ClientService clientService = new ClientServiceImpl();

		try {
			user = clientService.updateUser(user);
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
