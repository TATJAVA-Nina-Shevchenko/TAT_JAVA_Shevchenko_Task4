package com.epam.shevchenko.dao.impl;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.epam.shevchenko.bean.Book;
import com.epam.shevchenko.dao.exception.DAOException;

public class SQLBookDAOTest {

//  @Test
//  public void getSelectQuery() {
//    throw new RuntimeException("Test not implemented");
//  }
  
	
	
  @Test
  public void testShowAll() throws DAOException{
	  List<Book> expectedBooks = new ArrayList<Book>();
	  Book book = new Book("Oooo", "aaaa");
	  expectedBooks.add(book); 
	  
	  List<Book> actualBooks = new SQLBookDAO().showAll();
	  assertEquals(actualBooks, expectedBooks);
  }
}
