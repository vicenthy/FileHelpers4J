/*
 * RecordInfo.java
 *
 * Copyright (C) 2007 Felipe Gon�alves Coury <felipe.coury@gmail.com>
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

package com.github.vicenthy.filehelpers4j.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.github.vicenthy.filehelpers4j.annotations.FieldNullValue;
import com.github.vicenthy.filehelpers4j.annotations.FixedLengthRecord;
import com.github.vicenthy.filehelpers4j.annotations.IgnoreCommentedLines;
import com.github.vicenthy.filehelpers4j.annotations.IgnoreEmptyLines;
import com.github.vicenthy.filehelpers4j.annotations.IgnoreFirst;
import com.github.vicenthy.filehelpers4j.annotations.IgnoreLast;
import com.github.vicenthy.filehelpers4j.annotations.Seletor;
import com.github.vicenthy.filehelpers4j.engines.LineInfo;
import com.github.vicenthy.filehelpers4j.enums.RecordCondition;
import com.github.vicenthy.filehelpers4j.fields.FieldBase;
import com.github.vicenthy.filehelpers4j.fields.FieldFactory;
import com.github.vicenthy.filehelpers4j.fields.FixedLengthField;
import com.github.vicenthy.filehelpers4j.helpers.ConditionHelper;
import com.github.vicenthy.filehelpers4j.helpers.StringHelper;
import com.github.vicenthy.filehelpers4j.interfaces.NotifyRead;
import com.github.vicenthy.filehelpers4j.interfaces.NotifyWrite;


public final class RecordInfo<T> {
	private FieldBase[] fields;
	private final Class<T> recordClass;
	private Constructor<T> recordConstructor;

	private int ignoreFirst = 0;
	private int ignoreLast = 0;
	private boolean ignoreEmptyLines = false;
	private boolean ignoreEmptySpaces = false;
	private String commentMarker = null;
	private boolean commentAnyPlace = true;
	private RecordCondition recordCondition = RecordCondition.None;
	private String recordConditionSelector = "";

	private boolean notifyRead = false;
	private boolean notifyWrite = false;
	private String conditionRegEx = null;
	
	private int sizeHint = 32;
	
	//private ConverterBase converterProvider = null;

	private int fieldCount;
	
	public RecordInfo(Class<T> recordClass) {
		// this.recordObject = recordObject;
		this.recordClass = recordClass;
		initFields();
	}
	
	/**
	 * Parses a text line into a record object
	 * 
	 * @param line current text line extracted from file 
	 * @return parsed object
	 */
	public T strToRecord(LineInfo line) {
		if (mustIgnoreLine(line.getLineStr())) {
			return null;
		}

		Object[] values = new Object[fieldCount];

		// array that holds the fields values
		T record = null;
		try {
			for (int i = 0; i < fieldCount; i++) {
				values[i] = fields[i].extractValue(line);
			}
	
			record = createRecordObject();
			for (int i = 0; i < fieldCount; i++) {
				setInternalField(fields[i].getFieldInfo().getName(), record, values[i]);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Problems while reading field values from " + record + " object", e);
		}
		
		return record;
	}
	
	static Object getInternalField(final String fieldName, final Object target) {
	    Object value = AccessController
	            .doPrivileged(new PrivilegedAction<Object>() {
	                public Object run() {
	                    Object result = null;
	                    java.lang.reflect.Field field = null;
	                    try {
	                        field = target.getClass().getDeclaredField(
	                                fieldName);
	                        field.setAccessible(true);
	                        result = field.get(target);
	                    } catch (Exception e1) {
	                        return null;
	                    }
	                    return result;
	                }
	            });
	    return value;
	}	
	
	static void setInternalField(final String fieldName, final Object target, final Object value) {
	    AccessController.doPrivileged(new PrivilegedAction<Object>() {
	                public Object run() {
	                    Object result = null;
	                    java.lang.reflect.Field field = null;
	                    try {
	                        field = target.getClass().getDeclaredField(
	                                fieldName);
	                        field.setAccessible(true);
	                        field.set(target, value);
	                    } catch (Exception e1) {
	                        return null;
	                    }
	                    return result;
	                }
	            });
	}	
	
	/**
	 * Creates a string representation of the record object
	 * 
	 * @param record the record object
	 * @return string representation of the record object, respecting rules defined
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public <T> String recordToStr(T record) throws IllegalArgumentException, IllegalAccessException {
		StringBuffer sb = new StringBuffer(this.sizeHint);
		Object[] values = new Object[fieldCount];
		try {
			for (int i = 0; i < fieldCount; i++) {
					values[i] = getInternalField(fields[i].getFieldInfo().getName(), record);
				if(values[i] == null) {
					values[i] = verifyFieldNullValue(fields[i].getFieldInfo());
				}
			}

			for (int i = 0; i < fieldCount; i++) {
				fields[i].assignToString(sb, values[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return sb.toString();
	}
	
	
	
	
	
	/**
	 * Instantiates a new object of the record class type
	 * @return the newly instatiated object
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private T createRecordObject() throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
		try {
			return recordConstructor.newInstance();
		}
		catch (IllegalArgumentException e) {
			Object parameter = recordClass.getEnclosingClass().newInstance();
			return recordConstructor.newInstance(parameter);
		}
	}
	
	private Object verifyFieldNullValue(Field fieldInfo) {
		if(fieldInfo.isAnnotationPresent(FieldNullValue.class)) {
			return fieldInfo.getAnnotation(FieldNullValue.class).value();
		}
		return null;
	}

	
	public T convertObjectToGenertics(Object o) {
		return (T)o;
	}
		
	/**
	 * Verifies if current line should be ignored
	 * 
	 * @param line line to be examined
	 * @return true or false indicating if passed line should be ignored
	 */
	private boolean mustIgnoreLine(String line) {
		if (ignoreEmptyLines) {
			if ((ignoreEmptySpaces && line.trim().length() == 0) || line.length() == 0) {
				return true;
			}
		}
		
		if (commentMarker != null && commentMarker.length() > 0) {
			if ((commentAnyPlace && line.trim().startsWith(commentMarker)) || line.startsWith(commentMarker)) {
				return true;
			}
		}
		
		switch (recordCondition) {
		case ExcludeIfBegins:
			return ConditionHelper.beginsWith(line, recordConditionSelector);
			
		case IncludeIfBegins:
			return !ConditionHelper.beginsWith(line, recordConditionSelector);
			
		case ExcludeIfContains:
			return ConditionHelper.contains(line, recordConditionSelector);
			
		case IncludeIfContains:
			return !ConditionHelper.contains(line, recordConditionSelector);
			
		case ExcludeIfEnclosed:
			return ConditionHelper.enclosed(line, recordConditionSelector);
			
		case IncludeIfEnclosed:
			return !ConditionHelper.enclosed(line, recordConditionSelector);
			
		case ExcludeIfEnds:
			return ConditionHelper.endsWith(line, recordConditionSelector);
			
		case IncludeIfEnds:
			return !ConditionHelper.endsWith(line, recordConditionSelector);
			
		case ExcludeIfMatchRegex:
			return Pattern.matches(conditionRegEx, line);
			
		case IncludeIfMatchRegex:
			return !Pattern.matches(conditionRegEx, line);
			
		}
		
		return false;
	}

	/**
	 * Initiate the values of member fields by looking for annotations on the
	 * record object that changes behavior
	 */
	private void initFields() {
		IgnoreFirst igf = recordClass.getAnnotation(IgnoreFirst.class);
		if (igf != null) {
			this.ignoreFirst = igf.lines();
		}
		
		IgnoreLast igl = recordClass.getAnnotation(IgnoreLast.class);
		if (igl != null) {
			this.ignoreLast = igl.lines();
		}
		
		this.ignoreEmptyLines = recordClass.isAnnotationPresent(IgnoreEmptyLines.class);
		
		IgnoreCommentedLines igc = recordClass.getAnnotation(IgnoreCommentedLines.class);
		if (igc != null) {
			this.commentMarker = igc.commentMarker();
			this.commentAnyPlace = igc.anyPlace();
		}
		
		// TODO ConditionalRecord

		if(NotifyRead.class.isAssignableFrom(recordClass))
			notifyRead = true;

		if(NotifyWrite.class.isAssignableFrom(recordClass))
			notifyWrite = true;
		
		try {
			recordConstructor = recordClass.getConstructor();
		} catch (SecurityException e) {
			throw new RuntimeException(
					"The class " + recordClass.getName() + 
					" needs to be accessible to be used");
		} catch (NoSuchMethodException e) {
			boolean throwIt = true;

			try {
				if (recordClass.getEnclosingClass() != null) {
					recordConstructor = recordClass.getConstructor(recordClass.getEnclosingClass());
				}
				throwIt = false;
			}
			catch (NoSuchMethodException e1) {
			}

			if (throwIt) {
				throw new RuntimeException(
						"The class " + recordClass.getName() + 
						" needs to have an empty constructor to be used");
			}
		}
		
		fields = createCoreFields(recordClass.getDeclaredFields(), recordClass);
		fieldCount = this.fields.length;
		
		if (isFixedLength()) {
			sizeHint = 0;
			for (int i = 0; i < fieldCount; i++) {
				sizeHint += ((FixedLengthField) fields[i]).getFieldLength();
			}
		}
		
		if (fieldCount == 0) {
			throw new IllegalArgumentException("The record class " + recordClass.getName() + " don't contains any field.");
		}
	}

	/**
	 * Indicates if this record is of fixed length
	 * @return true if record is fixed length, false otherwise
	 */
	private boolean isFixedLength() {
		return recordClass.isAnnotationPresent(FixedLengthRecord.class);
	}
	
	/**
	 * Creates field descriptors for each field of the record class 
	 * 
	 * @param fields current fields of the record class, acquired via reflection
	 * @param recordClass the record class
	 * @return an array of FieldBase, field descriptor objects
	 */
	@SuppressWarnings("unchecked")
	private static FieldBase[] createCoreFields(Field[] fields, Class recordClass) {
		FieldBase field;
		List<FieldBase> fieldArr = new ArrayList<FieldBase>();
		
		boolean someOptional = false;
		for (Field f : fields) {
			field = FieldFactory.createField(f, recordClass, someOptional);
			if (field != null) {
				someOptional = field.isOptional();
				fieldArr.add(field);
				if (fieldArr.size() > 1) {
					fieldArr.get(fieldArr.size() - 2).setNextOptional(fieldArr.get(fieldArr.size() - 1).isOptional());
				}
			}
		}
		
		if (fieldArr.size() > 0) {
			fieldArr.get(0).setFirst(true);
			fieldArr.get(fieldArr.size() - 1).setLast(true);
		}
		
		return fieldArr.toArray(new FieldBase[] {});
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return StringHelper.toStringBuilder(this);
	}

	public FieldBase[] getFields() {
		return fields;
	}

	public void setFields(FieldBase[] fields) {
		this.fields = fields;
	}

	public int getIgnoreFirst() {
		return ignoreFirst;
	}

	public void setIgnoreFirst(int ignoreFirst) {
		this.ignoreFirst = ignoreFirst;
	}

	public int getIgnoreLast() {
		return ignoreLast;
	}

	public void setIgnoreLast(int ignoreLast) {
		this.ignoreLast = ignoreLast;
	}

	public boolean isIgnoreEmptyLines() {
		return ignoreEmptyLines;
	}

	public void setIgnoreEmptyLines(boolean ignoreEmptyLines) {
		this.ignoreEmptyLines = ignoreEmptyLines;
	}

	public boolean isIgnoreEmptySpaces() {
		return ignoreEmptySpaces;
	}

	public void setIgnoreEmptySpaces(boolean ignoreEmptySpaces) {
		this.ignoreEmptySpaces = ignoreEmptySpaces;
	}

	public String getCommentMarker() {
		return commentMarker;
	}

	public void setCommentMarker(String commentMaker) {
		this.commentMarker = commentMaker;
	}

	public boolean isCommentAnyPlace() {
		return commentAnyPlace;
	}

	public void setCommentAnyPlace(boolean commentAnyPlace) {
		this.commentAnyPlace = commentAnyPlace;
	}

	public RecordCondition getRecordCondition() {
		return recordCondition;
	}

	public void setRecordCondition(RecordCondition recordCondition) {
		this.recordCondition = recordCondition;
	}

	public String getRecordConditionSelector() {
		return recordConditionSelector;
	}

	public void setRecordConditionSelector(String recordConditionSelector) {
		this.recordConditionSelector = recordConditionSelector;
	}

	public boolean isNotifyRead() {
		return notifyRead;
	}

	public void setNotifyRead(boolean notifyRead) {
		this.notifyRead = notifyRead;
	}

	public boolean isNotifyWrite() {
		return notifyWrite;
	}

	public void setNotifyWrite(boolean notifyWrite) {
		this.notifyWrite = notifyWrite;
	}

	public String getConditionRegEx() {
		return conditionRegEx;
	}

	public void setConditionRegEx(String conditionRegEx) {
		this.conditionRegEx = conditionRegEx;
	}

	public int getFieldCount() {
		return fieldCount;
	}

	public void setFieldCount(int fieldCount) {
		this.fieldCount = fieldCount;
	}

	public Constructor<T> getRecordConstructor() {
		return recordConstructor;
	}

	public void setRecordConstructor(Constructor<T> recordConstructor) {
		this.recordConstructor = recordConstructor;
	}
}
