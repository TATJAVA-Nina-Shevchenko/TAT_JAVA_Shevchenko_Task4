package com.epam.shevchenko.controller.command;

import org.apache.log4j.Logger;

public abstract class BaseCommand implements Command {
	protected Logger log = Logger.getLogger(getClass());

}
