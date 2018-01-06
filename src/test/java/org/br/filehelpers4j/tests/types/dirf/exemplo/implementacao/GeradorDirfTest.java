package org.br.filehelpers4j.tests.types.dirf.exemplo.implementacao;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.br.filehelpers4j.masterdetailmultirecord.MasterDetailMultiRecordEngine;
import org.br.filehelpers4j.masterdetailmultirecord.MasterDetailMultiRecordFluentImplement;
import org.br.filehelpers4j.tests.masterdetailmultirecord.MasterDetailMultiRecordTest;
import org.br.filehelpers4j.tests.types.dirf.exemplo.RegistroDeBeneficiarioPessoaFisicaDoDeclarante;
import org.br.filehelpers4j.tests.types.dirf.exemplo.RegistroDeIdentificacaoDaDeclaracao;
import org.br.filehelpers4j.tests.types.dirf.exemplo.RegistroDeIdentificacaoDoCodigoDeReceita;
import org.br.filehelpers4j.tests.types.dirf.exemplo.RegistroDeIdentificacaoDoDeclarantePessoaJuridica;
import org.br.filehelpers4j.tests.types.dirf.exemplo.RegistroDeIdentificacaoDoTerminoDaDeclaracao;
import org.br.filehelpers4j.tests.types.dirf.exemplo.RegistroDeValoresMensaisImpostoRetidoNaFonte;
import org.br.filehelpers4j.tests.types.dirf.exemplo.RegistroDeValoresMensaisRendimentos;
import org.br.filehelpers4j.tests.types.dirf.exemplo.RegistroResponsavelPreenchimentoDeclaracao;
import org.junit.Before;
import org.junit.Test;

public class GeradorDirfTest {

	
	private MasterDetailMultiRecordFluentImplement mapa;
	private MasterDetailMultiRecordEngine engine;
	private static final String BASEDIR = GeradorDirfTest.class.getClass().getResource("/test/Good/").getPath();
	private static final String ARQUIVO = "33780222000123-DIRF-2016-2015-ORIGI-NORMAL.DEC";
	private Map<Object, List<?>> result;

	
	@Before
	public void init() {
		mapa = new MasterDetailMultiRecordFluentImplement();
		mapa
		.addMapperFile(RegistroDeIdentificacaoDaDeclaracao.class)
		.addMapperFile(RegistroResponsavelPreenchimentoDeclaracao.class)
		.addMapperFile(RegistroDeIdentificacaoDoDeclarantePessoaJuridica.class)
		.addMapperFile(RegistroDeIdentificacaoDoCodigoDeReceita.class)
		.addMapperFile(RegistroDeBeneficiarioPessoaFisicaDoDeclarante.class)
		.addMapperFile(RegistroDeValoresMensaisRendimentos.class)
		.addMapperFile(RegistroDeValoresMensaisImpostoRetidoNaFonte.class)
		.addMapperFile(RegistroDeIdentificacaoDoTerminoDaDeclaracao.class);
		
		mapa.setHeaderFile(RegistroDeIdentificacaoDaDeclaracao.class);
		mapa.setFooterFile(RegistroDeIdentificacaoDoTerminoDaDeclaracao.class);
		
		engine = new MasterDetailMultiRecordEngine(mapa);

	}
	
	@Test
	public void lerArquivoDirf() {
		try {
			result = engine.readFile(BASEDIR + ARQUIVO);
			assertNotNull(result);
			
			result.forEach( (master, detail) -> {
				System.out.println("MASTER" + master);
				System.out.println("DETAIL" + detail);
			});
			System.out.println(engine.getHeader());
			System.out.println(engine.getFooter());
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	
	@Test
	public void gerarArquivoDirf() {
		
	}
}
