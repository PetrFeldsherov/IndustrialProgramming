package com.by.petrfeldsherov.indprogr.parsing;

public enum AlgExprRegex {
    DOUBLE("(-?(0|([1-9][0-9]*))(\\.[0-9]+)?)"), POSITIVE_DOUBLE("(0|([1-9][0-9]*))(\\.[0-9]+)?"),
    AlGEXPR_ALPHABET("\\s*((" + DOUBLE.getRegex() + "|[+\\-\\/*^()a-z0-9])\\s*)+"),
    VALUE("\\s*([a-z])\\s*(=)\\s*(" + DOUBLE.getRegex() + ")\\s*(;)\\s*"), VALUES("(" + VALUE.getRegex() + ")*"),
    VARIABLE("[a-z]");

    private String regex;

    private AlgExprRegex(String regex) {
	this.regex = regex;
    }

    public String getRegex() {
	return regex;
    }

}
