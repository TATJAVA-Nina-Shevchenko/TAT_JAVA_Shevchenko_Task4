package com.epam.shevchenko.controller.util;

import java.util.HashMap;
import java.util.Map;

public class ResponseWriter {
	
	private static final String RESPONSE_PATTERN = "%s = %s;";

	public static String writeParamsToResponse(Map<String, String> params) {
		Map<String, String> requestParams = new HashMap<String, String>();
		
		String response = "";
		for (Map.Entry<String, String> entry : params.entrySet()) {
			String arg = entry.getKey();
			String value = entry.setValue(arg);
			response += String.format(RESPONSE_PATTERN, arg, value);
		}

		return response;
	}

}
