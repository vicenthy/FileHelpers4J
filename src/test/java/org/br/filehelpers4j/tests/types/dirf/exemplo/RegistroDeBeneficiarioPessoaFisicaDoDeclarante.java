package org.br.filehelpers4j.tests.types.dirf.exemplo;

import org.br.filehelpers4j.annotations.DelimitedRecord;
import org.br.filehelpers4j.annotations.FieldNullValue;
import org.br.filehelpers4j.annotations.FieldOptional;

@DelimitedRecord("|")
public class RegistroDeBeneficiarioPessoaFisicaDoDeclarante {
	
	
	@FieldNullValue("BPFDEC")
	private String identificadoDoRegistro;
	private String cpf;
	private String nome;
	@FieldOptional
	private String dataAtribuidaPeloLaudoDaMolesiaGrave;
	private String indicadorDeIdentificacaoDoAlimentado;
	private String indicadorDeIdentificacaoDePrevidenciaComplementar;
	
}
