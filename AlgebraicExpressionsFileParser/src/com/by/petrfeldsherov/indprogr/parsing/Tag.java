package com.by.petrfeldsherov.indprogr.parsing;

public enum Tag {

    MATH("math"), EXPR("expression"), VAL("values"), RES("result");

    private String tagName;

    private Tag(String tagName) {
	this.tagName = tagName;
    }

    public String str() {
	return tagName;
    }

}
