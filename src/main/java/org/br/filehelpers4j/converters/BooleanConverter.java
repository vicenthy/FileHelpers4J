package org.br.filehelpers4j.converters;

public class BooleanConverter extends ConverterBase{
	private String trueString = null;
	private String falseString = null;
	private String trueStringLower = null;
	private String falseStringLower = null;

	public BooleanConverter() {
		
	}
	
	public BooleanConverter(String trueString, String falseString) {
		this.trueString = trueString;
		this.falseString = falseString;
		this.trueStringLower = trueString.toLowerCase();
		this.falseStringLower = falseString.toLowerCase();
	}
	
	@Override
	public Object stringToField(String from) {
		Object val;
		try {
			String testTo = from.toLowerCase();
			if (trueString == null) {
				testTo = testTo.trim();
				if (testTo.equals("true") || testTo.equals("1"))
					val = true;
				else if (testTo.equals("false") || testTo.equals("0") || testTo.equals(""))
					val = false;
				else
					throw new Exception();
			}
			else {
				if (testTo.equals(trueStringLower) || testTo.trim().equals(trueStringLower)) 
					val = true;
				else if (testTo.equals(falseStringLower) || testTo.trim().equals(falseStringLower))
					val = false;
				else
					// throw new ConvertException(from, typeof(bool), "The string: " + from + " cant be recognized as boolean using the true/false values: " + mTrueString + "/" + mFalseString);
					throw new RuntimeException(
							"The string: " + from + " cant be recognized as boolean " +
							"using the true/false values: " + trueString + "/" + falseString);
			}
		} catch (Exception e) {
			// throw new ConvertException(from, typeof (Boolean));
			throw new RuntimeException("Error converting: " + from + " to boolean");
		}
		
		return val;
	}
	
	@Override
	public String fieldToString(Object from) {
		boolean b = Boolean.parseBoolean(from.toString());
		if (b)
			if (trueString == null)
				return "True";
			else
				return trueString;
		else 
			if (falseString == null)
				return "False";
			else
				return falseString;

	}
}
