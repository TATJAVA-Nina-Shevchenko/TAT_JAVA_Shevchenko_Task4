package com.epam.shevchenko.controller.command;

public enum CommandName {

	// common commands
	WRONG_REQUEST,  NOT_ENOUGH_RIGHTS, SHOW_ALL_BOOKS, LOGIN, REGISTRATION,

	// user commands
	LOGOUT,  SHOW_USER_PROFILE,

	// admin commands
	ADD_BOOK,

	// super-admin commands

	;
}
