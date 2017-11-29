package org.br.filehelpers4j.tests.converters.testobjects;

import java.util.Date;

import org.br.filehelpers4j.annotations.DelimitedRecord;
import org.br.filehelpers4j.annotations.FieldConverter;
import org.br.filehelpers4j.enums.ConverterKind;

@DelimitedRecord
public class DateFormatType1 {
	public int orderID;
	public int employeeID;
	
	public DateFormatType1() {
		
	}
	
	@FieldConverter(converter=ConverterKind.Date, format="d-M-yyyy") 
	public Date orderDate;
	@FieldConverter(converter=ConverterKind.Date, format="MMddyyyy")
	public Date requiredDate;
	@FieldConverter(converter=ConverterKind.Date, format="d/M/yy") 
	public Date shippedDate;
}
