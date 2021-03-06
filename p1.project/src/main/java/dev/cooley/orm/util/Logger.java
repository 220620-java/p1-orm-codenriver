package dev.cooley.orm.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

//Singleton
public class Logger {
	private static Logger logger;
	
	private Logger() {
		
	}

	public static synchronized Logger getLogger() {
		if (logger == null) {
			logger = new Logger();
		} 
		return logger;
	}
	 
	public void log(String message) {
		message = LocalDateTime.now().toString() + " --- " + message;
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/log.log", true))) {
			writer.write(message);
			writer.newLine();
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
