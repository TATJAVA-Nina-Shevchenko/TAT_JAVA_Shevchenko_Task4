package com.epam.shevchenko.server;

import java.util.concurrent.Callable;

import com.epam.shevchenko.controller.FrontController;

public class Executor implements Callable<String> {
	private String request;
	
	public Executor(String request){
		this.request = request;
	}

	@Override
	public String call() throws Exception {
		
		//TODO delete this string
		//To see difference in threads speed and sequence
		System.out.println("Sleep start");
		Thread.sleep((int)(Math.random()*5000 + 1000));
		System.out.println("Sleep finish");
		//*********
		
		FrontController fController = FrontController.getInstance();
		
		String response = fController.executeTask(request);
		return response;
	}

}
