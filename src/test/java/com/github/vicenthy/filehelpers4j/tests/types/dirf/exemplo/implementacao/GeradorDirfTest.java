package com.github.vicenthy.filehelpers4j.tests.types.dirf.exemplo.implementacao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.github.vicenthy.filehelpers4j.masterdetailmultirecord.MasterDetailMultiRecordEngine;
import com.github.vicenthy.filehelpers4j.masterdetailmultirecord.MasterDetailMultiRecordFluentImplement;
import com.github.vicenthy.filehelpers4j.tests.types.dirf.exemplo.RegistroDeBeneficiarioPessoaFisicaDoDeclarante;
import com.github.vicenthy.filehelpers4j.tests.types.dirf.exemplo.RegistroDeIdentificacaoDaDeclaracao;
import com.github.vicenthy.filehelpers4j.tests.types.dirf.exemplo.RegistroDeIdentificacaoDoCodigoDeReceita;
import com.github.vicenthy.filehelpers4j.tests.types.dirf.exemplo.RegistroDeIdentificacaoDoDeclarantePessoaJuridica;
import com.github.vicenthy.filehelpers4j.tests.types.dirf.exemplo.RegistroDeIdentificacaoDoTerminoDaDeclaracao;
import com.github.vicenthy.filehelpers4j.tests.types.dirf.exemplo.RegistroDeValoresMensaisImpostoRetidoNaFonte;
import com.github.vicenthy.filehelpers4j.tests.types.dirf.exemplo.RegistroDeValoresMensaisRendimentos;
import com.github.vicenthy.filehelpers4j.tests.types.dirf.exemplo.RegistroResponsavelPreenchimentoDeclaracao;

public class GeradorDirfTest {

	
	private MasterDetailMultiRecordFluentImplement mapa;
	private MasterDetailMultiRecordEngine engine;
	private static final String BASEDIR = GeradorDirfTest.class.getClass().getResource("/test/Good/").getPath();
	private static final String ARQUIVO = "33780222000123-DIRF-2016-2015-ORIGI-NORMAL.DEC";
	private static final String ARQUIVO_SAIDA = "dirf.txt";
	//Dados preechimento Dirf
	private RegistroDeIdentificacaoDaDeclaracao dirf ;
	private RegistroResponsavelPreenchimentoDeclaracao respo ;
	private RegistroDeIdentificacaoDoDeclarantePessoaJuridica decpj ;
	private RegistroDeIdentificacaoDoCodigoDeReceita idrec ;
	private RegistroDeIdentificacaoDoTerminoDaDeclaracao fimdirf ;

	private Map<Object, List<?>> result;
	private List<LinkedHashMap> subdetail;
	private File file;	
	private LinkedHashMap masterdetail;

	
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
		
		
		dirf = new RegistroDeIdentificacaoDaDeclaracao();
		respo = new RegistroResponsavelPreenchimentoDeclaracao();
		decpj = new RegistroDeIdentificacaoDoDeclarantePessoaJuridica();
		idrec = new RegistroDeIdentificacaoDoCodigoDeReceita();
		fimdirf = new RegistroDeIdentificacaoDoTerminoDaDeclaracao();
		
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

			
			
