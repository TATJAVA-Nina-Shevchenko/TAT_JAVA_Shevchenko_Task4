package com.epam.shevchenko.controller.command.admin;

import java.util.Map;

import com.epam.shevchenko.bean.Book;
import com.epam.shevchenko.constant.BookStatus;
import com.epam.shevchenko.constant.ReqRespMapping;
import com.epam.shevchenko.controller.command.BaseCommand;
import com.epam.shevchenko.service.BookService;
import com.epam.shevchenko.service.BookServiceImpl;
import com.epam.shevchenko.service.exception.ServiceException;

public class DeleteBook extends BaseCommand{

	@Override
	public String execute(Map<String, String> requestParams) {
		 //id is not available as input for book
		int bookId = Integer.parseInt(requestParams.get(ReqRespMapping.BOOK_ID));
		
		Book book = new Book();
		book.setId(bookId);
		book.setBookStatus(BookStatus.DELETED);
		
		BookService bookService = new BookServiceImpl();

		try {
			book = bookService.changeBookStatus(book);
		} catch (ServiceException e) {
			log.info("Problem during updating profile.");
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
