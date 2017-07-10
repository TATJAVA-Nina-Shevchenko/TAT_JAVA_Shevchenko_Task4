package com.epam.shevchenko.dao;

import java.util.List;

import com.epam.shevchenko.bean.Order;
import com.epam.shevchenko.dao.exception.DAOException;

public interface OrderDAO extends BaseDAO<Order> {

	void reserveBooks(int userId, List<Integer> booksId) throws DAOException;

	void refusedFromOrder(int userId, int orderId) throws DAOException;

}
