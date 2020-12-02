package com.acme.demo.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.acme.demo.bo.DemoBO;
import com.acme.demo.dto.LogBody;

@RestController
@RequestMapping("log")
public class DemoController {
	private final static Logger LOGGER = Logger.getLogger("com.acme.demo.DemoController");

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody LogBody request) {
		
		LOGGER.log(Level.INFO,"ingreso metodo endpoint save");
		DemoBO.LogMessage(request.getMessageText(), request.isMessage(), request.isWarning(), request.isError());
		return ResponseEntity.noContent().build();

	}


}
