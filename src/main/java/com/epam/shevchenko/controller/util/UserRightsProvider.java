package com.epam.shevchenko.controller.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.epam.shevchenko.controller.command.CommandName;
import com.epam.shevchenko.enums.UserStatus;

public class UserRightsProvider {

	public static Map<UserStatus, Set<CommandName>> getRights(){
		Map<UserStatus, Set<CommandName>> userRights = new HashMap<UserStatus, Set<CommandName>>();
			
		//common rights
		Set<CommandName> commonCommandList = new HashSet<CommandName>();
		commonCommandList.add(CommandName.SHOW_ALL_BOOKS);
		commonCommandList.add(CommandName.WRONG_REQUEST);
		commonCommandList.add(CommandName.LOGIN);
		commonCommandList.add(CommandName.REGISTRATION);
		
//TODO add functionality
		
		//user rights
		Set<CommandName> userCommandList = new HashSet<CommandName>();
		userCommandList.addAll(commonCommandList);
		userCommandList.add(CommandName.ADD_BOOK);
//TODO add functionality
		
		//admin rights
		Set<CommandName> adminCommandList = new HashSet<CommandName>();
		adminCommandList.addAll(commonCommandList);
//TODO add functionality		
		
		//super admin rights
		Set<CommandName> superAdminCommandList = new HashSet<CommandName>();
		superAdminCommandList.addAll(commonCommandList);
		superAdminCommandList.addAll(adminCommandList);
//TODO add functionality	
		
		
		userRights.put(UserStatus.COMMON, commonCommandList);
		userRights.put(UserStatus.USER, userCommandList);
		userRights.put(UserStatus.ADMIN, adminCommandList);
		userRights.put(UserStatus.SUPER_ADMIN, superAdminCommandList);
				
		return userRights;
	}

}
