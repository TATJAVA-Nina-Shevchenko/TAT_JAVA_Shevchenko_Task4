package com.epam.shevchenko.controller.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.epam.shevchenko.bean.Book;

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
			arg = "book_id_" + i;
			value = String.valueOf(book.getId());
			response += String.format(RESPONSE_PATTERN, arg, value);
			
			arg = "book_title_" + i;
			value = book.getTitle();
			response += String.format(RESPONSE_PATTERN, arg, value);

			arg = "book_author_" + i;
			value = book.getAuthor();
			response += String.format(RESPONSE_PATTERN, arg, value);
		}

		return response;
	}

}
