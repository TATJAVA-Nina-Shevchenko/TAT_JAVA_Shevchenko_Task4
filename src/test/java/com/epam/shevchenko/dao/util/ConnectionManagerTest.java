package com.epam.shevchenko.dao.util;

import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import com.epam.shevchenko.dao.exception.DAOException;

public class ConnectionManagerTest {

//  @Test
//  public void ConnectionManager() {
//    throw new RuntimeException("Test not implemented");
//  }

  @Test //(expectedExceptions = DAOException.class)
  public void testCreateConnection() throws DAOException {
   	  assertNotNull( ConnectionManager.getInstance().getConnection());
  }

//  @Test
//  public void initDBConnection() {
//    throw new RuntimeException("Test not implemented");
//  }

//  @Test
//  public void isPropertiesInit() {
//	 throw new RuntimeException("Test not implemented");
//  }
}
