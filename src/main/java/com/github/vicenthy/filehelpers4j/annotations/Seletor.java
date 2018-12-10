package com.github.vicenthy.filehelpers4j.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.vicenthy.filehelpers4j.masterdetail.CommonSelector;
import com.github.vicenthy.filehelpers4j.masterdetail.RecordAction;
import com.github.vicenthy.filehelpers4j.masterdetail.SelectorString;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Seletor {
	String token();
	RecordAction type();
	SelectorString seletorString() default SelectorString.Contains;
}
