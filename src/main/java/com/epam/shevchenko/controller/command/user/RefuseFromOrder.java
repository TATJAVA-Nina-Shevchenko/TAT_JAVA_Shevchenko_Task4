package com.epam.shevchenko.controller.command.user;

import java.util.Map;

import com.epam.shevchenko.bean.Order;
import com.epam.shevchenko.constant.OrderStatus;
import com.epam.shevchenko.constant.ReqRespMapping;
import com.epam.shevchenko.controller.command.BaseCommand;
import com.epam.shevchenko.service.OrderService;
import com.epam.shevchenko.service.OrderServiceImpl;
import com.epam.shevchenko.service.exception.ServiceException;

public class RefuseFromOrder extends BaseCommand {

	@Override
	public String execute(Map<String, String> requestParams) {

		Order order = new Order();
		order.setId(Integer.parseInt(requestParams.get(ReqRespMapping.ORDER_ID)));
		order.setOrderStatus(OrderStatus.REFUSED);

		OrderService orderService = new OrderServiceImpl();

		try {
			order = orderService.changeOrderStatus(order);
		} catch (ServiceException e) {
			log.error("Problem during refused order." + e);
		}

		String response = "";
		if (order != null) {
			String message = "Order was successfully abandoned";
			response = createPositiveResponse(message);
		} else {
			String message = "Fail refusing order";
			response = createNegativeResponse(message);
		}
		return response;
	}

}
