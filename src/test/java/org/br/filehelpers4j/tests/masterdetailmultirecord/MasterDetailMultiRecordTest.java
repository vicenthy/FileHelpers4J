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
import org.br.filehelpers4j.masterdetailmultirecord.MasterDetailMultiRecordEngine;
import org.br.filehelpers4j.masterdetailmultirecord.MasterDetailMultiRecordFluentImplement;
import org.br.filehelpers4j.tests.types.multirecord.Fonograma;
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
		mapa
		.addHeaderTransaction(HeaderTitular.class, setSelector("0TIT", RecordAction.HeaderTransaction))
		.addMaster(TitularHelper.class, setSelector("TIT1", RecordAction.Master))
		.addDetail(LocalizacaoEDocumentacaoHelper.class, setSelector("TIT2", RecordAction.Detail))
		.addDetail(PseudonimoHelper.class, setSelector("TIT4", RecordAction.Detail))
		.addTraillerTransaction(TraillerTitular.class, setSelector("9TIT", RecordAction.TraillerTransaction))
		//Obra
		.addHeaderTransaction(HeaderObra.class, setSelector("0OBM", RecordAction.HeaderTransaction))
		.addMaster(ObraMusicalHelper.class, setSelector("OBM1", RecordAction.Master))
		.addDetail(TitularesObra.class, setSelector("OBM2", RecordAction.Detail))
		.addDetail(SubTitulo.class, setSelector("OBM3", RecordAction.Detail))
		.addTraillerTransaction(TraillerObra.class, setSelector("9OBM", RecordAction.TraillerTransaction))
		//Fonograma
		.addHeaderTransaction(HeaderFonograma.class, setSelector("0FON", RecordAction.HeaderTransaction))
		.addMaster(Fonograma.class, setSelector("FON1", RecordAction.Master))
		.addDetail(TitularesFonograma.class, setSelector("FON2", RecordAction.Detail))
		.addDetail(InstrumentosFonograma.class, setSelector("FON3", RecordAction.Detail))
		.addTraillerTransaction(TraillerFonograma.class, setSelector("9FON", RecordAction.TraillerTransaction));
		engine = new MasterDetailMultiRecordEngine(mapa);
		file = new File(BASEDIR + "masterdetailmultirecord-result.txt");
		if(file.exists()){
			assertTrue(file.delete());
		}
	}
	
	public void testLeituraArquivoDeLayout() throws Exception {
		result = engine.readFile(BASEDIR + "masterdetailmultirecord-example.txt");
		assertEquals(6, result.keySet().size());
		assertEquals(2, result.get(result.keySet().toArray()[0]).size());
		assertEquals(2, result.get(result.keySet().toArray()[1]).size());
		assertEquals(3, result.get(result.keySet().toArray()[2]).size());
		assertEquals(3, result.get(result.keySet().toArray()[3]).size());
		assertEquals(3, result.get(result.keySet().toArray()[4]).size());
		assertEquals(13,result.get(result.keySet().toArray()[5]).size());
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
	
	private RecordActionSelector setSelector(String token, RecordAction action) {
		return new RecordActionSelector() {
			@Override
			public RecordAction getRecordAction(String recordString) {
				if(recordString.contains(token))
				return action;
			return RecordAction.Skip;
			}
		};
	}
	
	
}
