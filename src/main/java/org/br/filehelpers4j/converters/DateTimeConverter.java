package org.br.filehelpers4j.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.br.filehelpers4j.exceptions.ConvertException;

public class DateTimeConverter extends ConverterBase{
	String format;

	public DateTimeConverter() {
		this(ConverterBase.getDefaultDateTimeFormat());
	}
	
	public DateTimeConverter(String format) {
		if (format == null || format.length() < 1) {
			throw new IllegalArgumentException("The format of the DateTime Converter can be null or empty.");
		}

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			sdf.format(new Date());
		}
		catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("The format: '" + format + " is invalid for the DateTime Converter.");
		}
		
		this.format = format;
	}

	@Override
	public Object stringToField(String from) {
		if (from == null) {
			from = "";
		}
		
		Object val;
		
		try {
			val = new SimpleDateFormat(format).parse(from);
		} catch (ParseException e) {
			throw new ConvertException(from, format, Date.class);
		}
		
		return val;
	}

	@Override
	public String fieldToString(Object from) {
		return new SimpleDateFormat(format).format(from);
	}
}
