package com.epam.shevchenko.dao.impl;

import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import com.epam.shevchenko.bean.User;
import com.epam.shevchenko.dao.exception.DAOException;

public class SQLUserDAOTest {

  @Test
  public void TestGetUser() throws DAOException {
	  User user = new SQLUserDAO().getUser("Nina", "11111");
	  assertNotNull(user);
  }
}
