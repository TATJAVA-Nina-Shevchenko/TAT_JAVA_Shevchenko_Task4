package com.epam.shevchenko.service;

import java.util.List;

import com.epam.shevchenko.bean.Book;
import com.epam.shevchenko.dao.BookDAO;
import com.epam.shevchenko.dao.exception.DAOException;
import com.epam.shevchenko.dao.impl.SQLBookDAO;
import com.epam.shevchenko.service.exception.ServiceException;

public class BookServiceImpl implements BookService {

	@Override
	public List<Book> showAllBooks() throws ServiceException{
		List <Book> books = null;
		BookDAO bookDAO = new SQLBookDAO();
		try {
			books = bookDAO.showAll();
		} catch (DAOException e) {
			throw new ServiceException();
		}
		return books;
	}

	
}
