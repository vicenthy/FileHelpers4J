/*
 * MasterDetailMultiRecord.java
 *
 * Copyright (C) 2007 Felipe Gonçalves Coury <felipe.coury@gmail.com>
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.br.filehelpers4j.tests.masterdetailmultirecord;



import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.br.filehelpers4j.masterdetail.RecordAction;
import org.br.filehelpers4j.masterdetail.RecordActionSelector;
import org.br.filehelpers4j.masterdetailmultirecord.HeaderFile;
import org.br.filehelpers4j.masterdetailmultirecord.MasterDetailMultiRecordEngine;
import org.br.filehelpers4j.masterdetailmultirecord.MasterDetailMultiRecordFluentImplement;
import org.br.filehelpers4j.masterdetailmultirecord.TraillerFile;
import org.br.filehelpers4j.tests.types.multirecord.Fonograma;
import org.br.filehelpers4j.tests.types.multirecord.FonogramaMasterDetail;
import org.br.filehelpers4j.tests.types.multirecord.HeaderFonograma;
import org.br.filehelpers4j.tests.types.multirecord.HeaderObra;
import org.br.filehelpers4j.tests.types.multirecord.HeaderTitular;
import org.br.filehelpers4j.tests.types.multirecord.InstrumentosFonograma;
import org.br.filehelpers4j.tests.types.multirecord.LocalizacaoEDocumentacaoHelper;
import org.br.filehelpers4j.tests.types.multirecord.ObraMusicalHelper;
import org.br.filehelpers4j.tests.types.multirecord.PseudonimoHelper;
import org.br.filehelpers4j.tests.types.multirecord.SubTitulo;
import org.br.filehelpers4j.tests.types.multirecord.TitularHelper;
import org.br.filehelpers4j.tests.types.multirecord.TitularesFonograma;
import org.br.filehelpers4j.tests.types.multirecord.TitularesFonogramaSubDetail1;
import org.br.filehelpers4j.tests.types.multirecord.TitularesFonogramaSubDetail2;
import org.br.filehelpers4j.tests.types.multirecord.TitularesObra;
import org.br.filehelpers4j.tests.types.multirecord.TraillerFonograma;
import org.br.filehelpers4j.tests.types.multirecord.TraillerObra;
import org.br.filehelpers4j.tests.types.multirecord.TraillerTitular;

import junit.framework.TestCase;

public class MasterDetailMultiRecordTest extends TestCase {
	
	
	private MasterDetailMultiRecordFluentImplement mapa;
	private MasterDetailMultiRecordEngine engine;
	private Map<Object, List<?>> result;
	private File file;
	private static final String BASEDIR = MasterDetailMultiRecordTest.class.getClass().getResource("/test/Good/").getPath();

	
	@Override
	protected void setUp() throws Exception {
	
		mapa = new MasterDetailMultiRecordFluentImplement();
		//Titulares
		mapa.addMapperFile(HeaderTitular.class)
		.addMapperFile(TitularHelper.class)
		.addMapperFile(LocalizacaoEDocumentacaoHelper.class)
		.addMapperFile(PseudonimoHelper.class)
		.addMapperFile(TraillerTitular.class)
		//Obra
		.addMapperFile(HeaderObra.class)
		.addMapperFile(ObraMusicalHelper.class)
		.addMapperFile(TitularesObra.class)
		.addMapperFile(SubTitulo.class)
		.addMapperFile(TraillerObra.class)
		//Fonograma
		.addMapperFile(HeaderFonograma.class)
		.addMapperFile(FonogramaMasterDetail.class)
		.addMapperFile(TitularesFonogramaSubDetail1.class)
		.addMapperFile(TitularesFonogramaSubDetail2.class)
		.addMapperFile(Fonograma.class)
		.addMapperFile(TitularesFonograma.class)
		.addMapperFile(InstrumentosFonograma.class)
		.addMapperFile(TraillerFonograma.class)
		.addMapperFile(TraillerFile.class);
	
		mapa.setFooterFile(TraillerFile.class);
		mapa.setHeaderFile(HeaderFile.class);
		
		engine = new MasterDetailMultiRecordEngine(mapa);
		file = new File(BASEDIR + "masterdetailmultirecord-result.txt");
		if(file.exists()){
			assertTrue(file.delete());
		}
	}
	
	public void testLeituraArquivoDeLayoutPadrao() throws Exception {
		result = engine.readFile(BASEDIR + "masterdetailmultirecord-example.txt");
		//quantidade de registros do tipo master
		assertEquals(6, result.keySet().size());
		assertEquals(2, result.get(result.keySet().toArray()[0]).size());
		assertEquals(2, result.get(result.keySet().toArray()[1]).size());
		assertEquals(3, result.get(result.keySet().toArray()[2]).size());
		assertEquals(3, result.get(result.keySet().toArray()[3]).size());
		assertEquals(3, result.get(result.keySet().toArray()[4]).size());
		assertEquals(13,result.get(result.keySet().toArray()[5]).size());
		
	}

	
	public void testLeituraArquivoDeLayoutMasterDetailSubDetail() throws Exception {
		result = engine.readFile(BASEDIR + "masterdetailmultirecord-example-2.txt");
		//quantidade de registros do tipo master
		assertTrue(result.size() > 0);
		assertEquals(7, result.keySet().size());
		assertEquals(6,result.get(result.keySet().toArray()[6]).size());
		assertEquals(12,result.get(result.keySet().toArray()[5]).size());
		result.get(result.keySet().toArray()[5]).forEach(a -> {
			System.out.println(a);
		});
		
	}
	
	
	
	public void testEscritaArquivoDeLayout() {
		try {
			result = engine.readFile(BASEDIR + "masterdetailmultirecord-example.txt");
			engine.writeFile(BASEDIR + "masterdetailmultirecord-result.txt", result, 1);
			assertTrue(file.exists());
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
}
