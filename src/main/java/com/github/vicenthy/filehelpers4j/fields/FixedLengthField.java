/*
 * FixedLengthField.java
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

package com.github.vicenthy.filehelpers4j.fields;

import java.lang.reflect.Field;

import com.github.vicenthy.filehelpers4j.core.ExtractedInfo;
import com.github.vicenthy.filehelpers4j.engines.LineInfo;
import com.github.vicenthy.filehelpers4j.enums.AlignMode;
import com.github.vicenthy.filehelpers4j.enums.FixedMode;
import com.github.vicenthy.filehelpers4j.exceptions.BadUsageException;
import com.github.vicenthy.filehelpers4j.exceptions.WrongLenthException;
import com.github.vicenthy.filehelpers4j.helpers.StringHelper;

public class FixedLengthField extends FieldBase {

	private final int fieldLength;
	private final FieldAlignBean align;
	private final FixedMode fixedMode;
	
	public FixedLengthField(Field fi, int length, FieldAlignBean align, FixedMode fixedMode) {
		super(fi);
		this.fieldLength = length;
		this.align = align;
		this.fixedMode = fixedMode;
	}
	
	@Override
	protected ExtractedInfo extractFieldString(LineInfo line) throws WrongLenthException, BadUsageException {
		if (line.getCurrentLength() == 0) {
			if (isOptional())
				return ExtractedInfo.Empty;
			else
				throw new BadUsageException(
						"End Of Line found processing the field: " + 
						getFieldInfo().getName() + " at line " + 
						line.getLineNumber() + ". " +
						"(You need to mark it as @FieldOptional if you want to avoid this exception) in Class " + getFieldInfo().getDeclaringClass().getSimpleName());
		}
		ExtractedInfo res;

		if (line.getCurrentLength() < this.fieldLength) {
			if (fixedMode == FixedMode.AllowLessChars || fixedMode == FixedMode.AllowVariableLength) {
				res = new ExtractedInfo(line);
			}
			else {
			throw new WrongLenthException(getFieldInfo() , line.getCurrentString(), fieldLength, line.getCurrentLength(), line.getLineNumber(), getFieldInfo().getName() );
			}			
		}else if (isLast() && line.getCurrentLength() > fieldLength && fixedMode != FixedMode.AllowMoreChars && fixedMode != FixedMode.AllowVariableLength) {
			throw new WrongLenthException(getFieldInfo(), line.getCurrentString(), fieldLength, line.getCurrentLength(), line.getLineNumber(), getFieldInfo().getName() );
		}else{
			res = new ExtractedInfo(line, line.getCurrentPos() + fieldLength);
		}

		return res;
	}

	@Override
	protected void createFieldString(StringBuffer sb, Object fieldValue) {
		String field = super.baseFieldString(fieldValue);
		if(field == null)
			field = "";

		field = StringHelper.trimBoth(field, StringHelper.WHITESPACE_CHARS);

		if (field.length() > fieldLength) {
			field = field.substring(0, fieldLength);
			//sb.Length = length + this.mFieldLength;
		}

		if (align.getAlign() == AlignMode.Left) {
			sb.append(field);
			for (int i = 0; i < fieldLength - field.length(); i++) {
				sb.append(align.getChar());
			}
		}
		else if (align.getAlign() == AlignMode.Right) {
			for (int i = 0; i < fieldLength - field.length(); i++) {
				sb.append(align.getChar());
			}
			sb.append(field);
		}
		else {
			int middle = (fieldLength - field.length()) / 2;

			for (int i = 0; i < middle; i++) {
				sb.append(align.getChar());
			}
			sb.append(field);
			for (int i = 0; i < fieldLength - field.length() - middle; i++) {
				sb.append(align.getChar());
			}
		}
	}

	public int getFieldLength() {
		return fieldLength;
	}

	public FieldAlignBean getAlign() {
		return align;
	}

	public FixedMode getFixedMode() {
		return fixedMode;
	}

}
