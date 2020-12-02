package com.acme.demo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import com.acme.demo.bo.DemoBO;
import com.acme.demo.dto.LogBody;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ DemoBO.class })
public class DemoControllerTest {

	@InjectMocks
	DemoController demoController;		
	@Before
	public void initMocks() throws Exception{
		MockitoAnnotations.initMocks(this);
	}

	@Test	
	public void whenSaveThenReturnSuccess() throws Exception {
		LogBody logBody = new LogBody("prueba", true, false, false);		
		PowerMockito.mockStatic(DemoBO.class);
		PowerMockito.doNothing().when(DemoBO.class, "LogMessage", Mockito.anyString(), Mockito.anyBoolean(),
		Mockito.anyBoolean(), Mockito.anyBoolean());
		demoController.save(logBody);
		PowerMockito.verifyStatic(Mockito.times(1));
		
	}

}
