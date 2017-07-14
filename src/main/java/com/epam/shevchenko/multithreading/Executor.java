package com.epam.shevchenko.multithreading;

import java.util.concurrent.Callable;

import com.epam.shevchenko.controller.FrontController;

public class Executor implements Callable<String> {
	private String request;
	
	public Executor(String request){
		this.request = request;
	}

	@Override
	public String call() throws Exception {
		
		FrontController fController = FrontController.getInstance();
		
		String response = fController.executeTask(request);
		return response;
	}

}
