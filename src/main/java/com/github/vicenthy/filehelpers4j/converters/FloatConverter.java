package com.github.vicenthy.filehelpers4j.converters;

import java.math.BigDecimal;

public class FloatConverter extends ConverterBase{

	@Override
	public Object stringToField(String from) {
		if (from != null) {
			from = from.trim();
		}
		return Float.parseFloat(from);
	}
	
	@Override
	public String fieldToString(Object from) {
		return from.toString();
	}
}
