package com.epam.shevchenko.controller.util;

import java.util.List;
import java.util.Map;

import com.epam.shevchenko.bean.Book;
import com.epam.shevchenko.bean.User;
import com.epam.shevchenko.constant.ReqRespMapping;

public class ResponseWriter {

	private static final String RESPONSE_PATTERN = "%s = %s;";

	public static String writeParamsToResponse(Map<String, String> params) {
		String response = "";

		for (Map.Entry<String, String> entry : params.entrySet()) {
			String arg = entry.getKey();
			String value = entry.setValue(arg);
			response += String.format(RESPONSE_PATTERN, arg, value);
		}

		return response;
	}

	public static String writeBooksToResponse(List<Book> params) {
		String response = "";
		int i = 0;
		String arg = "";
		String value = "";
		
		for (Book book : params) {
			arg = ReqRespMapping.BOOK_ID + "_" + i;
			value = String.valueOf(book.getId());
			response += String.format(RESPONSE_PATTERN, arg, value);
			
			arg = ReqRespMapping.BOOK_TITLE + "_" + i;
			value = book.getTitle();
			response += String.format(RESPONSE_PATTERN, arg, value);

			arg = ReqRespMapping.BOOK_AUTHOR + "_" + i;
			value = book.getAuthor();
			response += String.format(RESPONSE_PATTERN, arg, value);
			i++;
		}

		return response;
	}
	
	public static String writeUsersToResponse(List<User> params) {
		String response = "";
		int i = 0;
		String arg = "";
		String value = "";
		
		for (User user: params) {
			arg = ReqRespMapping.USER_ID + "_" + i;
			value = String.valueOf(user.getId());
			response += String.format(RESPONSE_PATTERN, arg, value);
			
			arg = ReqRespMapping.USER_LOGIN + "_" + i;
			value = user.getLogin();
			response += String.format(RESPONSE_PATTERN, arg, value);

			arg = ReqRespMapping.USER_TELEPHONE + "_" + i;
			value = user.getTelephone();
			response += String.format(RESPONSE_PATTERN, arg, value);
			i++;
		}

		return response;
	}

}
