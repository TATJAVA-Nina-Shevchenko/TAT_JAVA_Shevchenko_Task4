package com.epam.shevchenko.controller.command.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.epam.shevchenko.bean.User;
import com.epam.shevchenko.controller.command.BaseCommand;
import com.epam.shevchenko.controller.util.ResponseWriter;
import com.epam.shevchenko.enums.ReqRespMapping;
import com.epam.shevchenko.service.ClientService;
import com.epam.shevchenko.service.ClientServiceImpl;
import com.epam.shevchenko.service.exception.ServiceException;

public class ShowUserProfile extends BaseCommand {

	@Override
	public String execute(Map<String, String> requestParams) {
		int userId = Integer.parseInt(requestParams.get(ReqRespMapping.USER_ID));
		ClientService clientService = new ClientServiceImpl();

		User user = null;

		try {
			user = clientService.getUser(userId);
		} catch (ServiceException e) {
			log.info("Problem during login.");
		}

		String response = "";
		if (user != null) {
			response = createPositiveResponse(user);
		} else {
			response = createNegativeResponse();
		}
		return response;
	}

	private String createPositiveResponse(User user) {
		// wraps user into list to use common method
		List<User> wrapedUser = new ArrayList<User>();
		wrapedUser.add(user);

		String response = ResponseWriter.writeUsersToResponse(wrapedUser);
		return response;
	}

	private String createNegativeResponse() {
		String response;
		response = "No such user found";
		// TODO formatted response 
		return response;
		
	}

}
