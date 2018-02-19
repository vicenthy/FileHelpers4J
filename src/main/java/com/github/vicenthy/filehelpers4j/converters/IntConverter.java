package com.github.vicenthy.filehelpers4j.converters;

public class IntConverter extends ConverterBase{
	@Override
	public Object stringToField(String from) {
		if (from != null) {
			from = from.trim();
		}
		return Integer.parseInt(from);
	}
	
	@Override
	public String fieldToString(Object from) {
		return from.toString();
	}
}
