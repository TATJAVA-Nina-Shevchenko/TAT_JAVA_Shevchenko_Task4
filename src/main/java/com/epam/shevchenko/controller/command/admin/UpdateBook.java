package com.epam.shevchenko.controller.command.admin;

import java.util.Map;

import com.epam.shevchenko.bean.Book;
import com.epam.shevchenko.constant.ReqRespMapping;
import com.epam.shevchenko.controller.command.BaseCommand;
import com.epam.shevchenko.service.BookService;
import com.epam.shevchenko.service.BookServiceImpl;
import com.epam.shevchenko.service.exception.ServiceException;

public class UpdateBook extends BaseCommand{

	@Override
	public String execute(Map<String, String> requestParams) {
		
		//id is not available as input for user
		int bookId = Integer.parseInt(requestParams.get(ReqRespMapping.BOOK_ID)); 
				
		//fields available for editing
		String title = requestParams.get(ReqRespMapping.BOOK_TITLE);
		String author = requestParams.get(ReqRespMapping.BOOK_AUTHOR);

		Book book = new Book();
		book.setId(bookId);
		book.setTitle(title);;
		book.setAuthor(author);
		
		BookService bookService = new BookServiceImpl();

		try {
			book = bookService.updateBook(book);
		} catch (ServiceException e) {
			log.error("Problem during updating profile." + e);
		}

		String response = "";
		if (book != null) {
			response = createPositiveResponse(book);
		} else {
			String message = "Fail updating profile";
			response = createNegativeResponse(message);
		}
		return response;
	}
	

}
