package com.epam.shevchenko.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.epam.shevchenko.bean.Book;
import com.epam.shevchenko.service.exception.ServiceException;

public interface BookService {

	List<Book> showAllBooks() throws ServiceException;

}
