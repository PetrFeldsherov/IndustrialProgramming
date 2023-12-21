package com.by.petrfeldsherov.indprogr.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class CalculatorTest {
    private static final ArrayList<String> CORRECT_EXPRESSIONS = new ArrayList<String>(
	    Arrays.asList("1+3-a*d*c", "1234+548-(d*c-323.444)", "1234.0000 +  548.00  -( d *(c*d/ d)-323.444)"));
    private static final String VALUES = "a=3;d=3;c=4563.1234;";
    private static final ArrayList<String> CORRECT_OUTPUT_TRUNCATED = new ArrayList<String>(
	    Arrays.asList("-41064.1106", "-11583.9262", "-11583.9262"));

    @Test
    public void testGetCalculationResult() {
	for (int i = 0; i < CORRECT_EXPRESSIONS.size(); i++) {
	    String expr = CORRECT_EXPRESSIONS.get(i);
	    String outputTruncated = CORRECT_OUTPUT_TRUNCATED.get(i);
	    Expression expression = new Expression(expr, VALUES);
	    assertTrue(answerStartsWith(expression.getResult(), outputTruncated));
	}
    }

    private boolean answerStartsWith(Double result, String outputTruncated) {
	return result.toString().indexOf(outputTruncated) == 0;
    }

}
