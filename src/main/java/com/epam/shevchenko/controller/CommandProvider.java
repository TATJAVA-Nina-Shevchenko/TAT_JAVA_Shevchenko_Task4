package com.epam.shevchenko.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.epam.shevchenko.controller.command.Command;
import com.epam.shevchenko.controller.command.CommandName;
import com.epam.shevchenko.controller.command.admin.AddBook;
import com.epam.shevchenko.controller.command.common.NotEnoughRights;
import com.epam.shevchenko.controller.command.common.WrongRequest;
import com.epam.shevchenko.controller.util.UserRightsProvider;
import com.epam.shevchenko.enums.UserStatus;

public class CommandProvider {
	private final Map<CommandName, Command> repository = new HashMap<CommandName, Command>();
	private final Map<UserStatus, Set<CommandName>> userRights = UserRightsProvider.getRights();

	CommandProvider() {
		repository.put(CommandName.ADD_BOOK, new AddBook());

		
		repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
		repository.put(CommandName.NOT_ENOUGH_RIGHTS, new NotEnoughRights());
	}
	
	public Command getCommand(String name) {
		CommandName commandName = convertToCommandName(name);
		Command command = repository.get(commandName);
		return command;
	}
	
	public Command getCommand(String name, UserStatus userStatus) {
		Command command = null;
		if(isRightsEnough(name, userStatus)){
			command = getCommand(name);
		} else {
			command = notEnoughRights();
		}
		return command;
	}
	
	
	private boolean isRightsEnough(String name, UserStatus userStatus) {
		CommandName commandName = convertToCommandName(name);
		
		if (userRights.containsKey(userStatus)) {
			Set<CommandName> commandsAllowed = userRights.get(userStatus);
			if (commandsAllowed.contains(commandName)){
				return true;
			}
			return false;
		}
		return false;
	}
	
	private CommandName convertToCommandName(String name) {
		CommandName commandName = null;
		try {
			commandName = CommandName.valueOf(name.toUpperCase());
		} catch (IllegalArgumentException | NullPointerException e) {
			commandName = CommandName.WRONG_REQUEST;
		}
		return commandName;
	}

	
	private Command notEnoughRights() {
		return repository.get(CommandName.NOT_ENOUGH_RIGHTS);
	}
}
