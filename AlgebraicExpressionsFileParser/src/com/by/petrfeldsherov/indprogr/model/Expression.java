package com.by.petrfeldsherov.indprogr.model;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.by.petrfeldsherov.indprogr.parsing.AlgExprRegex;
import com.by.petrfeldsherov.indprogr.parsing.Tag;
import com.by.petrfeldsherov.indprogr.ui.FormatType;

public class Expression {
    private String expression;
    private String valuesFormatedString;
    private Double result;
    private HashMap<Character, Double> values;
    private Validator validator;
    private Calculator calculator;

    public Expression(String expression, String valuesFormatedString) {
	this.expression = expression;
	this.valuesFormatedString = valuesFormatedString;

	setValues(replaceWhitespace(valuesFormatedString));

	String noWhitespaceExpression = replaceWhitespace(expression);
	validator = new Validator(noWhitespaceExpression, values);
	if (isValid()) {
	    calculator = new Calculator(noWhitespaceExpression, values);
	    result = calculator.getCalculationResult();
	}
    }

    public String getExpression() {
	return expression;
    }

    public String getValuesFormatedString() {
	return valuesFormatedString;
    }

    public Boolean isValid() {
	return validator.isValid();
    }

    public Double getResult() throws NullPointerException {
	if (!isValid()) {
	    throw new NullPointerException("Expression is invalid, no calculations were done.");
	}
	return result;
    }
    
    public String getSrcRegex(FormatType srcFormat) {
	if (srcFormat == FormatType.TXT) {
	    return Pattern.quote(expression) + "\\s*;\\s*" + Pattern.quote(valuesFormatedString) + "\\s*";
	} else if (srcFormat == FormatType.XML) {
	    return "<" + Tag.MATH.str() + ">\\s*<" + Tag.EXPR.str() + ">" + Pattern.quote(expression) + ";<\\/" + Tag.EXPR.str() + ">\\s*<"
		    + Tag.VAL.str() + ">" + Pattern.quote(valuesFormatedString) + "<\\/" + Tag.VAL.str() + ">\\s*<\\/" + Tag.MATH.str() + ">";
	} else {
	    return "\\{\\s*\"" + Tag.EXPR.str() + "\"\\s*:\\s*\"" + Pattern.quote(expression) + ";\",\\s*\"" + Tag.VAL.str() + "\"\\s*:\\s*\"" + Pattern.quote(valuesFormatedString) + "\"\\s*\\}";
	}
    }
    
    public static String getSrcRegexGeneral(FormatType srcFormat) {
	if (srcFormat == FormatType.TXT) {
	    return "^" + AlgExprRegex.AlGEXPR_ALPHABET.str() + ";" + AlgExprRegex.VALUES.str() + "$";
	} else if (srcFormat == FormatType.XML) {
	    return "<" + Tag.MATH.str() + ">\\s*<" + Tag.EXPR.str() + ">.*</" + Tag.EXPR.str() + ">\\s*<"
		    + Tag.VAL.str() + ">.*</" + Tag.VAL.str() + ">\\s*</" + Tag.MATH.str() + ">";
	} else {
	    return "{\\s*\"" + Tag.EXPR.str() + "\"\\s*:\\s*\".*\",\\s*\"" + Tag.VAL.str() + "\"\\s*:\\s*\".*\"\\s*}";
	}
    }

    public String getResultNodeStr(FormatType destFormat) {
	if (destFormat == FormatType.TXT) {
	    return " # " + Tag.RES.str() + ": " + getResult() + "\n";
	} else if (destFormat == FormatType.XML) {
	    return "<" + Tag.MATH.str() + ">\n\t<" + Tag.RES.str() + ">" + getResult() + "</" + Tag.RES.str() + ">\n</"
		    + Tag.MATH.str() + ">";
	} else {
	    return "{\"" + Tag.RES.str() + "\":\"" + getResult() + "\"}";
	}
    }

    private void setValues(String valuesFormatedString) {
	values = new HashMap<Character, Double>();
	Pattern pattern = Pattern.compile(AlgExprRegex.VALUE.str());
	Matcher matcher = pattern.matcher(valuesFormatedString);
	int lastMatchPos = 0;
	while (matcher.find()) {
	    values.put(matcher.group(1).charAt(0), Double.parseDouble(matcher.group(3)));
	    lastMatchPos = matcher.end();
	}
	if (lastMatchPos != valuesFormatedString.length())
	    System.out.println("Invalid string!");
    }

    private String replaceWhitespace(String s) {
	return s.replaceAll("\\s", "");
    }

}
