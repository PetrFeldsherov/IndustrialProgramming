package com.by.petrfeldsherov.indprogr.parsing;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Queue;

import com.by.petrfeldsherov.indprogr.model.Expression;
import com.by.petrfeldsherov.indprogr.proceeder.AlgExprWriter;
import com.by.petrfeldsherov.indprogr.ui.FormatType;

public class XmlWriter implements AlgExprWriter {

    @Override
    public void writeExpressionsToFile(String destFilename, Queue<Expression> algebraicExpressions) {
	StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
	for (Expression expr : algebraicExpressions) {
	    xml.append(expr.getResultNodeStr(FormatType.XML));
	    xml.append("\n");
	}
	xml.delete(xml.length() - 1, xml.length());
	PrintWriter out = null;
	try {
	    out = new PrintWriter(destFilename);
	    out.println(xml.toString());
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
    public void writeStrInfoToFile(String destFilename, String fileInfoAsStr) {
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
