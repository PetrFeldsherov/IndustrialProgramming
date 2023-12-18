package com.by.petrfeldsherov.indprogr.calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AlgExprValidator {
    private String expression;
    private Map<Character, Double> valuesOfVariables;
    private Set<Character> variablesInExprission;

    public AlgExprValidator(String expression, HashMap<Character, Double> valuesOfVariables) {
	this.expression = expression;
	this.valuesOfVariables = valuesOfVariables;
	setExpressionVariables();
    }

    public Boolean isValid() {
	throw new UnsupportedOperationException("The method is not yet implemented.");
	// TODO go throw expression and add [a-z] characters to set: check variable if
	// variable check return enaugh, else if number return true, else if not trivial
	// do two preparations and return enaugh values (hashmapentry cycle for every
	// item in variables set), !wrongBrackets, !wrongOperatorBalance - RPNzu
    }

    private void setExpressionVariables() {
	throw new UnsupportedOperationException("The method is not yet implemented.");
	// TODO new HashSet, go throw expression and add [a-z] characters to set
    }

}
