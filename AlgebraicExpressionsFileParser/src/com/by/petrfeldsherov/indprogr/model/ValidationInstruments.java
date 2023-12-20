package com.by.petrfeldsherov.indprogr.model;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

import com.by.petrfeldsherov.indprogr.parsing.AlgExprRegex;

public class ValidationInstruments {
    
    private static final String CONSTANTS_REPLACEMENT = "X";
    
    public static boolean wrongBrackets(String expr) {
	Stack<Character> bracketsStack = new Stack<Character>();
	for (int i = 0; i < expr.length(); i++) {
	    Character c = expr.charAt(i);

	    if (isOpeningBracket(c)) {
		if (isClosingBracket(expr.charAt(i + 1))) {
		    return true;
		}
		bracketsStack.push(c);
	    } else if (isClosingBracket(c)) {
		if (bracketsStack.isEmpty() || !isOpeningBracket(bracketsStack.peek())) {
		    return true;
		}
		bracketsStack.pop();
	    }
	}

	return !bracketsStack.isEmpty();
    }

    public static boolean wrongOperatorBalance(String expr) {
	if (expr.length() == 1) {
	    return !isVariable(expr.charAt(0));
	}
	
	Stack<AlgExprItem> typeStack = new Stack<AlgExprItem>();
	boolean prevIsOperand = false;
	for (int i = 0; i < expr.length(); i++) {
	    Character c = expr.charAt(i);
	    if (isVariable(c) || c.equals('0')) {
		if (prevIsOperand) {
		    return true;
		}
		prevIsOperand = true;
		typeStack.push(AlgExprItem.OPERAND);
	    } else if (isOpeningBracket(c)) {
		if (prevIsOperand) {
		    return true;
		}
		int closingBracketPos = getClosingBracketPos(new StringBuilder(expr), i);
		if (wrongOperatorBalance(expr.substring(i + 1, closingBracketPos))) {
		    return true;
		}
		prevIsOperand = true;
		typeStack.push(AlgExprItem.OPERAND);

		i = closingBracketPos;
	    } else { // not operand, not bracket -> operator
		if (i == 0) {
		    return true;
		} else if (!prevIsOperand) {
		    return true;
		}
		prevIsOperand = false;
		typeStack.push(AlgExprItem.OPERATOR);
	    }
	}

	if (typeStack.peek() != AlgExprItem.OPERAND) {
	    return true;
	}
	return false;
    }

    public static StringBuilder removeUnary(StringBuilder sb) {
	if (sb.length() == 1) {
	    return sb;
	}
	
	char firstPosChar = sb.charAt(0);
	char secPosChar = sb.charAt(1);
	if (firstPosChar == '+' && (isVariable(secPosChar) || secPosChar == '(')) {
	    sb.deleteCharAt(0);
	} else if (firstPosChar == '-' && isVariable(secPosChar)) {
	    sb.replace(0, 2, "(0-" + secPosChar + ")");
	} else if (firstPosChar == '-' && secPosChar == '(') {
	    int closingBracketPos = getClosingBracketPos(sb, 1);
	    sb.replace(0, closingBracketPos, "(0" + sb.substring(0, closingBracketPos) + ")");
	}

	for (int i = 0; i < sb.length(); i++) {
	    if (sb.charAt(i) == '(') {
		int closingBracketPos = getClosingBracketPos(sb, i);
		StringBuilder lowerLevelSb = new StringBuilder(sb.substring(i + 1, closingBracketPos));
		sb.replace(i + 1, closingBracketPos, removeUnary(lowerLevelSb).toString());
	    }
	}

	return sb;
    }

    public static String replaceConstants(String expr) {
	return expr.replaceAll(AlgExprRegex.POSITIVE_DOUBLE.getRegex(), CONSTANTS_REPLACEMENT);
    }

    public static int getClosingBracketPos(StringBuilder sb, int i) {
	Deque<Character> bracketsStack = new LinkedList<Character>();
	bracketsStack.push(sb.charAt(i));
	while (i++ < sb.length()) {
	    char c = sb.charAt(i);
	    if (isOpeningBracket(c)) {
		bracketsStack.push(c);
	    } else if (isClosingBracket(c)) {
		bracketsStack.pop();
		if (bracketsStack.isEmpty()) {
		    return i;
		}
	    }
	}
	return -1;
    }

    public static boolean isOpeningBracket(Character c) {
	return c.equals('(');
    }

    public static boolean isClosingBracket(Character c) {
	return c.equals(')');
    }

    public static boolean isVariable(Character c) {
	return c.toString().matches(AlgExprRegex.VARIABLE.getRegex()) || c.toString().equals(CONSTANTS_REPLACEMENT);
    }

    public static boolean isDouble(String s) {
	return s.matches(AlgExprRegex.DOUBLE.getRegex());
    }

}
