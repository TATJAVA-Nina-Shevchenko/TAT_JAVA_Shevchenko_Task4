package com.epam.shevchenko.controller.command.admin;

import java.util.Map;

import com.epam.shevchenko.bean.Book;
import com.epam.shevchenko.constant.ReqRespMapping;
import com.epam.shevchenko.controller.command.BaseCommand;
import com.epam.shevchenko.service.BookService;
import com.epam.shevchenko.service.BookServiceImpl;
import com.epam.shevchenko.service.exception.ServiceException;


public class AddBook extends BaseCommand {

	public String execute(Map<String, String> requestParams) {
		
		String title = requestParams.get(ReqRespMapping.BOOK_TITLE);
		String author = requestParams.get(ReqRespMapping.BOOK_AUTHOR);
				
		BookService bookService = new BookServiceImpl();
		Book book = new Book(title, author);
		boolean bookAddSucceed = false;
		try {
			bookAddSucceed = bookService.addBook(book);
		} catch (ServiceException e) {
			log.error("Problem during add book." + e);
		}

		
		String response = "";
		if (bookAddSucceed) {
			String message = "Book has been successfully added";
			response = createPositiveResponse(message);
		} else {
			String message = "Fail adding book";
			response = createNegativeResponse(message);
		}
		return response;
	}



	

}
