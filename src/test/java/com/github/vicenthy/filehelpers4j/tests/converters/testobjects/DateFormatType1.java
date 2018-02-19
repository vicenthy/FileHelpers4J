package com.github.vicenthy.filehelpers4j.tests.converters.testobjects;

import java.util.Date;

import com.github.vicenthy.filehelpers4j.annotations.DelimitedRecord;
import com.github.vicenthy.filehelpers4j.annotations.FieldConverter;
import com.github.vicenthy.filehelpers4j.enums.ConverterKind;

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
