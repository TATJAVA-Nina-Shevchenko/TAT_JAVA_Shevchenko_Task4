package com.epam.shevchenko.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.shevchenko.bean.User;
import com.epam.shevchenko.dao.UserDAO;
import com.epam.shevchenko.dao.exception.DAOException;
import com.epam.shevchenko.dao.util.ConnectionManager;
import com.epam.shevchenko.enums.TableMapping;
import com.epam.shevchenko.enums.UserStatus;


public class SQLUserDAO extends SQLBaseDAO<User> implements UserDAO{
	
	private static final String SELECT_ALL_SQL = "SELECT * FROM library.users LEFT JOIN library.user_status ON library.users.user_status_id = library.user_status.id";
	private static final String WHERE_CLAUSE_BY_LOGIN_AND_PASS_SQL = " WHERE login=? AND password=?";
	
	@Override
	protected String getSelectQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getUpdateQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getAddQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected PreparedStatement updateStatement(PreparedStatement prStatement, User t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<User> parseResultSet(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public User getUser(String login,String password) throws DAOException{
		User user= null;
		Connection con = ConnectionManager.getInstance().getConnection();
		String sql = SELECT_ALL_SQL + WHERE_CLAUSE_BY_LOGIN_AND_PASS_SQL ;
		try {
			PreparedStatement prStatement = con.prepareStatement(sql);
			prStatement.setString(1, login);
			prStatement.setString(2, password);
			ResultSet rs = prStatement.executeQuery();
			rs.next();	
			user = initUser(rs);
		} catch (SQLException e) {
			throw new DAOException("Error while showing all", e);
		}
	
		return user;
	}

	private User initUser(ResultSet rs) throws SQLException {
		User user = new User ();
		user.setId(rs.getInt(TableMapping.COLUMN_NAME_USER_ID));
		user.setLogin(rs.getString(TableMapping.COLUMN_NAME_USER_LOGIN));
		user.setPassword(rs.getString(TableMapping.COLUMN_NAME_USER_PASSWORD));
		user.setTelephone(rs.getString(TableMapping.COLUMN_NAME_USER_TELEPHONE));
		user.setUserStatus(UserStatus.valueOf(rs.getString(TableMapping.COLUMN_NAME_USER_STATUS_STATUS).toUpperCase()));
		
		return user;
	}

}
