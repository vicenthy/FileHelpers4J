/*
 * AllTests.java
 *
 * Copyright (C) 2007 Atila Augusto dos Santos - <atila.sistemas@gmail.com>
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


import com.github.vicenthy.filehelpers4j.tests.annotations.IgnoreTest;
import com.github.vicenthy.filehelpers4j.tests.callbacks.EventsTest;
import com.github.vicenthy.filehelpers4j.tests.callbacks.NotifiersTest;
import com.github.vicenthy.filehelpers4j.tests.callbacks.ProgressTest;
import com.github.vicenthy.filehelpers4j.tests.converters.DateFormatTest;
import com.github.vicenthy.filehelpers4j.tests.converters.DecimalNumbersTest;
import com.github.vicenthy.filehelpers4j.tests.converters.EnumConverterTest;
import com.github.vicenthy.filehelpers4j.tests.masterdetail.MasterDetailTest;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for com.github.vicenthy.filehelper4j.tests.converters");
		//$JUnit-BEGIN$
		suite.addTestSuite(IgnoreTest.class);
		suite.addTestSuite(EnumConverterTest.class);
		suite.addTestSuite(DateFormatTest.class);
		suite.addTestSuite(DecimalNumbersTest.class);
		suite.addTestSuite(MasterDetailTest.class);
		suite.addTestSuite(NotifiersTest.class);
		suite.addTestSuite(EventsTest.class);
		suite.addTestSuite(ProgressTest.class);
		//$JUnit-END$
		return suite;
	}

}
