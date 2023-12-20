package com.by.petrfeldsherov.indprogr.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import static com.by.petrfeldsherov.indprogr.model.ValidationInstruments.*;

public class Validator {
    private Boolean validationResult = null;
    private String expression;
    private Map<Character, Double> valuesOfVariables;
    private Set<Character> expressionVariables;

    public Validator(String expression, HashMap<Character, Double> valuesOfVariables) {
	this.expression = expression;
	this.valuesOfVariables = valuesOfVariables;
	setExpressionVariables();
	validate();
    }

    public Boolean isValid() {
	return validationResult;
    }

    private void setExpressionVariables() {
	expressionVariables = new HashSet<Character>();
	for (Character c : expression.toCharArray()) {
	    if (isVariable(c)) {
		expressionVariables.add(c);
	    }
	}
    }

    private void validate() {
	if (expression.length() == 1 && isVariable(expression.charAt(0))) {
	    validationResult = enaughValues();
	    return;
	} else if (isDouble(expression)) {
	    validationResult = true;
	    return;
	}

	expression = replaceConstants(expression);
	validationResult = enaughValues() && !wrongBrackets(expression)
		&& !wrongOperatorBalance(removeUnary(new StringBuilder(expression)).toString());
    }

    private Boolean enaughValues() {
	for (Character c : expressionVariables) {
	    if (!valueProvided(c)) {
		return false;
	    }
	}
	return true;
    }

    private boolean valueProvided(Character c) {
	for (Map.Entry<Character, Double> entry : valuesOfVariables.entrySet()) {
	    if (entry.getKey().equals(c)) {
		return true;
	    }
	}
	return false;
    }
}
