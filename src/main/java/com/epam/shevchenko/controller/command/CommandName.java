package com.epam.shevchenko.controller.command;

public enum CommandName {

	// common commands
	WRONG_REQUEST,  NOT_ENOUGH_RIGHTS, SHOW_ALL_BOOKS, LOGIN, REGISTRATION,

	// user commands
	LOGOUT,  SHOW_USER_PROFILE,  UPDATE_PROFILE, RESERVE_BOOKS, REFUSE_FROM_ORDER,

	// admin commands
	ADD_BOOK, SET_TO_ADMIN, SET_TO_BAN, UPDATE_BOOK, DELETE_BOOK,

	// super-admin commands
	SET_TO_USER
	;
}
