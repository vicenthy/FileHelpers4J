package com.github.vicenthy.filehelpers4j.tests.converters.testobjects;

import java.util.Date;

import com.github.vicenthy.filehelpers4j.annotations.DelimitedRecord;
import com.github.vicenthy.filehelpers4j.annotations.FieldConverter;
import com.github.vicenthy.filehelpers4j.enums.ConverterKind;

@DelimitedRecord(",")
public class DateFormatType2 {
	public int orderID;
	public int employeeID;
	@FieldConverter(converter=ConverterKind.Date, format="M-d-yyyy") 
	public Date orderDate;
	@FieldConverter(converter=ConverterKind.Date, format="MMddyyyy")
	public Date requiredDate;
	@FieldConverter(converter=ConverterKind.Date, format="M/d/yy")
	public Date shippedDate;
}
