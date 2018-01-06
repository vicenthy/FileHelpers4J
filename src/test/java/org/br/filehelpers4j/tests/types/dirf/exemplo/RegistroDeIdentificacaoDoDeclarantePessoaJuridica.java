package org.br.filehelpers4j.tests.types.dirf.exemplo;

import org.br.filehelpers4j.annotations.DelimitedRecord;
import org.br.filehelpers4j.annotations.FieldNullValue;
import org.br.filehelpers4j.annotations.Seletor;
import org.br.filehelpers4j.masterdetail.RecordAction;

@DelimitedRecord("|")
@Seletor(token="DECPJ", type=RecordAction.Master)
public class RegistroDeIdentificacaoDoDeclarantePessoaJuridica {
	
	@FieldNullValue("DECPJ")
	private String idenficadorDoRegistro;
	private String cnpj;
	private String nomeEmpresarial;
	private Integer naturezaDoDeclarante;
	private String cpfPeranteCnpj;
	private String indicadorSocioOstensivoResponsavelSCP;
	private String indicadorDeclaranteDepositarioDecorrenteDecisaoJudicial;
	private String indicadorDeclaranteAdministradoraOuIntermediadora;
	private String indicadorDeDeclanteResidenteNoExterior;
	private String indicadorDePlanoPrivadoDeAssistenciaASaude;
	private String indicadorDePagamentoOlimpiadas2016;
	private String indicadorEntidadeCapitalSocialSujeitoAVoto;
	private String indicadorSituacaoEspecialDaDeclaracao;
	private String dataEvento;
	
	
	
	
	
	public RegistroDeIdentificacaoDoDeclarantePessoaJuridica() {
		// TODO Auto-generated constructor stub
	}





	public String getIdenficadorDoRegistro() {
		return idenficadorDoRegistro;
	}





	public void setIdenficadorDoRegistro(String idenficadorDoRegistro) {
		this.idenficadorDoRegistro = idenficadorDoRegistro;
	}





	public String getCnpj() {
		return cnpj;
	}





	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}





	public String getNomeEmpresarial() {
		return nomeEmpresarial;
	}





	public void setNomeEmpresarial(String nomeEmpresarial) {
		this.nomeEmpresarial = nomeEmpresarial;
	}





	public Integer getNaturezaDoDeclarante() {
		return naturezaDoDeclarante;
	}





	public void setNaturezaDoDeclarante(Integer naturezaDoDeclarante) {
		this.naturezaDoDeclarante = naturezaDoDeclarante;
	}





	public String getCpfPeranteCnpj() {
		return cpfPeranteCnpj;
	}





	public void setCpfPeranteCnpj(String cpfPeranteCnpj) {
		this.cpfPeranteCnpj = cpfPeranteCnpj;
	}





	public String getIndicadorSocioOstensivoResponsavelSCP() {
		return indicadorSocioOstensivoResponsavelSCP;
	}





	public void setIndicadorSocioOstensivoResponsavelSCP(String indicadorSocioOstensivoResponsavelSCP) {
		this.indicadorSocioOstensivoResponsavelSCP = indicadorSocioOstensivoResponsavelSCP;
	}





	public String getIndicadorDeclaranteDepositarioDecorrenteDecisaoJudicial() {
		return indicadorDeclaranteDepositarioDecorrenteDecisaoJudicial;
	}





	public void setIndicadorDeclaranteDepositarioDecorrenteDecisaoJudicial(
			String indicadorDeclaranteDepositarioDecorrenteDecisaoJudicial) {
		this.indicadorDeclaranteDepositarioDecorrenteDecisaoJudicial = indicadorDeclaranteDepositarioDecorrenteDecisaoJudicial;
	}





	public String getIndicadorDeclaranteAdministradoraOuIntermediadora() {
		return indicadorDeclaranteAdministradoraOuIntermediadora;
	}





	public void setIndicadorDeclaranteAdministradoraOuIntermediadora(
			String indicadorDeclaranteAdministradoraOuIntermediadora) {
		this.indicadorDeclaranteAdministradoraOuIntermediadora = indicadorDeclaranteAdministradoraOuIntermediadora;
	}





	public String getIndicadorDeDeclanteResidenteNoExterior() {
		return indicadorDeDeclanteResidenteNoExterior;
	}





	public void setIndicadorDeDeclanteResidenteNoExterior(String indicadorDeDeclanteResidenteNoExterior) {
		this.indicadorDeDeclanteResidenteNoExterior = indicadorDeDeclanteResidenteNoExterior;
	}





	public String getIndicadorDePlanoPrivadoDeAssistenciaASaude() {
		return indicadorDePlanoPrivadoDeAssistenciaASaude;
	}





	public void setIndicadorDePlanoPrivadoDeAssistenciaASaude(String indicadorDePlanoPrivadoDeAssistenciaASaude) {
		this.indicadorDePlanoPrivadoDeAssistenciaASaude = indicadorDePlanoPrivadoDeAssistenciaASaude;
	}





	public String getIndicadorDePagamentoOlimpiadas2016() {
		return indicadorDePagamentoOlimpiadas2016;
	}





	public void setIndicadorDePagamentoOlimpiadas2016(String indicadorDePagamentoOlimpiadas2016) {
		this.indicadorDePagamentoOlimpiadas2016 = indicadorDePagamentoOlimpiadas2016;
	}





	public String getIndicadorEntidadeCapitalSocialSujeitoAVoto() {
		return indicadorEntidadeCapitalSocialSujeitoAVoto;
	}





	public void setIndicadorEntidadeCapitalSocialSujeitoAVoto(String indicadorEntidadeCapitalSocialSujeitoAVoto) {
		this.indicadorEntidadeCapitalSocialSujeitoAVoto = indicadorEntidadeCapitalSocialSujeitoAVoto;
	}





	public String getIndicadorSituacaoEspecialDaDeclaracao() {
		return indicadorSituacaoEspecialDaDeclaracao;
	}





	public void setIndicadorSituacaoEspecialDaDeclaracao(String indicadorSituacaoEspecialDaDeclaracao) {
		this.indicadorSituacaoEspecialDaDeclaracao = indicadorSituacaoEspecialDaDeclaracao;
	}





	public String getDataEvento() {
		return dataEvento;
	}





	public void setDataEvento(String dataEvento) {
		this.dataEvento = dataEvento;
	}
	
	
	
	
	
	
}
