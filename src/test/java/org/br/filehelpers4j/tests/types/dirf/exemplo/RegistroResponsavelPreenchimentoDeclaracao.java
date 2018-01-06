package org.br.filehelpers4j.tests.types.dirf.exemplo;

import org.br.filehelpers4j.annotations.DelimitedRecord;
import org.br.filehelpers4j.annotations.FieldNullValue;
import org.br.filehelpers4j.annotations.Seletor;
import org.br.filehelpers4j.masterdetail.RecordAction;

@DelimitedRecord("|")
@Seletor(token="RESPO", type=RecordAction.Master)
public class RegistroResponsavelPreenchimentoDeclaracao {
	
	@FieldNullValue("RESPO")
	private String identificadorDoRegistro;
	private String cpf;
	private String nome;
	private String ddd;
	private String telefone;
	private String ramal;
	private String fax;
	private String email;
	

	
	
	public RegistroResponsavelPreenchimentoDeclaracao() {
		// TODO Auto-generated constructor stub
	}




	public String getIdentificadorDoRegistro() {
		return identificadorDoRegistro;
	}




	public void setIdentificadorDoRegistro(String identificadorDoRegistro) {
		this.identificadorDoRegistro = identificadorDoRegistro;
	}




	public String getCpf() {
		return cpf;
	}




	public void setCpf(String cpf) {
		this.cpf = cpf;
	}




	public String getNome() {
		return nome;
	}




	public void setNome(String nome) {
		this.nome = nome;
	}




	public String getDdd() {
		return ddd;
	}




	public void setDdd(String ddd) {
		this.ddd = ddd;
	}




	public String getTelefone() {
		return telefone;
	}




	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}




	public String getRamal() {
		return ramal;
	}




	public void setRamal(String ramal) {
		this.ramal = ramal;
	}




	public String getFax() {
		return fax;
	}




	public void setFax(String fax) {
		this.fax = fax;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	
	
}
