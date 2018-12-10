package com.github.vicenthy.filehelpers4j.converters;

import java.math.BigInteger;

public class BigIntegerConverter extends ConverterBase{

	@Override
	public Object stringToField(String from) {
		if (from != null) {
			from = from.trim().replaceAll("\\D", "");
		}else {
			return from;
		}
		return new BigInteger(from);
	}
	
	@Override
	public String fieldToString(Object from) {
		return from.toString();
	}

}
