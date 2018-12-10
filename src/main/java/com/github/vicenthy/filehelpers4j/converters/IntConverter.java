package com.github.vicenthy.filehelpers4j.converters;

public class IntConverter extends ConverterBase{

	@Override
	public Object stringToField(String from) {
		if (from != null) {
			from = from.trim().replaceAll("\\D", "");
		}else {
			return from;
		}
		return Integer.valueOf(from);
	}
	
	@Override
	public String fieldToString(Object from) {
		return from.toString();
	}
}
