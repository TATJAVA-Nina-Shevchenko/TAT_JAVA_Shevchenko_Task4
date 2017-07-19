package com.epam.shevchenko.server;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;


public class Server implements Runnable {

	private final static Logger log = Logger.getLogger(Server.class);
	private static Server instance;
	private BlockingQueue<String> requests;
	private ConcurrentMap<String, Future<String>> responses;

	private static final int timeOut = 120_000; // in ms
	private static final int timeOutStep = 100; // in ms

	private boolean isStopped = false;

	private Server() {
		this.requests = new LinkedBlockingQueue<String>();
		this.responses = new ConcurrentHashMap<String, Future<String>>();
	}

	public static Server getInstance() {
		if (instance == null) {
			instance = new Server();
		}
		return instance;
	}

	public String getResponse(String req) {
		if (isStopped()) {
			log.error("Error by timeout");
			return null;
		}
				
		// send request to the server
		try {
			
			//to prevent deletion the same requests
			req += "request_id = " +  Math.random()*1000 + ";";
			
			requests.put(req);
		} catch (InterruptedException e) {
			log.error("error during putting request into server task queue: " + e);
		}

		// try to get response
		String response = null;

		try {

			// wait for getting response for specified timeout
			for (int i = 0; i < timeOut / timeOutStep; i++) {

				if (responses.get(req) == null || !responses.get(req).isDone()) {
					Thread.sleep(timeOutStep);
				} else {
					response = responses.remove(req).get();
//					response = responses.get(req).get();
					return response;
				}
			}

			responses.remove(req);
			log.error("Error by timeout");

		} catch (InterruptedException | ExecutionException e) {
			log.error("error during getting response from server: " + e);
		}
		return null; // only error by timeout or other error can leads to this
						// return
	}

	public void run() {
		ExecutorService ex = Executors.newCachedThreadPool();
		while (!isStopped()) {
			String nextReq = requests.poll();
			if (nextReq != null) {
				Future<String> response = ex.submit(new Executor(nextReq));
				responses.put(nextReq, response);
			}
		}
	}

	private boolean isStopped() {
		return this.isStopped;
	}

	public synchronized void stop() {
		this.isStopped = true;
	}

}
