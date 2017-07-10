package com.epam.shevchenko.service;

import java.util.List;

import com.epam.shevchenko.bean.Order;
import com.epam.shevchenko.service.exception.ServiceException;

public interface OrderService {

	boolean reserveBooks(int userId, List<Integer> booksId)  throws ServiceException;

	Order changeOrderStatus(Order order) throws ServiceException;

}
