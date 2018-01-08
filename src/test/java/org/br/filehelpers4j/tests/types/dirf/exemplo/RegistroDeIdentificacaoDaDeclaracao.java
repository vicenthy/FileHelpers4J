package org.br.filehelpers4j.tests.types.dirf.exemplo;

import org.br.filehelpers4j.annotations.DelimitedRecord;
import org.br.filehelpers4j.annotations.FieldNullValue;
import org.br.filehelpers4j.annotations.FieldOptional;
import org.br.filehelpers4j.annotations.Seletor;
import org.br.filehelpers4j.masterdetail.RecordAction;
import org.br.filehelpers4j.masterdetail.SelectorString;

/**
 * 
 * @author atila
 * 
 *   REGISTRO  - DIRF (cabeçalho)
 */
@DelimitedRecord("|")
@Seletor(token="Dirf", type=RecordAction.HeaderFile, seletorString=SelectorString.StarWith)
public class RegistroDeIdentificacaoDaDeclaracao {

	
	
	@FieldNullValue("Dirf")
	private String identificadorDoRegistro;
	private Integer anoReferencia;
	private Integer anoCalendario;
	@FieldNullValue("N")
	private String identificadorRetificadora;
	@FieldOptional
	private String numeroDoRecibo;
	@FieldNullValue("Q84FV63")
	private String IdentificadorDeEstruturaDoLayout;
	
	
	public RegistroDeIdentificacaoDaDeclaracao() {


	}
	
	public String getIdentificadorDoRegistro() {
		return identificadorDoRegistro;
	}
	public void setIdentificadorDoRegistro(String identificadorDoRegistro) {
		this.identificadorDoRegistro = identificadorDoRegistro;
	}
	public Integer getAnoReferencia() {
		return anoReferencia;
	}
	public void setAnoReferencia(Integer anoReferencia) {
		this.anoReferencia = anoReferencia;
	}
	public Integer getAnoCalendario() {
		return anoCalendario;
	}
	public void setAnoCalendario(Integer anoCalendario) {
		this.anoCalendario = anoCalendario;
	}
	public String getIdentificadorRetificadora() {
		return identificadorRetificadora;
	}
	public void setIdentificadorRetificadora(String identificadorRetificadora) {
		this.identificadorRetificadora = identificadorRetificadora;
	}
	public String getNumeroDoRecibo() {
		return numeroDoRecibo;
	}
	public void setNumeroDoRecibo(String numeroDoRecibo) {
		this.numeroDoRecibo = numeroDoRecibo;
	}
	public String getIdentificadorDeEstruturaDoLayout() {
		return IdentificadorDeEstruturaDoLayout;
	}
	public void setIdentificadorDeEstruturaDoLayout(String identificadorDeEstruturaDoLayout) {
		IdentificadorDeEstruturaDoLayout = identificadorDeEstruturaDoLayout;
	}

	@Override
	public String toString() {
		return "RegistroDeIdentificacaoDaDeclaracao [identificadorDoRegistro=" + identificadorDoRegistro
				+ ", anoReferencia=" + anoReferencia + ", anoCalendario=" + anoCalendario
				+ ", identificadorRetificadora=" + identificadorRetificadora + ", numeroDoRecibo=" + numeroDoRecibo
				+ ", IdentificadorDeEstruturaDoLayout=" + IdentificadorDeEstruturaDoLayout + "]";
	}
	
	
	
	
	
	
}
