package com.github.vicenthy.filehelpers4j.exceptions;

public class BadUsageException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1343270232255906686L;
	private String msgError;
	
	public BadUsageException(String msgError) {
	this.msgError = msgError;	

	}
	
	
	@Override
	public String getMessage() {
		return this.msgError;
	}

}
