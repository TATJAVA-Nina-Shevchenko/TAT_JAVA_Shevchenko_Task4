package com.epam.shevchenko.controller;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
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
				
				new Object[] { "session_id = user; command = bla_bla_bla", "error_message = Wrong request;" },
//				new Object[] { "session_id = user;  command  = add_book; book_title=First Book;  book_author = unknown", "error_message = Not enough rights for command;" },
//				new Object[] { "command = login; user_login=Nina; user_password=11111", "session_id = invqnl7mtu8k6jk6fgr5vlbai3;user_id_0 = 1;user_login_0 = Nina;user_telephone_0 = +354;user_status_id_0 = SUPER_ADMIN;success_message = successfully logged;" },
//				new Object[] { "command = login;", "error_message = Fail login;" },
//				new Object[] { "command = registration; user_login=Volha;  user_password=11111", "success_message = User was created;" },
//				new Object[] { "session_id = admin; command = add_book; book_title=First Book 4;  book_author = unknown", "success_message = Book has been successfully added;" },
				new Object[] { "command = show_all_books", "" },
//				new Object[] { "command = show_user_profile; session_id = user; user_id = 4", "user_id_0 = 4;user_login_0 = hhh;user_telephone_0 = bnfg;" },
//				new Object[] { "command = update_profile; session_id = user; user_id = 3; user_login=Maxim;  user_password=changedPass2; user_telephone = +375 29 5557777", "user_id_0 = 3;user_login_0 = Maxim;user_telephone_0 = +375 29 5557777;" },
//				new Object[] { "command = set_to_admin; session_id = admin; user_id = 6", "user_id_0 = 6;user_login_0 = Kiki;user_telephone_0 = ;user_status_id_0 = ADMIN;" },
//				new Object[] { "command = set_to_user; session_id = super_admin; user_id = 6", "user_id_0 = 6;user_login_0 = Kiki;user_telephone_0 = ;user_status_id_0 = USER;" },
//				new Object[] { "command = reserve_books; session_id = user; user_id = 1; book_id_0 = 1", "success_message = Books was successfully added" },
//				new Object[] { "command = refuse_from_order; session_id = user; order_id = 2", "success_message = Order was successfully abandoned" },
//				new Object[] { "command = refuse_from_book; session_id = user; user_id = 1; book_id = 1", "success_message = Book was deleted from order" },
//				new Object[] { "command = update_book; session_id = admin; book_id = 2; book_title=First Book;  book_author = unknown", "book_id_0 = 2;book_title_0 = First Book;book_author_0 = unknown;" },
//				new Object[] { "command = set_to_ban; session_id = admin; user_id = 5", "user_id_0 = 5;user_login_0 = hhh;user_telephone_0 = ;user_status_id_0 = BANNED;" },
//				new Object[] { "command = delete_book; session_id = admin; book_id = 3", "book_id_0 = 3;book_title_0 = hhhh;book_author_0 = hhhh;" },
//				new Object[] { "command = confirm_order; session_id= admin; order_id = 8", "success_message = Order was successfully leased" },
		};
	}

	@BeforeClass
	//TODO generate new database
	
	
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
	
	
	@AfterClass
	private void dropDb(){
		//TODO drop database
	}
	
	
}
