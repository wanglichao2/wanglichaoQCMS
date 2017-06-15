package com.iss.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.iss.thread.UploadRun;

public class MyListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("========== web start==========");
		new Thread(new UploadRun()).start();
	}

}
