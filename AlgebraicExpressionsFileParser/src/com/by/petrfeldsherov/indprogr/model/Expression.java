package com.by.petrfeldsherov.indprogr.calculator;

import java.util.HashMap;

import com.by.petrfeldsherov.indprogr.ui.FormatType;

public class AlgebraicExpression {
    private String expression;
    private String trimmedExpression;
    private String valuesFormatedString;
    private Double calculationResult = null;
    private HashMap<Character, Double> valuesOfVariables;
    private AlgExprValidator validator;
    private AlgExprCalculator calculator;

    public AlgebraicExpression(String expression, String valuesFormatedString) {
	this.expression = expression;
	trimmedExpression = expression.trim();
	this.valuesFormatedString = valuesFormatedString;
	setValuesOfVaribles();
    }

    public String getExpression() {
	return expression;
    }

    public Boolean isValid() {
	validator = new AlgExprValidator(expression, valuesOfVariables);
	return validator.isValid();
    }

    public Double getResult() {
	if (calculationResult == null) {
	    calculator = new AlgExprCalculator(expression, valuesOfVariables);
	    calculationResult = calculator.calculate();
	}
	return calculationResult;
    }

    private void setValuesOfVaribles() {
	throw new UnsupportedOperationException("The method is not yet implemented.");
	// TODO parse String with val=double; regex is ... , algorithm is match, check
	// unary +, get value, get double, make String with no symbols, if some
	// additional chacacters left in it - OddSymbols in values exception custom,
	// only name message and what is left required - throw invalid argument ot
	// something else doesn't matter
    }

    public String toConditionNode(FormatType srcFormat) {
	// TODO Auto-generated method stub
	return null;
    }

    public String toResultNode(FormatType srcFormat) {
	// TODO Auto-generated method stub
	return null;
    }

}
