package com.epam.shevchenko.controller.util;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

public class DataEncryptorTest {

  @Test
  public void getPasswordHashCode() {
    String pass = "11111";
    String encryptedPass = DataEncryptor.getPasswordHashCode(pass);
    System.out.println(encryptedPass);
    assertNotNull(encryptedPass);
    assertNotEquals(encryptedPass, pass);
    
  }
}
