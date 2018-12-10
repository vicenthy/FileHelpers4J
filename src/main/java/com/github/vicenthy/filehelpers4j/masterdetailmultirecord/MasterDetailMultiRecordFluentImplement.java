/*
 * MasterDetailMultiRecordFluentImplement.java
 *
 * Copyright (C) 2017 Atila Augusto dos Santos - <atila.sistemas@gmail.com>
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MasterDetailMultiRecordFluentImplement implements MasterDetailMultiRecordFluent, Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3660496374250929580L;
	private List<Class<?>> mapper;
	private Class<?> headerFile;
	private Class<?> footerFile;
	
	
	public MasterDetailMultiRecordFluentImplement() {
		mapper = new ArrayList<>();
	}
	
	public List<Class<?>> getMapper() {
		return mapper;
	}


	@Override
	public <T> MasterDetailMultiRecordFluent addMapperFile(Class<T> clazz) {
		mapper.add(clazz);
		return this;
	}

	
	@Override
	public void setMapper(List<Class<?>> mapper) {
		this.mapper = mapper;
	}

	public Class<?> getHeaderFile() {
		return headerFile;
	}

	public void setHeaderFile(Class<?> headerFile) {
		this.headerFile = headerFile;
	}

	public Class<?> getFooterFile() {
		return footerFile;
	}

	public void setFooterFile(Class<?> footerFile) {
		this.footerFile = footerFile;
	}

	
	
	
}
