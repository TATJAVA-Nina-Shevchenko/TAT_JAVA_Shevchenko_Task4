package com.epam.shevchenko.controller;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.epam.shevchenko.bean.User;
import com.epam.shevchenko.constant.UserStatus;

public class FrontControllerTest {

	// ******Data Providers***********
	@DataProvider
	public Object[][] dp() {
		return new Object[][] {
				
//				new Object[] { "sessionId = user; command = bla_bla_bla", "wrong request" },
//				new Object[] { "sessionId = user;  command  = add_book; book_title=First Book;  book_author = unknown", "Not enough rights for command" },
//				new Object[] { "command = login; user_login=Nina; user_password=11111", "successfully logged" },
//				new Object[] { "command = login;", "error log" },
//				new Object[] { "command = registration; user_login=Kiki;  user_password=11111", "user created" },
//				new Object[] { "sessionId = admin; command = add_book; book_title=First Book;  book_author = unknown", "Book has been successfully added" },
//				new Object[] { "command = show_all_books", "" },
//				new Object[] { "command = show_user_profile; sessionId = user; user_id = 4", "user_id_0 = 4;user_login_0 = hhh;user_telephone_0 = bnfg;" },
//				new Object[] { "command = update_profile; sessionId = user; user_id = 3; user_login=Maxim;  user_password=changedPass2; user_telephone = +375 29 5557777", "user_id_0 = 3;user_login_0 = Maxim;user_telephone_0 = +375 29 5557777;" },
//				new Object[] { "command = set_to_admin; sessionId = admin; user_id = 6", "user_id_0 = 6;user_login_0 = Kiki;user_telephone_0 = ;user_status_id_0 = ADMIN;" },
//				new Object[] { "command = set_to_user; sessionId = super_admin; user_id = 6", "user_id_0 = 6;user_login_0 = Kiki;user_telephone_0 = ;user_status_id_0 = USER;" },
//				new Object[] { "command = reserve_books; sessionId = user; user_id = 1; book_id_0 = 1", "Books was successfully added" },
//				new Object[] { "command = refuse_from_order; sessionId = user; order_id = 2", "Order was successfully abandoned" },
//				new Object[] { "command = refuse_from_book; sessionId = user; user_id = 1; book_id = 1", "Book was deleted from order" },
				new Object[] { "command = update_book; sessionId = admin; book_id = 2; book_title=First Book;  book_author = unknown", "" },
		};
	}

	@BeforeTest
	public void createArtificialSessions(){
		Map<String, User> artificialSessions = new HashMap<String, User>();
			
		//user rights
		User user = new User();
		user.setUserStatus(UserStatus.USER);
		artificialSessions.put("user", user);
		
		//admin rights
		user = new User();
		user.setUserStatus(UserStatus.ADMIN);
		artificialSessions.put("admin", user);
		
		//super admin rights
		user = new User();
		user.setUserStatus(UserStatus.SUPER_ADMIN);
		artificialSessions.put("super_admin", user);
		
		FrontController.setOpenedSessions(artificialSessions);
		
	}
	
	@Test(dataProvider = "dp")
	public void testExecuteTask(String request, String responseExpected) {
		
		FrontController controller = new FrontController();
		String responseActual = controller.executeTask(request);
	
		assertEquals(responseActual, responseExpected);
	}
	
	
}
