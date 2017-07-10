package com.epam.shevchenko.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.shevchenko.bean.Book;
import com.epam.shevchenko.bean.Order;
import com.epam.shevchenko.constant.BookStatus;
import com.epam.shevchenko.constant.TableMapping;
import com.epam.shevchenko.dao.OrderDAO;

public class SQLOrderDAO extends SQLBaseDAO<Order> implements OrderDAO{
	private static final String SELECT_ALL_SQL = "SELECT * FROM library.orders";
	
	private static final String ADD_ORDER_SQL = "INSERT INTO library.orders (users_id) VALUES (?)";

	//pattern for sql query to update order
	private static final String UPDATE_ORDER_SQL_PATTERN = "UPDATE library.orders SET %s WHERE id=?";
	

	@Override
	protected String getSelectQuery() {
		return SELECT_ALL_SQL;
	}


	@Override
	protected String getAddQuery() {
		return ADD_ORDER_SQL;
	}

	@Override
	protected List<Order> parseResultSet(ResultSet rs) throws SQLException {
		List<Order> result = new ArrayList<Order>();
		Order order;

		do {
			order = new Order();

			order.setId(rs.getInt(TableMapping.COLUMN_NAME_ORDER_ID));
			order.setUser(rs.getString(TableMapping.COLUMN_NAME_BOOK_TITLE));
			book.setAuthor(rs.getString(TableMapping.COLUMN_NAME_BOOK_AUTHOR));
			book.setBookStatus(BookStatus.getBookStatus(rs.getInt(TableMapping.COLUMN_NAME_BOOK_STATUS)));

			result.add(book);
		} while (rs.next());

		return result;
	}
	
	@Override
	protected PreparedStatement updateStatement(PreparedStatement prStatement, Order t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	protected String generateUpdateQuery(Order t) {
		// TODO Auto-generated method stub
		return null;
	}


	

}
