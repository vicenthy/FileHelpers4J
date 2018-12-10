/*
 * IntrumentosFonograma.java
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
package com.github.vicenthy.filehelpers4j.tests.types.multirecord;

import java.io.Serializable;

import com.github.vicenthy.filehelpers4j.annotations.FieldFixedLength;
import com.github.vicenthy.filehelpers4j.annotations.FieldIgnored;
import com.github.vicenthy.filehelpers4j.annotations.FixedLengthRecord;
import com.github.vicenthy.filehelpers4j.annotations.Seletor;
import com.github.vicenthy.filehelpers4j.enums.FixedMode;
import com.github.vicenthy.filehelpers4j.masterdetail.RecordAction;

@FixedLengthRecord(fixedMode=FixedMode.AllowLessChars)
@Seletor(token="FON3", type=RecordAction.Detail)
public class InstrumentosFonograma implements Serializable{
	
	/**
	 * 
	 */
	@FieldIgnored
	private static final long serialVersionUID = 3301737716569829267L;
	@FieldFixedLength(8)
	private String campocontrole;
	@FieldFixedLength(9)
	private String codecadfonograma;
	@FieldFixedLength(15)
	private String codSISRCfonograma;
	@FieldFixedLength(13)
	private String codecadtitular;
	@FieldFixedLength(15)
	private String codSISRCtitular;
	@FieldFixedLength(4)
	private String codinstumento;
	@FieldFixedLength(45)
	private String nomeinstrumento;
	
	
	
	public InstrumentosFonograma() {
	

	} 
	
	
	
	public String getCampocontrole() {
		return campocontrole;
	}
	public void setCampocontrole(String campocontrole) {
		this.campocontrole = campocontrole;
	}
	public String getCodecadfonograma() {
		return codecadfonograma;
	}
	public void setCodecadfonograma(String codecadfonograma) {
		this.codecadfonograma = codecadfonograma;
	}
	public String getCodSISRCfonograma() {
		return codSISRCfonograma;
	}
	public void setCodSISRCfonograma(String codSISRCfonograma) {
		this.codSISRCfonograma = codSISRCfonograma;
	}
	public String getCodecadtitular() {
		return codecadtitular;
	}
	public void setCodecadtitular(String codecadtitular) {
		this.codecadtitular = codecadtitular;
	}
	public String getCodSISRCtitular() {
		return codSISRCtitular;
	}
	public void setCodSISRCtitular(String codSISRCtitular) {
		this.codSISRCtitular = codSISRCtitular;
	}
	public String getCodinstumento() {
		return codinstumento;
	}
	public void setCodinstumento(String codinstumento) {
		this.codinstumento = codinstumento;
	}
	public String getNomeinstrumento() {
		return nomeinstrumento;
	}
	public void setNomeinstrumento(String nomeinstrumento) {
		this.nomeinstrumento = nomeinstrumento;
	}



	@Override
	public String toString() {
		return "InstrumentosFonograma [campocontrole=" + campocontrole + ", codecadfonograma=" + codecadfonograma
				+ ", codSISRCfonograma=" + codSISRCfonograma + ", codecadtitular=" + codecadtitular
				+ ", codSISRCtitular=" + codSISRCtitular + ", codinstumento=" + codinstumento + ", nomeinstrumento="
				+ nomeinstrumento + "]";
	}
	
	
	
	
	
	
	
	
}
