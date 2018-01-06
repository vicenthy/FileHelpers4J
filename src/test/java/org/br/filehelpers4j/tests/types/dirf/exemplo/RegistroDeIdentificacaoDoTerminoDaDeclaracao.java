package org.br.filehelpers4j.tests.types.dirf.exemplo;

import org.br.filehelpers4j.annotations.DelimitedRecord;
import org.br.filehelpers4j.annotations.FieldNullValue;
import org.br.filehelpers4j.annotations.Seletor;
import org.br.filehelpers4j.masterdetail.RecordAction;
import org.br.filehelpers4j.masterdetail.SelectorString;

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
	
	
	
	
	
}
