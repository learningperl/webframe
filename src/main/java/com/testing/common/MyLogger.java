package com.testing.common;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class MyLogger {
	public static final Logger logger = Logger.getLogger("webframe");
	public static String path;
	public static final int[] size = {0,0,0,32,32,256,256,256,4,1024};
	public static final int[] sizeres = {0,0,0,32,32,256,256,256,4,1024};
	
	public MyLogger(String p) {
		MyLogger.path = p;
		PropertyConfigurator.configure(path + "\\log4j.properties");
	}
	
}
