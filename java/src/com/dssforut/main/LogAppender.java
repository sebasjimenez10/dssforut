package com.dssforut.main;

import org.apache.log4j.Logger;

public class LogAppender {

	public static Logger log = Logger.getLogger(LogAppender.class);
	
	
	public static void logInfoMessage(String message){
		log.info(message);
	}
	
	public static void logDebugMessage(String message){
		log.debug(message);
	}
	
	public static void logWarnMessage(String message){
		log.warn(message);
	}
	
	public static void logErrorMessage(String message){
		log.error(message);
	}
	
	public static void logFatalMessage(String message){
		log.fatal(message);
	}
}
