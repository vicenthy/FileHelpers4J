package org.coury.filehelpers4j.tests.converters.testobjects;

import org.coury.filehelpers4j.annotations.DelimitedRecord;

@DelimitedRecord("|")
public class DecimalType {
	public int intField;
	public float floatField;
	public double doubleField;
	// added just for C# compatibility
	public float decimalField;
}
