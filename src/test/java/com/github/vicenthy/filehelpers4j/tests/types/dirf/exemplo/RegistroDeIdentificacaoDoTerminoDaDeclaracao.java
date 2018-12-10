package com.github.vicenthy.filehelpers4j.tests.types.dirf.exemplo;

import com.github.vicenthy.filehelpers4j.annotations.DelimitedRecord;
import com.github.vicenthy.filehelpers4j.annotations.FieldNullValue;
import com.github.vicenthy.filehelpers4j.annotations.Seletor;
import com.github.vicenthy.filehelpers4j.masterdetail.RecordAction;
import com.github.vicenthy.filehelpers4j.masterdetail.SelectorString;

@DelimitedRecord("|")
@Seletor(token="FIMDirf", type=RecordAction.TraillerFile, seletorString=SelectorString.StarWith)
public class RegistroDeIdentificacaoDoTerminoDaDeclaracao {

	@FieldNullValue("FIMDirf")
	private String identificadorDoRegistro;
	
	
	
	
	public RegistroDeIdentificacaoDoTerminoDaDeclaracao() {
		// TODO Auto-generated constructor stub
	}




	public String getIdentificadorDoRegistro() {
		return identificadorDoRegistro;
	}




	public void setIdentificadorDoRegistro(String identificadorDoRegistro) {
		this.identificadorDoRegistro = identificadorDoRegistro;
	}




	@Override
	public String toString() {
		return "RegistroDeIdentificacaoDoTerminoDaDeclaracao [identificadorDoRegistro=" + identificadorDoRegistro + "]";
	}
	
	
	
	
	
}
