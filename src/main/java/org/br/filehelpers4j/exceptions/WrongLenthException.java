package org.br.filehelpers4j.exceptions;

import java.lang.reflect.Field;

public class WrongLenthException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1966549998480148706L;
	private String currentString;
	private int currentLength;
	private int lineNumber;
	private String name;
	private int fieldLength;
	private Field field;
	
	public WrongLenthException(Field field, String currentString, int currentLength, int lineNumber, int fieldLength , String name) {
		// TODO Auto-generated constructor stub
		this.currentString = currentString;
		this.currentLength = currentLength;
		this.lineNumber = lineNumber;
		this.fieldLength = fieldLength;
		this.name = name;
		this.field = field;
	}

	@Override
	public String getMessage() {
		return "The string '" + currentString + 
		"' (length " + currentLength + ") at line " + 
		lineNumber + " has less chars than the defined for " + 
		name + " (" + fieldLength + "). " +
		"You can use the @FixedLengthRecord(fixedMode=FixedMode.AllowLessChars) to avoid this problem in Class " +field.getDeclaringClass().getSimpleName() ;
	}
	
	
	
}
