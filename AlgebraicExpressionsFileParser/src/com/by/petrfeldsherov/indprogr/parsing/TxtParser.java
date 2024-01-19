package com.by.petrfeldsherov.indprogr.parsing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import com.by.petrfeldsherov.indprogr.exception.ParsingException;
import com.by.petrfeldsherov.indprogr.model.Expression;
import com.by.petrfeldsherov.indprogr.proceeder.AlgExprParser;
import com.by.petrfeldsherov.indprogr.ui.FormatType;

public class TxtParser implements AlgExprParser {

    Queue<Expression> expressions = new LinkedList<Expression>();

    @Override
    public Queue<Expression> getExpressions(File f) throws ParsingException {
	try (BufferedReader br = new BufferedReader(new FileReader(f))) {
	    String line;
	    while ((line = br.readLine()) != null) {
		if (isExpression(line)) {
		    expressions.add(new Expression(expressionPart(line), valuesPart(line)));
		}
	    }
	} catch (IOException e) {
	    throw new ParsingException(e.getMessage(), FormatType.TXT, f);
	}
	return expressions;
    }

    private boolean isExpression(String line) {
	return line.matches(AlgExprRegex.AlGEXPR_ALPHABET.str() + ";" + AlgExprRegex.VALUES.str());
    }

    private String expressionPart(String line) {
	return line.substring(0, line.indexOf(';'));
    }

    private String valuesPart(String line) {
	return line.substring(line.indexOf(';') + 1);
    }

}
