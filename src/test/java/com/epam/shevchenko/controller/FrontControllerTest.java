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
//				new Object[] { "command = login; user_login=Nina; user_password=11111", "successfully logged" },
//				new Object[] { "command = login;", "error log" },
//				new Object[] { "command = registration; user_login=Kiki;  user_password=11111", "user created" },
//				new Object[] { "command = add_book; book_title=First Book;  book_author = unknown", "book addded" },
//				new Object[] { "command = show_all_books", "" },
				new Object[] { "command = show_user_profile; sessionId = 1111; user_id = 3", "user_id_0 = 3;user_login_0 = Maxim;user_contact_data_0 = +632;" },
				
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
