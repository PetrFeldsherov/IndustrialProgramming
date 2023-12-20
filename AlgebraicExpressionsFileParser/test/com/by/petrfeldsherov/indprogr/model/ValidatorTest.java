package com.by.petrfeldsherov.indprogr.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ValidatorTest {

    private static final List<String> CORRECT_EXPRESSIONS = new ArrayList<String>(
	    Arrays.asList("(a-b)+c/d-(d*b)", "a-a-a-a-a-a", "b+(b)+b"));
    private static final List<String> INCORRECT_EXPRESSIONS = new ArrayList<String>(
		    Arrays.asList("--a", "-c+a-b+(+a)-", "aa+b"));
    private static final String CORRECT_VALUES = "a = 3; b=4 ; c=-123.432;d=-1111.00003;";
    private static final String INCORRECT_VALUES = "r= 3; y=4 ; c=-123.432;x=-1111.00003;";

    @Test
    public void testIsValidCorrectExpressions() {
	for (String correctExpr : CORRECT_EXPRESSIONS) {
	    Expression exprCorrectValues = new Expression(correctExpr, CORRECT_VALUES);
	    Expression exprIncorrectValues = new Expression(correctExpr, INCORRECT_VALUES);
	    Assert.assertTrue(exprCorrectValues.isValid());
	    Assert.assertTrue(!exprIncorrectValues.isValid());
	}
    }

    @Test
    public void testIsValidIncorrectExpressions() {
	for (String incorrectExpr : INCORRECT_EXPRESSIONS) {
	    Expression exprCorrectValues = new Expression(incorrectExpr, CORRECT_VALUES);
	    Expression exprIncorrectValues = new Expression(incorrectExpr, INCORRECT_VALUES);
	    Assert.assertTrue(!exprCorrectValues.isValid());
	    Assert.assertTrue(!exprIncorrectValues.isValid());
	}
    }

}
