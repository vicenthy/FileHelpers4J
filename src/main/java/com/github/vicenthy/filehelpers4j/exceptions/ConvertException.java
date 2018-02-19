package com.github.vicenthy.filehelpers4j.exceptions;

import java.util.Date;

public class ConvertException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4134256660645308186L;
	private String from;
	private String format;
	private Class<?> clazz;
	
	
	public ConvertException(String from, String format, Class<?> clazz) {
		this.from = from;
		this.format = format;
		this.clazz = clazz;
	}



	@Override
	public String getMessage() {
		String extra = "";
		if(clazz.equals(Date.class)) {
			extra = formatExceptionDateTime();
		}
		return extra;
	}



	private String formatExceptionDateTime() {
		String extra;
		if (from.length() > format.length())
			extra = moreCharsFormat();
		else if (from.length() < format.length())
			extra = lessCharsFormat();
		else
			extra = " Using the format: '" + format + "'";
		return extra;
	}



	private String lessCharsFormat() {
		String extra;
		extra = " There are less chars than in the format string: '" + format + "'";
		return extra;
	}



	private String moreCharsFormat() {
		String extra;
		extra = " There are more chars than in the format string: '" + format + "'";
		return extra;
	}
}
