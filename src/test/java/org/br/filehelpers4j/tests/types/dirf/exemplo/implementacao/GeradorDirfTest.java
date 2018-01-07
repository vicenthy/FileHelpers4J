package org.br.filehelpers4j.tests.types.dirf.exemplo.implementacao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.br.filehelpers4j.masterdetailmultirecord.MasterDetailMultiRecordEngine;
import org.br.filehelpers4j.masterdetailmultirecord.MasterDetailMultiRecordFluentImplement;
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
	private static final String ARQUIVO_SAIDA = "dirf.txt";

	private Map<Object, List<?>> result;
	private File file;

	
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
		file = new File(BASEDIR + ARQUIVO_SAIDA);
		if(file.exists()){
			file.delete();
			System.out.println("ARQUIVO ANTIGO DELETADO!");
		}

	}
	
	@Test
	public void lerArquivoDirf() {
		try {
			result = engine.readFile(BASEDIR + ARQUIVO);
			assertEquals(3, result.keySet().size());
			assertEquals(3, result.values().size());

			result.values().forEach(detail -> {
				if(detail.size() > 0) {
				detail.forEach(masterdetail -> {
					//assertEquals(3, ((LinkedHashMap)masterdetail).keySet().size());
				});	
				}
				
			});
			
			assertNotNull(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	
	@Test
	public void gerarArquivoDirf() {
		try {
			result = engine.readFile(BASEDIR + ARQUIVO);
			assertNotNull(result);
			engine.writeFile(BASEDIR + ARQUIVO_SAIDA, result, 1);
			assertTrue(file.exists());
		
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
				reader.lines().forEach(line -> {
					System.out.println(line);
				});
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
