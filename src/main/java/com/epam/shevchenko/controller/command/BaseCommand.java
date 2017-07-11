package com.epam.shevchenko.controller.command;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.shevchenko.bean.Book;
import com.epam.shevchenko.bean.User;
import com.epam.shevchenko.constant.ReqRespMapping;
import com.epam.shevchenko.controller.util.ResponseWriter;

public abstract class BaseCommand implements Command {
	protected Logger log = Logger.getLogger(getClass());

	protected String createPositiveResponse(User user) {
		// wraps user into list to use common method
		List<User> wrapedUser = new ArrayList<User>();
		wrapedUser.add(user);

		String response = ResponseWriter.writeUsersToResponse(wrapedUser);
		return response;
	}

	protected  String createPositiveResponse(User user, String sessionId) {
		String response = "";
		response += ResponseWriter.writeToResponse(ReqRespMapping.SESSION_ID, sessionId);
		response += createPositiveResponse(user);
		response = "successfully logged";
		return response;
	}

	protected String createPositiveResponse(Book book) {
		// wraps book into list to use common method
		List<Book> wrapedBook = new ArrayList<Book>();
		wrapedBook.add(book);

		String response = ResponseWriter.writeBooksToResponse(wrapedBook);
		return response;
	}

	protected String createPositiveResponse(String message) {
		return ResponseWriter.writeToResponse(ReqRespMapping.SUCCESS_MESSAGE, message);
	}

	protected String createNegativeResponse(String message) {
		return ResponseWriter.writeToResponse(ReqRespMapping.ERROR_MESSAGE, message);
	}

}
