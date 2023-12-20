package com.by.petrfeldsherov.indprogr.parsing;

public enum Tag {

    MATH_NODE("math"), EXPRESSION_PART("expression"), VALUES_PART("values");

    private String tagName;

    private Tag(String tagName) {
	this.tagName = tagName;
    }

    public String getTagName() {
	return tagName;
    }

}
