/*
 * MasterDetailMultiRecordEngine.java
 *
 * Copyright (C) 2007 Atila Augusto dos Santos - <atila.sistemas@gmail.com>
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
package com.github.vicenthy.filehelpers4j.masterdetailmultirecord;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Stream;

import com.github.vicenthy.filehelpers4j.annotations.Seletor;
import com.github.vicenthy.filehelpers4j.core.RecordInfo;
import com.github.vicenthy.filehelpers4j.engines.LineInfo;
import com.github.vicenthy.filehelpers4j.events.AfterReadRecordHandler;
import com.github.vicenthy.filehelpers4j.events.AfterWriteRecordHandler;
import com.github.vicenthy.filehelpers4j.events.BeforeReadRecordEventArgs;
import com.github.vicenthy.filehelpers4j.events.BeforeReadRecordHandler;
import com.github.vicenthy.filehelpers4j.events.BeforeWriteRecordHandler;
import com.github.vicenthy.filehelpers4j.helpers.StringHelper;
import com.github.vicenthy.filehelpers4j.masterdetail.RecordAction;

public class MasterDetailMultiRecordEngine implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7381910119361044469L;
	private MasterDetailMultiRecordFluent fluent;
	private Object master;
	private List<Object> details;
	private Object masterdetail;
	private List<Object> subdetails;
	private Map<Object, List<?>> masterDetailSubDetail;
	private Map<Object, Object> traillerTransactions;
	private Map<Object, Object> headerTransactions;
	private Object headerTransaction;
	private Map<Object, List<?>> masterDetailMultiRecod;
	private FileWriter fw;
	private BufferedWriter writer;

	private BeforeReadRecordHandler<?> beforeReadRecordHandler;
	private AfterReadRecordHandler<?> afterReadRecordHandler;
	private BeforeWriteRecordHandler<?> beforeWriteRecordHandler;
	private AfterWriteRecordHandler<?> afterWriteRecordHandler;
	private Object footer;
	private Object header;

	public MasterDetailMultiRecordEngine(MasterDetailMultiRecordFluent fluent) {
		this.fluent = fluent;
	}

	private void init() {
		masterDetailMultiRecod = new LinkedHashMap<>();
		masterDetailSubDetail = new LinkedHashMap<>();
		traillerTransactions = new LinkedHashMap<>();
		headerTransactions = new LinkedHashMap<>();
		details = new ArrayList<>();
		subdetails = new ArrayList<>();
	}

	private Map<Object, List<?>> readStream(Reader fileReader) {
		init();
		BufferedReader reader = new BufferedReader(fileReader);
		reader.lines().forEach(line -> {
			RecordAction action = RecordAction.Skip;
			Class<?> entry = checkRegisterType(line);
			if (entry != null) {
				action = getCurrentRecorAction(entry);
			}
			switch (action) {
			case HeaderFile:
				verifyHeaderOrFooter(line);
				break;
			case HeaderTransaction:
				processHeaderTransaction(line, entry);
				break;
			case Master:
				finallyRegisterMasterDetail();
				finallyRegister();
				processMaster(line, entry);
				break;
			case MasterDetail:
				finallyRegisterMasterDetail();
				processMasterDetail(line, entry);
				break;
			case SubDetail:
				processSubDetail(line, entry);
				break;
			case Detail:
				processDetail(line, entry);
				break;
			case TraillerTransaction:
				processTraillerTransaction(line, entry);
				break;
			case Skip:
				processSkip(line, entry);
				break;
			case TraillerFile:
				finallyRegisterMasterDetail();
				finallyRegister();
				verifyHeaderOrFooter(line);
				break;
			default:
				break;
			}

		});
		return masterDetailMultiRecod;
	}

	private void processSkip(String line, Class<?> entry) {
		
	}

	private void processSubDetail(String line, Class<?> entry) {
		try {
			subdetails.add(parseStrToRecord(entry, line));
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}	
		
	}

	private void processMasterDetail(String line, Class<?> entry) {
		try {
			masterdetail = parseStrToRecord(entry, line);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void verifyHeaderOrFooter(String line) {
		try {
			if (existsHeaderAndFooter()) {
				if (findByToken(extractedAnnotation(fluent.getHeaderFile()), line)) {
					header = parseStrToRecord(fluent.getHeaderFile(), line);
				}
				if (findByToken(extractedAnnotation(fluent.getFooterFile()), line)) {
					footer = parseStrToRecord(fluent.getFooterFile(), line);
				}
			}	
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	private boolean findByToken(Seletor extractedAnnotation, String line) {
		switch (extractedAnnotation.seletorString()) {
		case Contains:
			return line.toLowerCase().contains(extractedAnnotation.token().toLowerCase());
		case Equals:
			return line.toLowerCase().equals(extractedAnnotation.token().toLowerCase());
		case StarWith:
			return line.toLowerCase().startsWith(extractedAnnotation.token().toLowerCase());
		case EndWith:
			return line.toLowerCase().endsWith(extractedAnnotation.token().toLowerCase());
		default:
			break;
		}
		return false;
	}


	private boolean existsHeaderAndFooter() {
		if(fluent.getFooterFile() != null && fluent.getHeaderFile() != null) {
				if(extractedAnnotation(fluent.getHeaderFile()) != null && extractedAnnotation(fluent.getFooterFile()) != null) {
					return true;
				}else {
					throw new RuntimeException("Não foi possível localizar as anotações de header e footer");
				}
		}else {
			throw new RuntimeException("Não foi possível localizar um header e um footer ");
		}
	}

	private void processTraillerTransaction(String line, Class<?> entry) {
		try {
			traillerTransactions.put(master, parseStrToRecord(entry, line));
			headerTransactions.put(master, headerTransaction);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());

		}
	
	}

	private RecordAction getCurrentRecorAction(Class<?> entry) {
		return extractedAnnotation(entry).type();
	}

	private void finallyRegister() {
		if (master != null && details.size() > 0) {
			masterDetailMultiRecod.put(master, details);
		} else if (master != null) {
			masterDetailMultiRecod.put(master, new ArrayList<>());
		}
		master = null;
		details = new ArrayList<>();
	}

	
	private void finallyRegisterMasterDetail() {
		if (masterdetail != null && subdetails.size() > 0) {
			masterDetailSubDetail.put(masterdetail, subdetails);
			details.add(masterDetailSubDetail);
		} else if (masterdetail != null) {
			masterDetailSubDetail.put(masterdetail, new ArrayList<>());
			details.add(masterDetailSubDetail);
		}
		masterdetail = null;
		subdetails = new ArrayList<>();
		masterDetailSubDetail = new LinkedHashMap<>();
	}
	
	private void processHeaderTransaction(String line, Class<?> clazz) {
		
		try {
			headerTransaction = parseStrToRecord(clazz, line);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			new RuntimeException();
		}
		
	}

	private void processDetail(String line, Class<?> clazz) {
		try {
			details.add(parseStrToRecord(clazz, line));
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processMaster(String line, Class<?> clazz) {
		try {
			master = parseStrToRecord(clazz, line);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Class<?> checkRegisterType(String line) {
		try {
			return fluent.getMapper().stream()
					.filter(a ->  extractedAnnotation(a)!= null && findByToken(extractedAnnotation(a), line ))
					.findFirst().orElse(null);
			
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return null;
		}

	}

	private Seletor extractedAnnotation(Class<?> klass) {
		if (klass.isAnnotationPresent(Seletor.class)) {
			return klass.getAnnotation(Seletor.class);
		} else {
			return null;
		}
	}

	public Map<Object, List<?>> readFile(String fileName) throws IOException {
		Map<Object, List<?>> result;
		FileReader fr = null;
		try {
			fr = new FileReader(new File(fileName));
			result = readStream(fr);
		} finally {
			if (fr != null) {
				fr.close();
			}
		}
		return result;
	}
	
	
	public Stream<Entry<Object, List<?>>> readFileAsAStreamApi(String fileName) throws IOException {
		Map<Object, List<?>> result;
		FileReader fr = null;
		try {
			fr = new FileReader(new File(fileName));
			result = readStream(fr);
		} finally {
			if (fr != null) {
				fr.close();
			}
		}
		return result.entrySet().stream();
	}

	public Map<Object, List<?>> readResource(String fileName) throws IOException {
		Map<Object, List<?>> result;
		Reader r = null;
		try {
			r = new InputStreamReader(getClass().getResourceAsStream(fileName));
			result = readStream(r);
		} finally {
			if (r != null) {
				r.close();
			}
		}
		return result;
	}

	public void writeFile(String fileName, Map<Object, List<?>> records, int maxRecords) throws IOException {
		try {
			createFile(fileName);
			if(header != null) {
				writeLine(header.getClass(), header);
			}
				writeStream(records, maxRecords);
			if(footer != null) {
				writeLine(footer.getClass(), footer);
			}
			} finally {
			closeFile();
		}
	}

	public void createFile(String fileName) throws IOException {
		File file = new File(fileName);
		if(!file.exists()) {
			file.createNewFile();
		}
		fw = new FileWriter(file);
		writer = new BufferedWriter(fw);
		
}

	public <T> void writeLine(Class<?> clazz, T obj) {
		try {
			writer.write(parseRecordToStr(obj, clazz) + StringHelper.NEW_LINE);
			writer.flush();
			
		} catch (IOException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	private void writeStream(Map<Object, List<?>> records, int maxRecords) throws IOException {;
	records.forEach((master, details) -> {
			try {
				writeLine(master.getClass(), master);
				if(details != null) {
				details.forEach(detail -> {
					try {
						if(detail instanceof LinkedHashMap ) {
							writeStream((LinkedHashMap) detail, maxRecords);
						}else {
							writeLine(detail.getClass(), detail);
						}
					} catch (IllegalArgumentException | IOException  e) {
						e.printStackTrace();
					}					
				});
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		});
	}

	private void closeFile() throws IOException {
		if (fw != null) {
			fw.flush();
			fw.close();
		}
	}

	public <T> void setBeforeReadRecordHandler(BeforeReadRecordHandler<T> beforeReadRecordHandler) {
		this.beforeReadRecordHandler = beforeReadRecordHandler;
	}

	public <T> void setAfterReadRecordHandler(AfterReadRecordHandler<T> afterReadRecordHandler) {
		this.afterReadRecordHandler = afterReadRecordHandler;
	}

	public <T> void setBeforeWriteRecordHandler(BeforeWriteRecordHandler<T> beforeWriteRecordHandler) {
		this.beforeWriteRecordHandler = beforeWriteRecordHandler;
	}

	public <T> void setAfterWriteRecordHandler(AfterWriteRecordHandler<T> afterWriteRecordHandler) {
		this.afterWriteRecordHandler = afterWriteRecordHandler;
	}

	private <T> boolean onBeforeReadRecord(BeforeReadRecordEventArgs<T> e) {
		return false;
	}

	@SuppressWarnings("unchecked")
	private <T> boolean onAfterReadRecord(String line, T record) {
		return false;
	}

	@SuppressWarnings("unchecked")
	private <T> boolean onBeforeWriteRecord(T obj, RecordInfo recordInfo) {
		return false;
	}

	private <T> String onAfterWriteRecord(String line, T record) {
		return line;
	}

	public <T> T parseStrToRecord(Class<T> clazz, String text) throws InstantiationException, IllegalAccessException {
		return new RecordInfo<T>(clazz).strToRecord(new LineInfo(text));
	}

	public <T> String parseRecordToStr(T obj, Class<?> clazz)
			throws IllegalArgumentException, IllegalAccessException {
		RecordInfo<?> recordInfo = new RecordInfo<>(clazz);
		onBeforeWriteRecord(obj, recordInfo);
		return recordInfo.recordToStr(obj);
	}

	public Object getFooter() {
		return footer;
	}

	public void setFooter(Object footer) {
		this.footer = footer;
	}

	public Object getHeader() {
		return header;
	}

	public void setHeader(Object header) {
		this.header = header;
	}

	
	
	public <T> T getTraillerTransaction(Object master, Class<T> klass) {
		return  (T) traillerTransactions.get(master);
	}
	public <T> T getHeaderTransaction(Object master, Class<T> klass) {
		return  (T) headerTransactions.get(master);
	}
	
	
}
