package com.by.petrfeldsherov.indprogr.model;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.by.petrfeldsherov.indprogr.parsing.AlgExprRegex;
import com.by.petrfeldsherov.indprogr.ui.FormatType;

public class Expression {
    private String expression;
    private String valuesFormatedString;
    private HashMap<Character, Double> values;
    private Validator validator;
    private Calculator calculator;

    public static String toConditionNode(Expression expr, FormatType srcFormat) throws UnsupportedOperationException {
	throw new UnsupportedOperationException("The method toConditionNode is not yet implemented.");
    }

    public static String toResultNode(Expression expr, FormatType destFormat) throws UnsupportedOperationException {
	throw new UnsupportedOperationException("The method toResultsNode is not yet implemented.");
    }

    public Expression(String expression, String valuesFormatedString) {
	this.expression = expression;
	this.valuesFormatedString = valuesFormatedString;

	setValues(replaceWhitespace(valuesFormatedString));

	String noWhitespaceExpression = replaceWhitespace(expression);
	validator = new Validator(noWhitespaceExpression, values);
	if (isValid()) {
	    calculator = new Calculator(noWhitespaceExpression, values);
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
	return calculator.getCalculationResult();
    }

    private void setValues(String valuesFormatedString) {
	values = new HashMap<Character, Double>();
	Pattern pattern = Pattern.compile(AlgExprRegex.VALUE.getRegex());
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
