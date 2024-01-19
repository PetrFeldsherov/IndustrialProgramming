package com.by.petrfeldsherov.indprogr.parsing;

public enum AlgExprRegex {
    DOUBLE("(-?(0|([1-9][0-9]*))(\\.[0-9]+)?)"), POSITIVE_DOUBLE("(0|([1-9][0-9]*))(\\.[0-9]+)?"),
    AlGEXPR_ALPHABET("\\s*(([+\\-\\/*^()a-z0-9]|" + DOUBLE.str() + ")\\s*)+"),
    VALUE("\\s*([a-z])\\s*(=)\\s*(" + DOUBLE.str() + ")\\s*(;)\\s*"), VALUES("(" + VALUE.str() + ")*"),
    VARIABLE("[a-z]");

    private String regex;

    private AlgExprRegex(String regex) {
	this.regex = regex;
    }

    public String str() {
	return regex;
    }

}
