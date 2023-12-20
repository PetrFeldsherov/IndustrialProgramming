package com.by.petrfeldsherov.indprogr.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static com.by.petrfeldsherov.indprogr.model.ValidationInstruments.*;

public class ValidationInstrumentsTest {

    private static final ArrayList<String> WRONG_OP_BALANCE_EXPRESSIONS = new ArrayList<String>(
	    Arrays.asList("a+-b", "--a", "-c+a-b+(+a)-", "aa+b", "a+(-p+)-c"));
    private static final ArrayList<String> CORRECT_OP_BALANCE_EXPRESSIONS = new ArrayList<String>(
	    Arrays.asList("a+b", "(a-b)+c/d-(d*b)", "a-a-a-a-a-a", "b+(b)+b"));
    private static final ArrayList<String> WRONG_BR_BALANCE_EXPRESSIONS = new ArrayList<String>(
	    Arrays.asList(")(d)(d)(d)(d)", "(d)()"));
    private static final ArrayList<String> CORRECT_BR_BALANCE_EXPRESSIONS = new ArrayList<String>(
	    Arrays.asList("(d)+(d)(((d)))", "((d+(d)+((d))))", "(((d+3)+2)+1)"));
    private static final ArrayList<String> RM_UNARY_BEFORE = new ArrayList<String>(
	    Arrays.asList("-a", "-(-a-(-b))", "b+(b)+b"));
    private static final ArrayList<String> RM_UNARY_CORRECT_OUTPUT = new ArrayList<String>(
	    Arrays.asList("(0-a)", "(0-((0-a)-((0-b))))", "b+(b)+b"));
    

    @Test
    public void testWrongBrackets() {
	for (String wrong : WRONG_BR_BALANCE_EXPRESSIONS) {
	    assertTrue(wrongBrackets(wrong));
	}
	for (String correct : CORRECT_BR_BALANCE_EXPRESSIONS) {
	    assertFalse(wrongBrackets(correct));
	}
    }

    @Test
    public void testWrongOperatorBalance() {
	for (String wrong : WRONG_OP_BALANCE_EXPRESSIONS) {
	    assertTrue(wrongOperatorBalance(wrong));
	}
	for (String correct : CORRECT_OP_BALANCE_EXPRESSIONS) {
	    assertFalse(wrongOperatorBalance(correct));
	}
    }

    @Test
    public void testRemoveUnary() {
	for (int i = 0; i < RM_UNARY_BEFORE.size(); i++) {
	    String before = RM_UNARY_BEFORE.get(i);
	    String correctOutput = RM_UNARY_CORRECT_OUTPUT.get(i);
	    String removeResult = removeUnary(new StringBuilder(before)).toString();
	    assertTrue(removeResult.equals(correctOutput));
	}
    }

    @Test
    public void testReplaceConstants() {
	String itemAfterProceeding = replaceConstants("123.12043+1234-a+1.1");
	String expectedResult = "X+X-a+X";
	assertTrue(itemAfterProceeding.equals(expectedResult));
    }

    @Test
    public void testGetClosingBracketPos() {
	StringBuilder brackets = new StringBuilder("()dd(d)(dd(dd))");
	assertEquals(1, getClosingBracketPos(brackets, 0));
	assertEquals(6, getClosingBracketPos(brackets, 4));
	assertEquals(brackets.length() - 1, getClosingBracketPos(brackets, 7));
    }
    
    @Test
    public void testIsVariable() {
	assertTrue(isVariable('v'));
	assertTrue(isVariable('X'));
	assertFalse(isVariable('.'));
	assertFalse(isVariable('&'));
    }

    @Test
    public void testIsDouble() {
	assertTrue(isDouble("1234.004524"));
	assertTrue(isDouble("5"));
	assertTrue(isDouble("44.0000044"));
	assertFalse(isDouble("01234.004524"));
	assertFalse(isDouble("not double"));
    }

}
