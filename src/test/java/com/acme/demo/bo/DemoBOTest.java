package com.acme.demo.bo;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.acme.demo.dto.LogBody;

public class DemoBOTest {

	@Mock
	DemoBO demoBO;
	Map<String, String> dbParams;
	DemoBO initicialDemo;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		dbParams = new HashMap<String, String>();
		dbParams.put("logFileFolder", "src/main/resources");
		ReflectionTestUtils.setField(demoBO, "logToFile", true);
		ReflectionTestUtils.setField(demoBO, "logToConsole", true);
		ReflectionTestUtils.setField(demoBO, "logToDatabase", true);
		ReflectionTestUtils.setField(demoBO, "logMessage", true);
		ReflectionTestUtils.setField(demoBO, "logWarning", true);
		ReflectionTestUtils.setField(demoBO, "logError", true);
		ReflectionTestUtils.setField(demoBO, "dbParams", dbParams);
	}

	@Test
	public void whenLogMessageThenReturnSuccess() {

		LogBody logBody = new LogBody("prueba", true, false, false);
		Mockito.doNothing().when(demoBO).LogMessage(logBody.getMessageText(), logBody.isMessage(), logBody.isWarning(),
				logBody.isError());
		demoBO.LogMessage(logBody.getMessageText(), logBody.isMessage(), logBody.isWarning(), logBody.isError());
	}

	@Test
	public void whenLogMessageThenBreak() {

		LogBody logBody = new LogBody("", true, false, false);
		Mockito.doNothing().when(demoBO).LogMessage(logBody.getMessageText(), logBody.isMessage(), logBody.isWarning(),
				logBody.isError());
		demoBO.LogMessage(logBody.getMessageText(), logBody.isMessage(), logBody.isWarning(), logBody.isError());

	}

	@Test
	public void whenLogMessageThenReturnIvalidConfiguration() {
		initicialDemo = new DemoBO(false, false, false, false, false, false, dbParams);
		LogBody logBody = new LogBody("prueba", true, false, false);
		Mockito.doNothing().when(demoBO).LogMessage(logBody.getMessageText(), logBody.isMessage(), logBody.isWarning(),
				logBody.isError());
		demoBO.LogMessage(logBody.getMessageText(), logBody.isMessage(), logBody.isWarning(), logBody.isError());
	}
	
	
	@Test
	public void whenLogMessageThenReturnIvalidMultipleLogs() {
		LogBody logDemo = new LogBody("prueba", true, true, true);
		Mockito.doNothing().when(demoBO).LogMessage(logDemo.getMessageText(), logDemo.isMessage(), logDemo.isWarning(),
				logDemo.isError());
		demoBO.LogMessage(logDemo.getMessageText(), logDemo.isMessage(), logDemo.isWarning(), logDemo.isError());
	}
	
	@Test
	public void whenLogMessageIsLogMessageThenSave() {
		LogBody logPrueba = new LogBody("prueba", true, false, false);
		Mockito.doNothing().when(demoBO).LogMessage(logPrueba.getMessageText(), logPrueba.isMessage(), logPrueba.isWarning(),
				logPrueba.isError());
		demoBO.LogMessage(logPrueba.getMessageText(), logPrueba.isMessage(), logPrueba.isWarning(), logPrueba.isError());
	}
	
	
	@Test
	public void whenLogMessageIsLogErrorThenSave() {
		LogBody logPrueba = new LogBody("prueba", false, false, true);
		Mockito.doNothing().when(demoBO).LogMessage(logPrueba.getMessageText(), logPrueba.isMessage(), logPrueba.isWarning(),
				logPrueba.isError());
		demoBO.LogMessage(logPrueba.getMessageText(), logPrueba.isMessage(), logPrueba.isWarning(), logPrueba.isError());
	}
	
	@Test
	public void whenLogMessageIsLogWarningThenSave() {
		LogBody logPrueba = new LogBody("prueba", false, true, false);
		Mockito.doNothing().when(demoBO).LogMessage(logPrueba.getMessageText(), logPrueba.isMessage(), logPrueba.isWarning(),
				logPrueba.isError());
		demoBO.LogMessage(logPrueba.getMessageText(), logPrueba.isMessage(), logPrueba.isWarning(), logPrueba.isError());
	}
	
	@Test
	public void whenLogMessageIsInvalidThenLogNotDefined() {
		LogBody logPrueba = new LogBody("prueba", true, true, false);
		Mockito.doNothing().when(demoBO).LogMessage(logPrueba.getMessageText(), logPrueba.isMessage(), logPrueba.isWarning(),
				logPrueba.isError());
		demoBO.LogMessage(logPrueba.getMessageText(), logPrueba.isMessage(), logPrueba.isWarning(), logPrueba.isError());
	}
	
}
