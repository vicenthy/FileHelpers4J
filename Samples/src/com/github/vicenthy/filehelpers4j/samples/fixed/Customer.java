/*
 * Customer.java
 *
 * Copyright (C) 2007 Felipe Gon�alves Coury <felipe.coury@gmail.com>
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

package com.github.vicenthy.filehelpers4j.samples.fixed;

import java.util.Date;

import com.github.vicenthy.filehelpers4j.annotations.FieldAlign;
import com.github.vicenthy.filehelpers4j.annotations.FieldConverter;
import com.github.vicenthy.filehelpers4j.annotations.FieldFixedLength;
import com.github.vicenthy.filehelpers4j.annotations.FieldOptional;
import com.github.vicenthy.filehelpers4j.annotations.FieldTrim;
import com.github.vicenthy.filehelpers4j.annotations.FixedLengthRecord;
import com.github.vicenthy.filehelpers4j.enums.AlignMode;
import com.github.vicenthy.filehelpers4j.enums.ConverterKind;
import com.github.vicenthy.filehelpers4j.enums.TrimMode;
import com.github.vicenthy.filehelpers4j.helpers.StringHelper;

@FixedLengthRecord()
public class Customer {
	@FieldFixedLength(4)
	private Integer custId;

	@FieldAlign(alignMode=AlignMode.Right)
	@FieldFixedLength(20)
	private String name;

	@FieldFixedLength(3)
	private Integer rating;

	@FieldTrim(trimMode=TrimMode.Right)
	@FieldFixedLength(10)
	@FieldConverter(converter = ConverterKind.Date, format = "dd-MM-yyyy")
	private Date addedDate;
	
	@FieldFixedLength(3)
	@FieldOptional
	private String stockSimbol;	
	
	@Override
	public String toString() {
		String l = System.getProperty("line.separator");
		StringBuffer b = new StringBuffer();
		b.append("Customer: ").append(l);
		b.append("   custId = " + custId).append(l);
		b.append("   name = " + name).append(l);
		b.append("   rating = " + rating).append(l);
		b.append("   addedDate = " + addedDate).append(l);
		b.append("   stockSimbol = " + stockSimbol).append(l);
		return StringHelper.toStringBuilder(this, b.toString());
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public String getStockSimbol() {
		return stockSimbol;
	}

	public void setStockSimbol(String stockSimbol) {
		this.stockSimbol = stockSimbol;
	}
}
