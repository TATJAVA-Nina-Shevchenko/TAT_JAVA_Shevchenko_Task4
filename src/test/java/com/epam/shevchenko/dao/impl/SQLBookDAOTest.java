package com.epam.shevchenko.dao.impl;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.epam.shevchenko.bean.Book;
import com.epam.shevchenko.constant.BookStatus;
import com.epam.shevchenko.dao.exception.DAOException;

public class SQLBookDAOTest {

	// @Test
	// public void getSelectQuery() {
	// throw new RuntimeException("Test not implemented");
	// }

	@Test
	public void testShowAll() throws DAOException {
		List<Book> expectedBooks = new ArrayList<Book>();
		Book book = new Book(1, "Oooo", "aaaa");
		expectedBooks.add(book);
		book = new Book(2, "ttyy", "yytt");
		expectedBooks.add(book);
		
		List<Book> actualBooks = new SQLBookDAO().showAll();
		assertEquals(actualBooks, expectedBooks);
	}

	@Test
	public void testGetById() throws DAOException {
		Book expectedBook = new Book(1, "Oooo", "aaaa");

		Book actualBook = new SQLBookDAO().getById(1);
		assertEquals(actualBook, expectedBook);
	}

	@Test
	public void testUpdate() throws DAOException {
		Book expectedBook = new Book(1, "iiii", "pppp");

		new SQLBookDAO().update(expectedBook);
		Book actualBook = new SQLBookDAO().getById(1);
		expectedBook.setBookStatus(BookStatus.AVAILABLE);
		assertEquals(actualBook, expectedBook);
		
		
		expectedBook = new Book(1, "ttyy", "yytt");
		new SQLBookDAO().update(expectedBook);
	}
	
	@Test
	public void testAdd() throws DAOException {
		Book expectedBook = new Book(4, "pppp", "4444");

		new SQLBookDAO().add(new Book("pppp", "4444"));
		Book actualBook = new SQLBookDAO().getById(4);
		assertEquals(actualBook, expectedBook);
				
	}
}
