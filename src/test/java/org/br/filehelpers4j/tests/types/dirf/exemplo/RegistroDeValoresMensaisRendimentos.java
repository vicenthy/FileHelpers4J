package org.br.filehelpers4j.tests.types.dirf.exemplo;

import org.br.filehelpers4j.annotations.DelimitedRecord;
import org.br.filehelpers4j.annotations.FieldNullValue;
import org.br.filehelpers4j.annotations.Seletor;
import org.br.filehelpers4j.masterdetail.RecordAction;
import org.br.filehelpers4j.masterdetail.SelectorString;


@DelimitedRecord("|")
@Seletor(token="RTRT", type=RecordAction.SubDetail, seletorString=SelectorString.StarWith)
public class RegistroDeValoresMensaisRendimentos {

	
	@FieldNullValue("RTRT")
	private String identificadorDoRegistro;
	private String janeiro;
	private String fevereiro;
	private String marco;
	private String abril;
	private String maio;
	private String junho;
	private String julho;
	private String agosto;
	private String setembro;
	private String outubro;
	private String novembro;
	private String dezembro;
	private String decimoTerceiro;
	
	
	public RegistroDeValoresMensaisRendimentos() {
		// TODO Auto-generated constructor stub
	}


	public String getIdentificadorDoRegistro() {
		return identificadorDoRegistro;
	}


	public void setIdentificadorDoRegistro(String identificadorDoRegistro) {
		this.identificadorDoRegistro = identificadorDoRegistro;
	}


	public String getJaneiro() {
		return janeiro;
	}


	public void setJaneiro(String janeiro) {
		this.janeiro = janeiro;
	}


	public String getFevereiro() {
		return fevereiro;
	}


	public void setFevereiro(String fevereiro) {
		this.fevereiro = fevereiro;
	}


	public String getMarco() {
		return marco;
	}


	public void setMarco(String marco) {
		this.marco = marco;
	}


	public String getAbril() {
		return abril;
	}


	public void setAbril(String abril) {
		this.abril = abril;
	}


	public String getMaio() {
		return maio;
	}


	public void setMaio(String maio) {
		this.maio = maio;
	}


	public String getJunho() {
		return junho;
	}


	public void setJunho(String junho) {
		this.junho = junho;
	}


	public String getJulho() {
		return julho;
	}


	public void setJulho(String julho) {
		this.julho = julho;
	}


	public String getAgosto() {
		return agosto;
	}


	public void setAgosto(String agosto) {
		this.agosto = agosto;
	}


	public String getSetembro() {
		return setembro;
	}


	public void setSetembro(String setembro) {
		this.setembro = setembro;
	}


	public String getOutubro() {
		return outubro;
	}


	public void setOutubro(String outubro) {
		this.outubro = outubro;
	}


	public String getNovembro() {
		return novembro;
	}


	public void setNovembro(String novembro) {
		this.novembro = novembro;
	}


	public String getDezembro() {
		return dezembro;
	}


	public void setDezembro(String dezembro) {
		this.dezembro = dezembro;
	}


	public String getDecimoTerceiro() {
		return decimoTerceiro;
	}


	public void setDecimoTerceiro(String decimoTerceiro) {
		this.decimoTerceiro = decimoTerceiro;
	}


	@Override
	public String toString() {
		return "RegistroDeValoresMensaisRendimentos [identificadorDoRegistro=" + identificadorDoRegistro + ", janeiro="
				+ janeiro + ", fevereiro=" + fevereiro + ", marco=" + marco + ", abril=" + abril + ", maio=" + maio
				+ ", junho=" + junho + ", julho=" + julho + ", agosto=" + agosto + ", setembro=" + setembro
				+ ", outubro=" + outubro + ", novembro=" + novembro + ", dezembro=" + dezembro + ", decimoTerceiro="
				+ decimoTerceiro + "]";
	}
	
	
	
	
	
}
