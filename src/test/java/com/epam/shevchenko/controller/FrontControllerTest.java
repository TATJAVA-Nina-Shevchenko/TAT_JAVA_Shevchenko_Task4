package com.epam.shevchenko.controller;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.epam.shevchenko.bean.User;
import com.epam.shevchenko.enums.UserStatus;

public class FrontControllerTest {

	// ******Data Providers***********
	@DataProvider
	public Object[][] dp() {
		return new Object[][] {
//			new Object[] { "sessionId = 1111; command = add_book", "added book" },
//				new Object[] { "sessionId = 1111; command = bla_bla_bla", "wrong request" },
				new Object[] { "command = login; login=Nina; password=11111", "successfully logged" },
				new Object[] { "command = login;", "error log" },

		};
	}

	@Test(dataProvider = "dp")
	public void testExecuteTask(String request, String responseExpected) {
		Map<String, User> artificialSessions = new HashMap<String, User>();
		User user = new User();
		user.setUserStatus(UserStatus.USER);
		artificialSessions.put("1111", user);
		FrontController.setOpenedSessions(artificialSessions);
		
		FrontController controller = new FrontController();
		String responseActual = controller.executeTask(request);
	
		assertEquals(responseActual, responseExpected);
	}
}
