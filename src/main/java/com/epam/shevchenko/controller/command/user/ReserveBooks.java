package com.epam.shevchenko.controller.command.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.epam.shevchenko.constant.ReqRespMapping;
import com.epam.shevchenko.controller.command.BaseCommand;
import com.epam.shevchenko.service.OrderService;
import com.epam.shevchenko.service.OrderServiceImpl;
import com.epam.shevchenko.service.exception.ServiceException;

public class ReserveBooks extends BaseCommand {

	@Override
	public String execute(Map<String, String> requestParams) {
		List<Integer> booksId =new ArrayList<Integer>();
				
		for (Map.Entry<String, String> entry : requestParams.entrySet()) {
			if (entry.getKey().startsWith(ReqRespMapping.BOOK_ID) ){
				booksId.add(Integer.parseInt(entry.getValue()));
			  }
			}
		int userId = Integer.parseInt(requestParams.get(ReqRespMapping.USER_ID));
		OrderService orderService = new OrderServiceImpl();

		boolean bookAddSucceed = false;
		try {
			bookAddSucceed = orderService.reserveBooks(userId, booksId);
		} catch (ServiceException e) {
			log.info("Problem during create order.");
		}

		
		String response = "";
		if (bookAddSucceed) {
			String message = "Books was successfully added";
			response = createPositiveResponse(message);
		} else {
			String message = "Fail adding book";
			response = createNegativeResponse(message);
		}
		return response;
	}

	

}
