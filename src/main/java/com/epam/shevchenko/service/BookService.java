package com.epam.shevchenko.service;

import java.util.List;

import com.epam.shevchenko.bean.Book;
import com.epam.shevchenko.service.exception.ServiceException;

public interface BookService {

	List<Book> showAllBooks() throws ServiceException;

	boolean addBook(Book book) throws ServiceException;

	Book updateBook(Book book) throws ServiceException;

}
