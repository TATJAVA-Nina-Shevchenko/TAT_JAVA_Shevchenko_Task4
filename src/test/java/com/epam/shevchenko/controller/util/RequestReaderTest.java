package com.epam.shevchenko.controller.util;

import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.testng.annotations.Test;

public class RequestReaderTest {

  @Test
  public void testReadParams() {
	  String stringParams = "secureKey=1111; login = Nina; password =; message; commandName = add";
	  
	  Map<String, String> params = RequestReader.readParams(stringParams);
	  assertEquals(params.get("secureKey"), "1111");
	  assertEquals(params.get("login"), "Nina");
	  assertEquals(params.get("password"), "");
	  assertEquals(params.get("message"), "");
	  assertEquals(params.get("commandName"), "add");
	
  }
}
