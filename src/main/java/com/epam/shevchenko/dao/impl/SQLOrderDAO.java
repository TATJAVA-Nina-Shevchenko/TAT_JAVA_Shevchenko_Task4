package com.epam.shevchenko.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.shevchenko.bean.Order;
import com.epam.shevchenko.bean.User;
import com.epam.shevchenko.constant.OrderStatus;
import com.epam.shevchenko.constant.TableMapping;
import com.epam.shevchenko.dao.OrderDAO;
import com.epam.shevchenko.dao.exception.DAOException;
import com.epam.shevchenko.dao.util.ConnectionManager;

public class SQLOrderDAO extends SQLBaseDAO<Order> implements OrderDAO {
	private static final String SELECT_ALL_SQL = "SELECT * FROM library.orders";

	private static final String ADD_ORDER_SQL = "INSERT INTO library.history (users_id, order_status_id) VALUES (? , ?)";
	private static final String ADD_BOOKS_IN_ORDER_SQL = "INSERT INTO library.history_has_books (history_id, books_id) VALUES (?, ?)";
	private static final String ADDITIONAL_INSERT_SQL = "(?, ?)";

	// pattern for sql query to update order
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

			User lazyUser = new User();
			lazyUser.setId(Long.parseLong(rs.getString(TableMapping.COLUMN_NAME_ORDER_USER)));
			order.setUser(lazyUser);
			result.add(order);
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

	@Override
	public void reserveBooks(int userId, List<Integer> booksId) throws DAOException {

		Connection con = ConnectionManager.getInstance().getConnection();
		String sql1 = ADD_ORDER_SQL;

		String sql2 = generateMultiAddSql(booksId);
		int isUpdated = -1;

		PreparedStatement prStatement1 = null;
		PreparedStatement prStatement2 = null;
		try {

			// transaction begins
			con.setAutoCommit(false);

			// first sql command
			prStatement1 = con.prepareStatement(sql1);
			prStatement1.setInt(1, userId);
			prStatement1.setInt(2, OrderStatus.RESERVED.getOrderStatusCode());
			prStatement1.executeUpdate();
			isUpdated = prStatement1.getUpdateCount();
			
			ResultSet rs = prStatement1.getGeneratedKeys();
			if (rs.next()) {
				int orderId = rs.getInt(1);
//			}
//
//			if (isUpdated != -1) {
				// second sql command
				prStatement2 = con.prepareStatement(sql2);
				for (int i = 0; i < booksId.size(); i++) {
					prStatement2.setInt(2 * i + 1, orderId);
					prStatement2.setInt(2 * i + 2, booksId.get(i));
				}
				prStatement2.executeUpdate();
				isUpdated = prStatement2.getUpdateCount();

				// transaction commit
				con.commit();
			}
		} catch (SQLException e) {
			cancelTransaction(con);
			throw new DAOException("Error while reserving books", e);
		} finally {
			closeStatement(prStatement1);
			closeStatement(prStatement2);
			closeTransaction(con);

		}

		if (isUpdated == -1) {
			throw new DAOException("Error while adding");
		}

	}

	private String generateMultiAddSql(List<Integer> booksId) {
		String sqlResult = ADD_BOOKS_IN_ORDER_SQL;
		if (booksId == null) {
			return null;
		}
		if (booksId.size() > 1) {
			int numberOfInsertions = booksId.size();
			for (int i = 1; i < numberOfInsertions; i++) {
				sqlResult += ", " + ADDITIONAL_INSERT_SQL;
			}
		}
		return sqlResult;

	}

}
