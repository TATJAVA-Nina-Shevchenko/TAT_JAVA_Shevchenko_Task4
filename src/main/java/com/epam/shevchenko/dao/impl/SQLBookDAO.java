package com.epam.shevchenko.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.shevchenko.bean.Book;
import com.epam.shevchenko.dao.BookDAO;
import com.epam.shevchenko.dao.exception.DAOException;
import com.epam.shevchenko.enums.TableMapping;

public class SQLBookDAO extends SQLBaseDAO<Book> implements BookDAO {

	private static final String SELECT_ALL_SQL = "SELECT * FROM library.books";
	
	@Override
	protected String getSelectQuery() {
		return SELECT_ALL_SQL;
	}

	@Override
	protected List<Book> parseResultSet(ResultSet rs) throws SQLException {
		List <Book> result = new ArrayList<Book>();
		Book book = new Book();
		while(rs.next()){
			book.setTitle(rs.getString(TableMapping.COLUMN_NAME_BOOK_TITLE));
			book.setAuthor(rs.getString(TableMapping.COLUMN_NAME_BOOK_AUTHOR));
			result.add(book);
		}
		return result;
	}

	
	public void delete(com.epam.shevchenko.bean.Book t) throws DAOException {
		// TODO Auto-generated method stub

	}

	public void add(com.epam.shevchenko.bean.Book t) throws DAOException {
		// TODO Auto-generated method stub

	}

	public void update(com.epam.shevchenko.bean.Book t) throws DAOException {
		// TODO Auto-generated method stub

	}

	public com.epam.shevchenko.bean.Book getById(int id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}



}
