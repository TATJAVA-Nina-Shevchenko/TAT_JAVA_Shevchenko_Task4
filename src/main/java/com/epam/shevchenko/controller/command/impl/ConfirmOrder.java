package com.epam.shevchenko.controller.command.impl;

import java.util.Map;

import com.epam.shevchenko.bean.Order;
import com.epam.shevchenko.constant.OrderStatus;
import com.epam.shevchenko.constant.ReqRespMapping;
import com.epam.shevchenko.controller.command.BaseCommand;
import com.epam.shevchenko.service.OrderService;
import com.epam.shevchenko.service.OrderServiceImpl;
import com.epam.shevchenko.service.exception.ServiceException;

public class ConfirmOrder extends BaseCommand {

	@Override
	public String execute(Map<String, String> requestParams) {
		Order order = new Order();
		order.setId(Integer.parseInt(requestParams.get(ReqRespMapping.ORDER_ID)));
		order.setOrderStatus(OrderStatus.LEASED);

		OrderService orderService = new OrderServiceImpl();

		try {
			order = orderService.changeOrderStatus(order);
		} catch (ServiceException e) {
			log.error("Problem during refused order." + e);
		}

		String response = "";
		if (order != null) {
			String message = "Order was successfully leased";
			response = createPositiveResponse(message);
		} else {
			String message = "Fail leasing order";
			response = createNegativeResponse(message);
		}
		return response;
	}

}
