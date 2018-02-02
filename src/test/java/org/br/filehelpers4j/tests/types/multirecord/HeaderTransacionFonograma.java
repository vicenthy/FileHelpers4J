/*
 * HeaderFonograma.java
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
package org.br.filehelpers4j.tests.types.multirecord;

import org.br.filehelpers4j.annotations.FieldFixedLength;
import org.br.filehelpers4j.annotations.FixedLengthRecord;
import org.br.filehelpers4j.annotations.Seletor;
import org.br.filehelpers4j.masterdetail.RecordAction;

@Seletor(token="0FON", type=RecordAction.HeaderTransaction)
@FixedLengthRecord
public class HeaderTransacionFonograma {

	
	
	@FieldFixedLength(3)
	private String typeRegistre;
	@FieldFixedLength(1)
	private Integer registre;
	@FieldFixedLength(9)
	private String sufix;
	
	
	
	public HeaderTransacionFonograma() {


	}
	
	
	
	
	@Override
	public String toString() {
		return "HeaderTransacionFonograma [typeRegistre=" + typeRegistre + ", registre=" + registre + ", sufix=" + sufix
				+ "]";
	}




	public String getTypeRegistre() {
		return typeRegistre;
	}
	public void setTypeRegistre(String typeRegistre) {
		this.typeRegistre = typeRegistre;
	}
	public Integer getRegistre() {
		return registre;
	}
	public void setRegistre(Integer registre) {
		this.registre = registre;
	}
	public String getSufix() {
		return sufix;
	}
	public void setSufix(String sufix) {
		this.sufix = sufix;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
