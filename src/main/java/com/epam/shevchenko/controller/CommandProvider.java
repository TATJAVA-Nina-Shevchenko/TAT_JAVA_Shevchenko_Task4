package com.epam.shevchenko.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.epam.shevchenko.constant.UserStatus;
import com.epam.shevchenko.controller.command.Command;
import com.epam.shevchenko.controller.command.CommandName;
import com.epam.shevchenko.controller.command.admin.AddBook;
import com.epam.shevchenko.controller.command.admin.DeleteBook;
import com.epam.shevchenko.controller.command.admin.SetToAdmin;
import com.epam.shevchenko.controller.command.admin.SetToBan;
import com.epam.shevchenko.controller.command.admin.UpdateBook;
import com.epam.shevchenko.controller.command.admin.supe.SetToUser;
import com.epam.shevchenko.controller.command.common.Login;
import com.epam.shevchenko.controller.command.common.NotEnoughRights;
import com.epam.shevchenko.controller.command.common.Registration;
import com.epam.shevchenko.controller.command.common.ShowAllBooks;
import com.epam.shevchenko.controller.command.common.WrongRequest;
import com.epam.shevchenko.controller.command.user.RefuseFromOrder;
import com.epam.shevchenko.controller.command.user.ReserveBooks;
import com.epam.shevchenko.controller.command.user.ShowUserProfile;
import com.epam.shevchenko.controller.command.user.UpdateProfile;
import com.epam.shevchenko.controller.util.UserRightsProvider;

public class CommandProvider {
	private final Map<CommandName, Command> repository = new HashMap<CommandName, Command>();
	private final Map<UserStatus, Set<CommandName>> userRights = UserRightsProvider.getRights();

	
	// registers all commands in command provider
	CommandProvider() {
		repository.put(CommandName.ADD_BOOK, new AddBook());
		repository.put(CommandName.LOGIN, new Login());
		repository.put(CommandName.REGISTRATION, new Registration());
		repository.put(CommandName.SHOW_ALL_BOOKS, new ShowAllBooks());
		repository.put(CommandName.SHOW_USER_PROFILE, new ShowUserProfile());
		repository.put(CommandName.UPDATE_PROFILE, new UpdateProfile());
		repository.put(CommandName.SET_TO_ADMIN, new SetToAdmin());
		repository.put(CommandName.SET_TO_BAN, new SetToBan());
		repository.put(CommandName.SET_TO_USER, new SetToUser());
		repository.put(CommandName.UPDATE_BOOK, new UpdateBook());
		repository.put(CommandName.DELETE_BOOK, new DeleteBook());
		repository.put(CommandName.RESERVE_BOOKS, new ReserveBooks());
		repository.put(CommandName.REFUSE_FROM_ORDER, new RefuseFromOrder());
		
		
		
		repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
		repository.put(CommandName.NOT_ENOUGH_RIGHTS, new NotEnoughRights());
	}
	
	public Command getCommand(String name) {
		CommandName commandName = convertToCommandName(name);
		Command command = repository.get(commandName);
		return command;
	}
	
	//provides appropriate command if user has enough rights or return default command
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
