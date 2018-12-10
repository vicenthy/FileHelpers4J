/*
 * ConverterBase.java
 *
 * Copyright (C) 2007 Felipe Gon�alves Coury <felipe.coury@gmail.com>
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package com.github.vicenthy.filehelpers4j.converters;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import com.github.vicenthy.filehelpers4j.enums.ConverterKind;

public class ConvertHelpers {

	public static ConverterBase getConverter(ConverterKind converterKind, String format) {
		switch (converterKind) {
		case Date:
			return new DateTimeConverter(format);
		case Boolean:
			return new BooleanConverter();
		case Integer:
			return new IntConverter();
		case BigDecimal:
			return new BigDecimalConverter();
		case Double:
			return new DoubleConverter();
		case Float:
			return new FloatConverter();
		case Long:
			return new LongConverter();
		case BigInteger:
			return new BigIntegerConverter();
		default:
			return null;
		}
	}
	
	public static ConverterBase getDefaultConverter(Field field) {
		if (field.getType() == String.class) {
			return new AnyConverter();
		}else if (field.getType() == Date.class) {
			return new DateTimeConverter();
		}else if (field.getType() == Boolean.class) {
			return new BooleanConverter();
		}else if (field.getType() == Integer.class) {
			return new IntConverter();
		}else if (field.getType() == BigDecimal.class) {
			return new BigDecimalConverter();
		}else if (field.getType() == Double.class) {
			return new DoubleConverter();
		}else if (field.getType() == Float.class) {
			return new FloatConverter();
		}else if (field.getType() == Long.class) {
			return new LongConverter();
		}else if (field.getType() == BigInteger.class) {
			return new BigIntegerConverter();
		}
		return null;
	}




	
	


}
