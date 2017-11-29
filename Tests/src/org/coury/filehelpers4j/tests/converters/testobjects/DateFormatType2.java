package org.coury.filehelpers4j.tests.converters.testobjects;

import java.util.Date;

import org.coury.filehelpers4j.annotations.DelimitedRecord;
import org.coury.filehelpers4j.annotations.FieldConverter;
import org.coury.filehelpers4j.enums.ConverterKind;

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
