package org.br.filehelpers4j.tests.types.dirf.exemplo;

import org.br.filehelpers4j.annotations.DelimitedRecord;
import org.br.filehelpers4j.annotations.FieldNullValue;

@DelimitedRecord("|")
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
	
}