			assertNotNull(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	@Test
	public void gerarArquivoDirfManual() {
		
		masterdetail = new LinkedHashMap();
		subdetail = new ArrayList<>();
		result = new LinkedHashMap();
		configDirf();
		//Dados Beneficiario 01
		RegistroDeBeneficiarioPessoaFisicaDoDeclarante bpfdec = criarBeneficiario("58637297080", "JOAO DA SILVA 001");
		RegistroDeValoresMensaisRendimentos rtrt = criarRendimentos("221", "1233", "2222", "333", "332", "232");
		RegistroDeValoresMensaisImpostoRetidoNaFonte rtirf = criarImpostoRetidoNaFonte ("221", "1233", "2222", null, null, null);
		List<Object> rendimentos1 = new ArrayList<>();
		rendimentos1.add(rtrt);
		rendimentos1.add(rtirf);
		//Dados Beneficiario 02
		RegistroDeBeneficiarioPessoaFisicaDoDeclarante bpfdec2 = criarBeneficiario("58637297080", "JOAO DA SILVA 002");
		RegistroDeValoresMensaisRendimentos rtrt2 = criarRendimentos("221", "1233", null, "333", null, "232");
		RegistroDeValoresMensaisImpostoRetidoNaFonte rtirf2 = criarImpostoRetidoNaFonte (null, "1233", "2222", null, null, null);
		List<Object> rendimentos2 = new ArrayList<>();
		rendimentos2.add(rtrt2);
		rendimentos2.add(rtirf2);
		//Dados Beneficiario 03
		RegistroDeBeneficiarioPessoaFisicaDoDeclarante bpfdec3 = criarBeneficiario("58637297080", "JOAO DA SILVA 003");
		RegistroDeValoresMensaisRendimentos rtrt3 = criarRendimentos("221", "1233", "2222", null, "332", "232344");
		RegistroDeValoresMensaisImpostoRetidoNaFonte rtirf3 = criarImpostoRetidoNaFonte ("221", null, "2222", null, null, null);
		List<Object> rendimentos3 = new ArrayList<>();
		rendimentos3.add(rtrt3);
		rendimentos3.add(rtirf3);
		//adição dos dados masterdetail na ordem
		masterdetail.put(bpfdec, rendimentos1);
		masterdetail.put(bpfdec2, rendimentos2);
		masterdetail.put(bpfdec3, rendimentos3);
		subdetail.add(masterdetail);
		
		
		try {
			//adição dos dados seguindo a ordem do mapa
			result.put(respo, null);
			result.put(decpj, null);
			result.put(idrec, subdetail);
			engine.setHeader(dirf);
			engine.setFooter(fimdirf);
			//geração do arquivo	
			engine.writeFile(BASEDIR + ARQUIVO_SAIDA, result, 1);
			assertTrue(file.exists());
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			assertTrue(reader.lines().count() > 0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
	}

	private RegistroDeValoresMensaisImpostoRetidoNaFonte criarImpostoRetidoNaFonte(String janeiro, String fevereiro, String marco,
			String abril, String maio, String junho) {
		return new RegistroDeValoresMensaisImpostoRetidoNaFonte(janeiro, fevereiro, marco,
				abril,  maio, junho);
	}

	private RegistroDeValoresMensaisRendimentos criarRendimentos(String janeiro, String fevereiro, String marco,
			String abril, String maio, String junho) {
		return new RegistroDeValoresMensaisRendimentos(janeiro, fevereiro, marco,
				abril,  maio, junho);
	}

	private RegistroDeBeneficiarioPessoaFisicaDoDeclarante criarBeneficiario(String cpf, String nome) {
		return new RegistroDeBeneficiarioPessoaFisicaDoDeclarante(cpf, nome);
	}

	private void configDirf() {
		dirf.setAnoCalendario(2017);
		dirf.setAnoReferencia(2016);
		
		
		respo.setNome("TESTE");
		respo.setCpf("71717510078");
		respo.setDdd("21");
		respo.setEmail("teste@gmail.com");
		respo.setTelefone("2122203635");
		respo.setRamal("22");
		
		
		
		decpj.setCnpj("26343615000104");
		decpj.setCpfPeranteCnpj("13001078715");
		decpj.setNomeEmpresarial("EMPRESA TESTE");
		decpj.setNaturezaDoDeclarante(0);
		decpj.setIndicadorDeclaranteAdministradoraOuIntermediadora("N");
		decpj.setIndicadorDeclaranteDepositarioDecorrenteDecisaoJudicial("N");
		decpj.setIndicadorDeDeclanteResidenteNoExterior("N");
		decpj.setIndicadorDePagamentoOlimpiadas2016("N");
		decpj.setIndicadorDePlanoPrivadoDeAssistenciaASaude("N");
		decpj.setIndicadorEntidadeCapitalSocialSujeitoAVoto("N");
		decpj.setIndicadorSituacaoEspecialDaDeclaracao("N");
		idrec.setCodigoDeReceita("0558");
		fimdirf.setIdentificadorDoRegistro("FIMDirf");
	}
	
	
	@Test
	public void gerarArquivoDirfDeListaVindaDoArquivoPronto() {
		try {
			result = engine.readFile(BASEDIR + ARQUIVO);
			assertNotNull(result);
			configDirf();
			engine.setHeader(dirf);
			engine.setFooter(fimdirf);
			engine.writeFile(BASEDIR + ARQUIVO_SAIDA, result, 1);
			assertTrue(file.exists());
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			assertTrue(reader.lines().count() > 0 );
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
