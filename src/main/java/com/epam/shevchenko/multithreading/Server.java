package com.epam.shevchenko.multithreading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

public class Server implements Runnable {
	private Server instance;
	private BlockingQueue<String> requests;
	private ConcurrentMap<String, Future<String>> responses;

	private boolean isStopped = false;

	private Server() {
		this.requests = new LinkedBlockingQueue<String>();
		this.responses = new ConcurrentHashMap<String, Future<String>>();
	}

	public Server getInstance() {
		if (instance == null) {
			instance = new Server();
		}
		return instance;
	}

	public void sendRequest(String req) {
		if (requests != null) {
			try {
				requests.put(req);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getResponse(String req) {
		String response = null;
		if (responses != null) {
			try {
				if (responses.get(req).isDone())
					response = responses.get(req).get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return response;
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
