package com.epam.shevchenko.controller;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.epam.shevchenko.enums.UserStatus;

public class FrontControllerTest {

	// ******Data Providers***********
	@DataProvider
	public Object[][] dp() {
		return new Object[][] { new Object[] { "sessionId = 1111; command = add_book", "added book" },
				new Object[] { "sessionId = 1111; command = bla_bla_bla", "wrong request" },

		};
	}

//	@BeforeMethod

	@Test(dataProvider = "dp")
	public void testExecuteTask(String request, String responseExpected) {
		Map<String, UserStatus> artificialSessions = new HashMap<String, UserStatus>();
		artificialSessions.put("1111", UserStatus.USER);
		FrontController controller = new FrontController();
		controller.setOpenedSessions(artificialSessions);
		String responseActual = controller.executeTask(request);
	
		assertEquals(responseActual, responseExpected);
	}
}
