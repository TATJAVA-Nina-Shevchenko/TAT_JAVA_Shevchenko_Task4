package com.epam.shevchenko.server;

import com.epam.shevchenko.server.Server;

public class ServerRunner {
	private static final String REQUEST_01 = "session_id = user; command = bla_bla_bla;";
	private static final String REQUEST_02 = "command = login; user_login=Nina; user_password=11111;";
	private static final String REQUEST_03 = "command = show_all_books;";
	private static final String REQUEST_04 = "command = show_all_books;";
	private static final String REQUEST_05 = "command = login; user_login=Nina; user_password=11111;";

	
	public static void main(String[] args){
		
		Thread serverStart =new Thread(Server.getInstance());
		serverStart.start();
		
		
		Thread user01 = new Thread(){
			public void run(){
				System.out.println("Thread 01 starts");
				System.out.println("Thread 01: " + Server.getInstance().getResponse(REQUEST_01));
			}
		};
		
		Thread user02 = new Thread(){
			public void run(){
				System.out.println("Thread 02 starts");
				System.out.println("Thread 02: " +Server.getInstance().getResponse(REQUEST_02));
			}
		};
		
		Thread user03 = new Thread(){
			public void run(){
				System.out.println("Thread 03 starts");
				System.out.println("Thread 03: " +Server.getInstance().getResponse(REQUEST_03));
			}
		};
		
		
		Thread user04 = new Thread(){
			public void run(){
				System.out.println("Thread 04 starts");
				System.out.println("Thread 04: " +Server.getInstance().getResponse(REQUEST_04));
			}
		};
		
		
		Thread user05 = new Thread(){
			public void run(){
				System.out.println("Thread 05 starts");
				System.out.println("Thread 05: " +Server.getInstance().getResponse(REQUEST_05));
			}
		};
		
		
		user01.start();
		user02.start();
		user03.start();
		user04.start();
		user05.start();
		
	}

}
