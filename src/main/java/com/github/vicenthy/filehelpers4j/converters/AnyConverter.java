package com.github.vicenthy.filehelpers4j.converters;

public class AnyConverter extends ConverterBase{

	@Override
	public Object stringToField(String from) {
		if(from != null)
			return from.toString();
		else
			return null;
	}
	
	@Override
	public String fieldToString(Object from) {
		if(from != null)
			return from.toString();
		else
			return null;
	}


}
