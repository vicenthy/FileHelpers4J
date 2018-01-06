package org.br.filehelpers4j.tests.types.dirf.exemplo;

import org.br.filehelpers4j.annotations.DelimitedRecord;
import org.br.filehelpers4j.annotations.FieldNullValue;

@DelimitedRecord("|")
public class RegistroDeIdentificacaoDoCodigoDeReceita {

	@FieldNullValue("IDREC")
	private String identificadorDoRegistro;
	private Integer codigoDeReceita ;
	
}
