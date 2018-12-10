package com.github.vicenthy.filehelpers4j.tests.converters;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.github.vicenthy.filehelpers4j.engines.FileHelperEngine;
import com.github.vicenthy.filehelpers4j.tests.common.Common;
import com.github.vicenthy.filehelpers4j.tests.converters.testobjects.WapperDecimalType;
import com.github.vicenthy.filehelpers4j.tests.converters.testobjects.DecimalType;

public class WapperDecimalNumbersTest {

	
	
	
	@Test
	public void run() throws IOException {
		FileHelperEngine<WapperDecimalType> engine = new FileHelperEngine<WapperDecimalType>(WapperDecimalType.class);
		List<WapperDecimalType> res = 
			(ArrayList<WapperDecimalType>) Common.readTest(engine, "Good/NumberFormat.txt");
		
		
		res.forEach(a -> {
		assertTrue(a.bigdecimalField.getClass().equals(BigDecimal.class));
		assertTrue(a.doubleField.getClass().equals(Double.class));
		assertTrue(a.intField.getClass().equals(Integer.class));
				
		});

	}
}
