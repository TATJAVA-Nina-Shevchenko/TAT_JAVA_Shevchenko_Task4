package com.epam.shevchenko.controller.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.epam.shevchenko.constant.UserStatus;
import com.epam.shevchenko.controller.command.CommandName;

public class UserRightsProvider {

	public static Map<UserStatus, Set<CommandName>> getRights() {
		Map<UserStatus, Set<CommandName>> userRights = new HashMap<UserStatus, Set<CommandName>>();

		// common rights
		Set<CommandName> commonCommandList = new HashSet<CommandName>();
		commonCommandList.add(CommandName.SHOW_ALL_BOOKS);
		commonCommandList.add(CommandName.WRONG_REQUEST);
		commonCommandList.add(CommandName.LOGIN);
		commonCommandList.add(CommandName.REGISTRATION);


		// TODO add functionality

		// user rights
		Set<CommandName> userCommandList = new HashSet<CommandName>();
		userCommandList.addAll(commonCommandList);
		userCommandList.add(CommandName.SHOW_USER_PROFILE);
		userCommandList.add(CommandName.UPDATE_PROFILE);

		// TODO add functionality

		// admin rights
		Set<CommandName> adminCommandList = new HashSet<CommandName>();
		adminCommandList.addAll(commonCommandList);
		adminCommandList.add(CommandName.ADD_BOOK);
		adminCommandList.add(CommandName.SET_TO_ADMIN);
		adminCommandList.add(CommandName.SET_TO_BAN);
		adminCommandList.add(CommandName.UPDATE_BOOK);
		adminCommandList.add(CommandName.DELETE_BOOK);
		
		
		// TODO add functionality

		// super admin rights
		Set<CommandName> superAdminCommandList = new HashSet<CommandName>();
		superAdminCommandList.addAll(adminCommandList);
		superAdminCommandList.add(CommandName.SET_TO_USER);

		// TODO add functionality

		userRights.put(UserStatus.COMMON, commonCommandList);
		userRights.put(UserStatus.USER, userCommandList);
		userRights.put(UserStatus.ADMIN, adminCommandList);
		userRights.put(UserStatus.SUPER_ADMIN, superAdminCommandList);

		return userRights;
	}

}
