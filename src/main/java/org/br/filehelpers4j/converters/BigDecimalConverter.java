package org.br.filehelpers4j.converters;

import java.math.BigDecimal;

public class BigDecimalConverter extends ConverterBase{

	@Override
	public Object stringToField(String from) {
		if (from != null) {
			from = from.trim();
		}
		return new BigDecimal(from);
	}
	
	@Override
	public String fieldToString(Object from) {
		return from.toString();
	}
}
