package com.epam.shevchenko.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.shevchenko.dao.BaseDAO;
import com.epam.shevchenko.dao.exception.DAOException;
import com.epam.shevchenko.dao.util.ConnectionManager;

public abstract class SQLBaseDAO<T> implements BaseDAO<T> {
	
	protected abstract String getSelectQuery();

	protected abstract List<T> parseResultSet(ResultSet rs)  throws SQLException;

	public List<T> showAll() throws DAOException {
		Connection con = ConnectionManager.getInstance().getConnection();
		PreparedStatement prStatement;
		List<T> result = new ArrayList<T>();
		String sql = getSelectQuery();
		try {
			prStatement = con.prepareStatement(sql);
			ResultSet rs = prStatement.executeQuery();
			result = parseResultSet(rs);
		} catch (SQLException e) {
			throw new DAOException("Error while showing all", e);
		}
		return result;
	}

	public void delete(T t) throws DAOException {
		// TODO Auto-generated method stub

	}

	public void delete(int id) throws DAOException {
		// TODO Auto-generated method stub

	}

	public void add(T t) throws DAOException {
		// TODO Auto-generated method stub

	}

	public void update(T t) throws DAOException {
		// TODO Auto-generated method stub

	}

	public T getById(int id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
