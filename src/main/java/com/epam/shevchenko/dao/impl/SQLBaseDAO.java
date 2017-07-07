package com.epam.shevchenko.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.shevchenko.dao.BaseDAO;
import com.epam.shevchenko.dao.exception.DAOException;
import com.epam.shevchenko.dao.exception.ResultSetClosingErrorDAOException;
import com.epam.shevchenko.dao.exception.StatementClosingErrorDAOException;
import com.epam.shevchenko.dao.util.ConnectionManager;

public abstract class SQLBaseDAO<T> implements BaseDAO<T> {
	private static final String WHERE_CLAUSE_BY_ID_SQL = " WHERE id=?";

	protected abstract String getSelectQuery();

	protected abstract String getUpdateQuery();

	protected abstract String getAddQuery();

	protected abstract PreparedStatement updateStatement(PreparedStatement prStatement, T t) throws SQLException;
	
	protected abstract List<T> parseResultSet(ResultSet rs) throws SQLException;

	public List<T> showAll() throws DAOException {
		Connection con = ConnectionManager.getInstance().getConnection();
		List<T> result = new ArrayList<T>();
		String sql = getSelectQuery();

		PreparedStatement prStatement = null;
		ResultSet rs = null;
		try {
			prStatement = con.prepareStatement(sql);
			rs = prStatement.executeQuery();
			result = parseResultSet(rs);
		} catch (SQLException e) {
			throw new DAOException("Error while showing all", e);
		}finally {
			closeResultSet(rs);
			closeStatement(prStatement);
		}
		
		return result;
	}

	public T getById(long id) throws DAOException {
		Connection con = ConnectionManager.getInstance().getConnection();
		List<T> result;
		String sql = getSelectQuery() + WHERE_CLAUSE_BY_ID_SQL;

		PreparedStatement prStatement = null;
		ResultSet rs = null;
		try {
			prStatement = con.prepareStatement(sql);
			prStatement.setLong(1, id);
			rs = prStatement.executeQuery();
			result = parseResultSet(rs);
		} catch (SQLException e) {
			throw new DAOException("Error while showing all", e);
		} finally {
			closeResultSet(rs);
			closeStatement(prStatement);
		}

		if (result == null || result.size() == 0) {
			return null;
		}
		if (result.size() != 1) {
			throw new DAOException("Error. More than one record received");
		}
		return result.iterator().next();
	}

	public void update(T t) throws DAOException {
		Connection con = ConnectionManager.getInstance().getConnection();
		String sql = getUpdateQuery();
		int isUpdated = -1;
		PreparedStatement prStatement = null;
		try {
			prStatement = con.prepareStatement(sql);
			prStatement = updateStatement(prStatement, t);
			prStatement.executeUpdate();
			isUpdated = prStatement.getUpdateCount();
		} catch (SQLException e) {
			throw new DAOException("Error while updating", e);
		} finally {
			closeStatement(prStatement);
		}

		if (isUpdated == -1) {
			throw new DAOException("Error while updating");
		}
	}

	public void add(T t) throws DAOException {
		Connection con = ConnectionManager.getInstance().getConnection();
		String sql = getAddQuery();
		int isUpdated = -1;
		PreparedStatement prStatement = null;
		try {
			prStatement = con.prepareStatement(sql);
			prStatement = updateStatement(prStatement, t);
			prStatement.executeUpdate();
			isUpdated = prStatement.getUpdateCount();
		} catch (SQLException e) {
			throw new DAOException("Error while adding", e);
		} finally {
			closeStatement(prStatement);
		}

		if (isUpdated == -1) {
			throw new DAOException("Error while adding");
		}

	}

	public void delete(int id) throws DAOException {
		// TODO Auto-generated method stub

	}

	public void delete(T t) throws DAOException {
		// TODO Auto-generated method stub

	}

	public void closeResultSet(ResultSet resultSet) throws ResultSetClosingErrorDAOException {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			throw new ResultSetClosingErrorDAOException("SQL exception closing result set: " + e, e);
		}
	}

	public void closeStatement(Statement statement) throws StatementClosingErrorDAOException {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			throw new StatementClosingErrorDAOException("SQL exception closing statement: " + e, e);
		}
	}

}
