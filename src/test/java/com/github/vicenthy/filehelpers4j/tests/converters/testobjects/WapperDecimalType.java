package com.github.vicenthy.filehelpers4j.tests.converters.testobjects;

import java.math.BigDecimal;

import com.github.vicenthy.filehelpers4j.annotations.DelimitedRecord;

@DelimitedRecord("|")
public class WapperDecimalType {

	public Integer intField;
	public Float floatField;
	public BigDecimal bigdecimalField;
	public Double doubleField;
	@Override
	public String toString() {
		return "WapperDecimalType [intField=" + intField + ", floatField=" + floatField + ", bigdecimalField=" + bigdecimalField + ", doubleField=" + doubleField + "]";
	}
	
	
	

	
	
}
