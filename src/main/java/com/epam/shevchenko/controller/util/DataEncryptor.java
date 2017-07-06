package com.epam.shevchenko.controller.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DataEncryptor {

	private static String ALGORITHM = "MD5";
	private static int POSITIVE = 1;

	public static String getPasswordHashCode(String password) {
		
		try {
			MessageDigest dig = MessageDigest.getInstance(ALGORITHM);
			// generating of hashcode
			BigInteger hash = new BigInteger(POSITIVE, dig.digest(password.getBytes()));
			// converting to string of 32 symbols, which represents hex number
			return String.format("%032x", hash);

		} catch (NoSuchAlgorithmException e) {
				throw new RuntimeException("Exception at DataEncryptor: NoSuchAlgorithm");
		}
	}
}
