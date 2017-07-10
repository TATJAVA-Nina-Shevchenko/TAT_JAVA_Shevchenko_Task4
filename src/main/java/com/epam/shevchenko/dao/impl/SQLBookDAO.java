package com.epam.shevchenko.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.shevchenko.bean.Book;
import com.epam.shevchenko.constant.BookStatus;
import com.epam.shevchenko.constant.TableMapping;
import com.epam.shevchenko.dao.BookDAO;

public class SQLBookDAO extends SQLBaseDAO<Book> implements BookDAO {

	private static final String SELECT_ALL_SQL = "SELECT * FROM library.books";
//	private static final String WHERE_CLAUSE_AVAILABLE_SQL = " WHERE book_status='1'";
	
	private static final String ADD_BOOK_SQL = "INSERT INTO library.books (title, author) VALUES (?, ?)";

	//pattern for sql query to update book
	private static final String UPDATE_BOOK_SQL_PATTERN = "UPDATE library.books SET %s WHERE id=?";
	
	
	@Override
	protected String getSelectQuery() {
		return SELECT_ALL_SQL;
	}

	@Override
	protected String getAddQuery() {
		return ADD_BOOK_SQL;
	}

	@Override
	protected List<Book> parseResultSet(ResultSet rs) throws SQLException {
		List<Book> result = new ArrayList<Book>();
		Book book;

		do {
			book = new Book();

			book.setId(rs.getInt(TableMapping.COLUMN_NAME_BOOK_ID));
			book.setTitle(rs.getString(TableMapping.COLUMN_NAME_BOOK_TITLE));
			book.setAuthor(rs.getString(TableMapping.COLUMN_NAME_BOOK_AUTHOR));
			book.setBookStatus(BookStatus.getBookStatus(rs.getInt(TableMapping.COLUMN_NAME_BOOK_STATUS)));

			result.add(book);
		} while (rs.next());

		return result;
	}

	@Override
	protected PreparedStatement updateStatement(PreparedStatement prStatement, Book book) throws SQLException {
		if (prStatement != null) {
			prStatement.setString(1, book.getTitle());
			prStatement.setString(2, book.getAuthor());
			if (book.getId() != 0) {
				prStatement.setLong(3, book.getId());
			}
		}
		return prStatement;
	}


	@Override
	protected String generateUpdateQuery(Book book) {
		String resultQuery = "";
		String updatedValueSql = "";
		
		if(book.getAuthor() != null){
			updatedValueSql += String.format(VALUE_FOR_UPDATE_PATTERN, TableMapping.COLUMN_NAME_BOOK_AUTHOR, book.getAuthor()) ;
		}
		
		if(book.getTitle() != null){
			if (!updatedValueSql.isEmpty()){
				updatedValueSql += ",";
			}
			updatedValueSql += String.format(VALUE_FOR_UPDATE_PATTERN, TableMapping.COLUMN_NAME_BOOK_TITLE, book.getTitle());
		}
		
		if(book.getBookStatus() != null){
			if (!updatedValueSql.isEmpty()){
				updatedValueSql += ",";
			}
			updatedValueSql +=String.format(VALUE_FOR_UPDATE_PATTERN, TableMapping.COLUMN_NAME_BOOK_STATUS, book.getBookStatus().getBookStatusCode() );
		}	
		
		if (updatedValueSql.isEmpty()){
			return null;
		}
				
		resultQuery =String.format(UPDATE_BOOK_SQL_PATTERN, updatedValueSql);
		return resultQuery;
	}

}
