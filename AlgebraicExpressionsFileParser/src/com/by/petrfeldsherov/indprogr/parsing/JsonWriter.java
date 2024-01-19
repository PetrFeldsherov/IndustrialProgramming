package com.by.petrfeldsherov.indprogr.parsing;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Queue;

import com.by.petrfeldsherov.indprogr.model.Expression;
import com.by.petrfeldsherov.indprogr.proceeder.AlgExprWriter;
import com.by.petrfeldsherov.indprogr.ui.FormatType;

public class JsonWriter implements AlgExprWriter {

    @Override
    public void writeExpressionsToFile(String destFilename, Queue<Expression> algebraicExpressions) {
	StringBuilder json = new StringBuilder("{\n\t\"" + Tag.MATH.str() + "\" : [\n\t\t");
	for (Expression expr : algebraicExpressions) {
	    json.append(expr.getResultNodeStr(FormatType.JSON));
	    json.append(",\n\t\t");
	}
	json.delete(json.length() - 4, json.length());
	json.append("\n\t]\n}");
	PrintWriter out = null;
	try {
	    out = new PrintWriter(destFilename);
	    out.println(json.toString());
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
