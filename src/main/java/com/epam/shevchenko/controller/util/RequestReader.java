package com.epam.shevchenko.controller.util;

import java.util.HashMap;
import java.util.Map;

// Provides methods to read requests in list
public class RequestReader {
	private static final String PARAM_DELIMITER = ";"; //Is used to delimit different params
	private static final char ARGS_VALUE_DELIMITER = '='; //Is used to delimit values from params

	
	//read params from string request and return them as a map <parameter, value>
	
	public static Map<String, String> readParams(String request) {
		Map<String, String> requestParams = new HashMap<String, String>();
		String[] params = request.split(PARAM_DELIMITER);
		
		String arg = "";
		String value = "";
		for (int i = 0; i < params.length; i++) {
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
