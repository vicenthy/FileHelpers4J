package com.github.vicenthy.filehelpers4j.tests.converters.testobjects;

import com.github.vicenthy.filehelpers4j.annotations.DelimitedRecord;

@DelimitedRecord("|")
public class DecimalType {
	public int intField;
	public float floatField;
	public double doubleField;
	// added just for C# compatibility
	public float decimalField;
}
