package com.by.petrfeldsherov.indprogr.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Calculator {

    private Double calculationResult = null;
    private String postfixExpressionForm;
    Map<Character, Double> valuesOfVariables;
    @SuppressWarnings("serial")
    Map<Character, Integer> operatorPriorities = new HashMap<Character, Integer>() {
	{
	    put('(', 0);
	    put('+', 1);
	    put('-', 1);
	    put('*', 2);
	    put('/', 2);
	    put('^', 3);
	    put('~', 4);
	}
    };

    public Calculator(String infixExpressionForm, Map<Character, Double> valuesOfVariables) {
	postfixExpressionForm = toPostfixForm(infixExpressionForm);
	this.valuesOfVariables = valuesOfVariables;
	calculate();
    }

    public Double getCalculationResult() {
        return calculationResult;
    }

    private String toPostfixForm(String infixExpressionForm) {
        StringBuilder postfixForm = new StringBuilder();
        Stack<Character> stack = new Stack<Character>();
    
        for (int i = 0; i < infixExpressionForm.length(); i++) {
            Character c = infixExpressionForm.charAt(i);
    
            if (Character.isDigit(c)) {
        	String parsedDouble = getDoubleStr(infixExpressionForm, i);
        	postfixForm.append(parsedDouble + " ");
        	i += parsedDouble.length() - 1;
            } else if (isVariable(c)) {
        	postfixForm.append(c);
            } else if (c.equals('(')) {
        	stack.push(c);
            } else if (c.equals(')')) {
        	while (!stack.isEmpty() && !stack.peek().equals('(')) {
        	    postfixForm.append(stack.pop());
        	}
        	stack.pop();
            } else if (isOperator(c)) {
        	Character op = c;
    
        	if (isUnary(op, infixExpressionForm, i)) {
        	    op = '~';
        	}
    
        	while (!stack.isEmpty() && operatorPriorities.get(stack.peek()) >= operatorPriorities.get(op)) {
        	    postfixForm.append(stack.pop());
        	}
        	stack.push(op);
            }
        }
    
        while (!stack.isEmpty()) {
            postfixForm.append(stack.pop());
        }
    
        return postfixForm.toString();
    }

    private void calculate() {
	Stack<Double> values = new Stack<Double>();
	int operationsCounter = 0;

	for (int i = 0; i < postfixExpressionForm.length(); i++) {
	    Character c = postfixExpressionForm.charAt(i);

	    if (Character.isDigit(c)) {
		String parsedDouble = getDoubleStr(postfixExpressionForm, i);
		values.push(Double.parseDouble(parsedDouble));
		i += parsedDouble.length();
	    } else if (isVariable(c)) {
		values.push(valuesOfVariables.get(c));
	    } else if (isOperator(c)) {
		operationsCounter++;

		if (c.equals('~')) {
		    double last = getNotEmpty(values);
		    values.push(getOperationResult('-', 0, last));
		    continue;
		}

		double second = getNotEmpty(values);
		double first = getNotEmpty(values);
		values.push(getOperationResult(c, first, second));
		
//		System.out.println(operationsCounter + ") " + first + " " + c + " " + second + " = " + values.peek());
	    }
	}

	calculationResult = values.pop();
    }

    private String getDoubleStr(String infixExpressionForm, int i) {
	StringBuilder doubleStr = new StringBuilder();
	boolean pointPassed = false;
	while (i < infixExpressionForm.length()) {
	    Character c = infixExpressionForm.charAt(i);

	    if (Character.isDigit(c)) {
		doubleStr.append(c);
	    } else if (c.equals('.')) {
		if (pointPassed) {
		    throw new IllegalArgumentException(
			    "Unexpected second point on position " + i + ". Cannot parse double.");
		}
		pointPassed = true;
		doubleStr.append(c);
	    } else {
		break;
	    }
	    
	    i++;
	}

	return doubleStr.toString();
    }

    private Double getOperationResult(char c, double first, double second) {
	switch (c) {
	case '+':
	    return first + second;
	case '-':
	    return first - second;
	case '*':
	    return first * second;
	case '/':
	    if (second == 0) {
		throw new ArithmeticException("Devision by zero.");
	    }
	    return first / second;
	case '^':
	    return Math.pow(first, second);
	default:
	    return 0.0; // throw new IllegalArgumentException("Invalid operator provided.");
	}
    }

    private double getNotEmpty(Stack<Double> values) {
	return !values.isEmpty() ? values.pop() : 0;
    }

    private boolean isUnary(Character c, String infixExpressionForm, int i) {
	return c.equals('-')
		&& (i == 0 || (i > 1 && operatorPriorities.containsKey(infixExpressionForm.charAt(i - 1))));
    }

    private boolean isOperator(Character c) {
	return operatorPriorities.containsKey(c);
    }

    private boolean isVariable(Character c) {
	return Character.isAlphabetic(c) && Character.isLowerCase(c);
    }

}
