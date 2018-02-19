/*
 * SampleCustomer.java
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
package com.github.vicenthy.filehelpers4j.tests.converters.testobjects;

import com.github.vicenthy.filehelpers4j.annotations.DelimitedRecord;
import com.github.vicenthy.filehelpers4j.annotations.IgnoreCommentedLines;
import com.github.vicenthy.filehelpers4j.annotations.IgnoreFirst;
import com.github.vicenthy.filehelpers4j.annotations.IgnoreLast;


@DelimitedRecord("|")
@IgnoreFirst
@IgnoreLast
@IgnoreCommentedLines(commentMarker = "//")
public class SampleCustomer {
	public String name;
	public String age;
}
