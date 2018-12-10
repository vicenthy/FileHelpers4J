package com.github.vicenthy.filehelpers4j.tests.types.dirf.exemplo;

import com.github.vicenthy.filehelpers4j.annotations.DelimitedRecord;
import com.github.vicenthy.filehelpers4j.annotations.FieldNullValue;
import com.github.vicenthy.filehelpers4j.annotations.FieldOptional;
import com.github.vicenthy.filehelpers4j.annotations.Seletor;
import com.github.vicenthy.filehelpers4j.masterdetail.RecordAction;
import com.github.vicenthy.filehelpers4j.masterdetail.SelectorString;

@DelimitedRecord("|")
@Seletor(token="BPFDEC", type=RecordAction.MasterDetail, seletorString=SelectorString.StarWith)
public class RegistroDeBeneficiarioPessoaFisicaDoDeclarante {
	
	
	@FieldNullValue("BPFDEC")
	private String identificadoDoRegistro;
	private String cpf;
	private String nome;
	@FieldOptional
	private String dataAtribuidaPeloLaudoDaMolesiaGrave;
	private String indicadorDeIdentificacaoDoAlimentado;
	private String indicadorDeIdentificacaoDePrevidenciaComplementar;
	
	
	
	
	
	public RegistroDeBeneficiarioPessoaFisicaDoDeclarante() {
		// TODO Auto-generated constructor stub
	}





	public RegistroDeBeneficiarioPessoaFisicaDoDeclarante(String cpf, String nome) {
		this.cpf = cpf;
		this.nome = nome;
	}





	public String getIdentificadoDoRegistro() {
		return identificadoDoRegistro;
	}





	public void setIdentificadoDoRegistro(String identificadoDoRegistro) {
		this.identificadoDoRegistro = identificadoDoRegistro;
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





	public String getDataAtribuidaPeloLaudoDaMolesiaGrave() {
		return dataAtribuidaPeloLaudoDaMolesiaGrave;
	}





	public void setDataAtribuidaPeloLaudoDaMolesiaGrave(String dataAtribuidaPeloLaudoDaMolesiaGrave) {
		this.dataAtribuidaPeloLaudoDaMolesiaGrave = dataAtribuidaPeloLaudoDaMolesiaGrave;
	}





	public String getIndicadorDeIdentificacaoDoAlimentado() {
		return indicadorDeIdentificacaoDoAlimentado;
	}





	public void setIndicadorDeIdentificacaoDoAlimentado(String indicadorDeIdentificacaoDoAlimentado) {
		this.indicadorDeIdentificacaoDoAlimentado = indicadorDeIdentificacaoDoAlimentado;
	}





	public String getIndicadorDeIdentificacaoDePrevidenciaComplementar() {
		return indicadorDeIdentificacaoDePrevidenciaComplementar;
	}





	public void setIndicadorDeIdentificacaoDePrevidenciaComplementar(
			String indicadorDeIdentificacaoDePrevidenciaComplementar) {
		this.indicadorDeIdentificacaoDePrevidenciaComplementar = indicadorDeIdentificacaoDePrevidenciaComplementar;
	}





	@Override
	public String toString() {
		return "RegistroDeBeneficiarioPessoaFisicaDoDeclarante [identificadoDoRegistro=" + identificadoDoRegistro
				+ ", cpf=" + cpf + ", nome=" + nome + ", dataAtribuidaPeloLaudoDaMolesiaGrave="
				+ dataAtribuidaPeloLaudoDaMolesiaGrave + ", indicadorDeIdentificacaoDoAlimentado="
				+ indicadorDeIdentificacaoDoAlimentado + ", indicadorDeIdentificacaoDePrevidenciaComplementar="
				+ indicadorDeIdentificacaoDePrevidenciaComplementar + "]";
	}
	
	
	
	
	
}
