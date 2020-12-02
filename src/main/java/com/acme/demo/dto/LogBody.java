package com.acme.demo.dto;

public class LogBody {

	String messageText;

	boolean message;
	
	boolean warning;

	boolean error;


	public LogBody(String messageText, boolean message, boolean warning, boolean error) {
		super();
		this.messageText = messageText;
		this.message = message;
		this.warning = warning;
		this.error = error;
	}
	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	public boolean isMessage() {
		return message;
	}
	public void setMessage(boolean message) {
		this.message = message;
	}
	public boolean isWarning() {
		return warning;
	}
	public void setWarning(boolean warning) {
		this.warning = warning;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}

	
}
