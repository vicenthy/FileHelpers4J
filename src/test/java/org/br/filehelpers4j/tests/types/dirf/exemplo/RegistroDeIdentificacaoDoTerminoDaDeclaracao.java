package org.br.filehelpers4j.tests.types.dirf.exemplo;

import org.br.filehelpers4j.annotations.DelimitedRecord;
import org.br.filehelpers4j.annotations.FieldNullValue;

@DelimitedRecord("|")
public class RegistroDeIdentificacaoDoTerminoDaDeclaracao {

	@FieldNullValue("FIMDirf")
	private String identificadorDoRegistro;
	
}
