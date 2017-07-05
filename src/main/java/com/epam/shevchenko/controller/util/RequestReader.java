package com.epam.shevchenko.controller.util;

import java.util.HashMap;
import java.util.Map;

public class RequestReader {
	private static final String PARAM_DELIMITER = ";";
	private static final char ARGS_VALUE_DELIMITER = '=';

	public static Map<String, String> readParams(String request) {
		Map<String, String> requestParams = new HashMap<String, String>();
		String[] params = request.split(PARAM_DELIMITER);
		String arg = "";
		String value = "";
		for (int i = 0; i < params.length; i++) {
			// TODO a potencial bug: if string do not contains args value. Done
			if (params[i].indexOf(ARGS_VALUE_DELIMITER) != -1) {
				arg = params[i].substring(0, params[i].indexOf(ARGS_VALUE_DELIMITER)).trim();
				value = params[i].substring((params[i].indexOf(ARGS_VALUE_DELIMITER) + 1), params[i].length()).trim();
			} else {
				arg = params[i].trim();
			}
			requestParams.put(arg, value);
		}
		return requestParams;
	}

}
