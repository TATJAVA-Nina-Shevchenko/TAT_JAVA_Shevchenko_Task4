package com.epam.shevchenko.controller.command.common;

import java.util.List;
import java.util.Map;

import com.epam.shevchenko.bean.Book;
import com.epam.shevchenko.controller.command.BaseCommand;
import com.epam.shevchenko.controller.util.ResponseWriter;
import com.epam.shevchenko.service.BookService;
import com.epam.shevchenko.service.BookServiceImpl;
import com.epam.shevchenko.service.exception.ServiceException;

public class ShowAllBooks extends BaseCommand{

	public String execute(Map<String, String> requestParams) {

		BookService bookService = new BookServiceImpl();
		List<Book> books = null;
		try {
			books = bookService.showAllBooks();
		} catch (ServiceException e) {
			log.error("Problem during show all books." + e);
		}

		String response = "";
		if (books != null) {
			response = createPositiveResponse(books);
		} else {
			response = createNegativeResponse();
		}
		return response;
	}

	private String createPositiveResponse(List<Book> books) {
		String response = ResponseWriter.writeBooksToResponse(books);
		return response;
	}
	
	private String createNegativeResponse() {
		String response = "Book show all fail.";
		return response;
	}

}
