package com.acme.demo.bo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.apache.commons.lang3.StringUtils;
import com.acme.demo.dao.ConnectionDAO;


public class DemoBO {
	private final static Logger LOGGER = Logger.getLogger("com");
	private static Map dbParams;

	private static boolean logToFile;
	private static boolean logToConsole;
	private static boolean logToDatabase;

	private static boolean logMessage;
	private static boolean logWarning;
	private static boolean logError;

	public DemoBO(boolean logToFileParam, boolean logToConsoleParam, boolean logToDatabaseParam, boolean logMessageParam,
			boolean logWarningParam, boolean logErrorParam, Map dbParamsMap) {
		logError = logErrorParam;
		logMessage = logMessageParam;
		logWarning = logWarningParam;
		logToDatabase = logToDatabaseParam;
		logToFile = logToFileParam;
		logToConsole = logToConsoleParam;
		dbParams = dbParamsMap;
	}

	public static void LogMessage(String messageText, boolean message, boolean warning, boolean error) {

		try {		    
			
	        File logFile = new File(dbParams.get("logFileFolder") + "/logFile.txt");
	        if (!logFile.getParentFile().exists() &&!logFile.exists()) {
	        	logFile.getParentFile().mkdirs();
	        	logFile.createNewFile();        
	            }			
			
			Handler consoleHandler = new ConsoleHandler();
			FileHandler fileHandler = new FileHandler(dbParams.get("logFileFolder").toString() + "/logFile.txt", false);
			SimpleFormatter simpleFormatter = new SimpleFormatter();
			fileHandler.setFormatter(simpleFormatter);
			consoleHandler.setLevel(Level.ALL);
			fileHandler.setLevel(Level.ALL);
			messageText.trim();

			if (logToConsole) {
				LOGGER.addHandler(consoleHandler);
			}
			if (logToFile) {
				LOGGER.addHandler(fileHandler);
			}

			if (StringUtils.isBlank(messageText)) {
				LOGGER.log(Level.WARNING, "el mensaje no deberia ser nullo o vacio");
				return;
			}
			if (!logToConsole && !logToFile && !logToDatabase) {
				throw new Exception("Invalid configuration");
			}
			if (!logError && !logMessage && !logWarning) {
				throw new Exception("no se inicializo todos logs con true");
			}

			if (message && warning && error) {
				throw new Exception("error log multiples para trazar");
			}

			int t = 0;
			String l = "";
			
			if (message==logMessage&& logMessage!=error && logMessage!=warning) {				
				l = l + "message " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;
				t = 1;
				
			}
			else if (error==logError&&logError!=message&&logError!=warning) {
				l = l + "error " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;
				t = 2;
			}


			else if (warning==logWarning&& logWarning!=message&&logWarning!=error) {
				l = l + "warning " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + messageText;
				t = 3;
			}	else {			
				throw new Exception("error se necesita un log para trazar");
				
			}
			
			
			LOGGER.log(Level.INFO,l);

			if (logToDatabase) {
				LOGGER.log(Level.INFO, "se envio log a registrar");
				ConnectionDAO connectionDAO= new ConnectionDAO();
				connectionDAO.saveLog(String.valueOf(t), l);

			}

		} catch (IOException ef) {

			LOGGER.log(Level.SEVERE, "ocurrio un error en el manejador FileHandler");
			LOGGER.log(Level.SEVERE, DemoBO.getStackTrace(ef));			
		}
		catch (SQLException ex) {
			LOGGER.log(Level.SEVERE, "ocurrio un error al guardar log");
			LOGGER.log(Level.SEVERE, DemoBO.getStackTrace(ex));
			
		} 
		catch (Exception e) {
			LOGGER.log(Level.SEVERE, DemoBO.getStackTrace(e));
		}
	}

	public static String getStackTrace(Exception e) {
		StringWriter sWriter = new StringWriter();
		PrintWriter pWriter = new PrintWriter(sWriter);
		e.printStackTrace(pWriter);
		return  sWriter.toString();
	}
}
