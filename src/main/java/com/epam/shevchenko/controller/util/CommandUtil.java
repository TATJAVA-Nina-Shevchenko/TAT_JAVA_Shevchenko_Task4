package com.epam.shevchenko.controller.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.text.WordUtils;
import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.epam.shevchenko.controller.command.Command;
import com.epam.shevchenko.controller.command.CommandName;
import com.epam.shevchenko.controller.xmlparser.CommandSaxHandler;

public class CommandUtil {
	private final static Logger log = Logger.getLogger(CommandUtil.class);
		
	private final static String COMMAND_PACKAGE_PREFFICS = "com.epam.shevchenko.controller.command.impl.";

	// java reflection
	public static Map<CommandName, Command> getCommandMapFromStrings(List<String> commandList) {

		Map<CommandName, Command> commandMap = new HashMap<CommandName, Command>();

		for (String commandString : commandList) {
			Command command;
			CommandName commandName;

			try {
				command = getCommandByName(WordUtils.capitalizeFully(commandString).replace(" ", ""));
				commandName = CommandName.valueOf(commandString.replace(" ", "_").toUpperCase());
				commandMap.put(commandName, command);
				
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				log.error("There is no such command implementation :" + commandString, e);
			}
		}

		return commandMap;
	}

	private static Command getCommandByName(String commandString)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		commandString = COMMAND_PACKAGE_PREFFICS + commandString;
		Class c = Class.forName(commandString);
		Object obj = c.newInstance();
		Command command = (Command) obj;
		return command;
	}

	public static Map<CommandName, Command> readCommandsFromXml(String url) throws SAXException, IOException {

		XMLReader reader = XMLReaderFactory.createXMLReader();
		CommandSaxHandler handler = new CommandSaxHandler();
		reader.setContentHandler(handler);
		reader.parse(new InputSource(url));

		List<String> commandList = handler.getCommandList();
		return getCommandMapFromStrings(commandList);
	}

}
