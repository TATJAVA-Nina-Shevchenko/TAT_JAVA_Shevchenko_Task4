package com.epam.shevchenko.bean;

import java.io.Serializable;


import com.epam.shevchenko.enums.UserRole;

public class User  implements  Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String login;
	private char[] password;
	private String contactData;
	private UserRole role;
	

}
