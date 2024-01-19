package com.by.petrfeldsherov.indprogr.parsing;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Queue;

import com.by.petrfeldsherov.indprogr.model.Expression;
import com.by.petrfeldsherov.indprogr.proceeder.AlgExprWriter;
import com.by.petrfeldsherov.indprogr.ui.FormatType;

public class TxtWriter implements AlgExprWriter {

    @Override
    public void writeExpressionsToFile(String destFilename, Queue<Expression> algebraicExpressions) throws NullPointerException {
	PrintWriter out = null;
	try {
	    out = new PrintWriter(destFilename);
	    for (Expression expr : algebraicExpressions) {
		out.println(expr.getResultNodeStr(FormatType.TXT));
	    }
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} finally {
	    if (out == null) {
		throw new NullPointerException("Cannot close writing stream.");
	    }
	    out.close();
	}
    }

    @Override
    public void writeStrInfoToFile(String destFilename, String fileInfoAsStr) throws NullPointerException {
	PrintWriter out = null;
	try {
	    out = new PrintWriter(destFilename);
	    out.println(fileInfoAsStr);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} finally {
	    if (out == null) {
		throw new NullPointerException("Cannot close writing stream.");
	    }
	    out.close();
	}
    }
}
