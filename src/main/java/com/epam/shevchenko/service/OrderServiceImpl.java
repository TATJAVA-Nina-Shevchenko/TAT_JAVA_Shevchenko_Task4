package com.epam.shevchenko.service;

import java.util.List;

import com.epam.shevchenko.bean.Order;
import com.epam.shevchenko.dao.OrderDAO;
import com.epam.shevchenko.dao.exception.DAOException;
import com.epam.shevchenko.dao.impl.SQLOrderDAO;
import com.epam.shevchenko.service.exception.ServiceException;

public class OrderServiceImpl implements OrderService{

	@Override
	public boolean reserveBooks(int userId, List<Integer> booksId) throws ServiceException {
		OrderDAO orderDAO = new SQLOrderDAO();
		try {
			orderDAO.reserveBooks(userId, booksId);
		} catch (DAOException e) {
			throw new ServiceException("Error in services during reserve books", e);
		}
		return true;
	}

	@Override
	public Order changeOrderStatus(Order order) throws ServiceException {
	OrderDAO orderDAO = new SQLOrderDAO();
		
		try {
			orderDAO.update(order);;
//			order = orderDAO.getById(order.getId());
		} catch (DAOException e) {
			throw new ServiceException();
		}

		return order;
	}

}
