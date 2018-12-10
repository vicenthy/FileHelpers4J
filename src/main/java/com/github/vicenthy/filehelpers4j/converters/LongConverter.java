package com.github.vicenthy.filehelpers4j.converters;

public class LongConverter extends ConverterBase{

	
	@Override
	public Object stringToField(String from) {
		if (from != null) {
			from = from.trim().replaceAll("\\D", "");
		}else {
			return from;
		}
		return Long.valueOf(from);
	}
	
	@Override
	public String fieldToString(Object from) {
		return from.toString();
	}

}
