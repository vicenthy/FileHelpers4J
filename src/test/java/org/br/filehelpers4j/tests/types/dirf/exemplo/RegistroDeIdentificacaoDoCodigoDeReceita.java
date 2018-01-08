package org.br.filehelpers4j.tests.types.dirf.exemplo;

import org.br.filehelpers4j.annotations.DelimitedRecord;
import org.br.filehelpers4j.annotations.FieldNullValue;
import org.br.filehelpers4j.annotations.Seletor;
import org.br.filehelpers4j.masterdetail.RecordAction;
import org.br.filehelpers4j.masterdetail.SelectorString;

@DelimitedRecord("|")
@Seletor(token="IDREC", type=RecordAction.Master, seletorString=SelectorString.StarWith)
public class RegistroDeIdentificacaoDoCodigoDeReceita {

	@FieldNullValue("IDREC")
	private String identificadorDoRegistro;
	private String codigoDeReceita ;
	
	
	
	
	public RegistroDeIdentificacaoDoCodigoDeReceita() {
		// TODO Auto-generated constructor stub
	}




	public String getIdentificadorDoRegistro() {
		return identificadorDoRegistro;
	}




	public void setIdentificadorDoRegistro(String identificadorDoRegistro) {
		this.identificadorDoRegistro = identificadorDoRegistro;
	}




	public String getCodigoDeReceita() {
		return codigoDeReceita;
	}




	public void setCodigoDeReceita(String codigoDeReceita) {
		this.codigoDeReceita = codigoDeReceita;
	}




	@Override
	public String toString() {
		return "RegistroDeIdentificacaoDoCodigoDeReceita [identificadorDoRegistro=" + identificadorDoRegistro
				+ ", codigoDeReceita=" + codigoDeReceita + "]";
	}
	
	
	
	
	
}
