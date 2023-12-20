package com.by.petrfeldsherov.indprogr.parsing;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Queue;

import com.by.petrfeldsherov.indprogr.model.Expression;
import com.by.petrfeldsherov.indprogr.proceeder.AlgExprWriter;

public class TxtWriter implements AlgExprWriter {

    @Override
    public void writeExpressionsToFile(String destFilename, Queue<Expression> algebraicExpressions) {
	// TODO write header, foreach write node via to formatted node method of
	// Expression, write footer

    }

    @Override
    public void writeStrInfoToFile(String destFilename, String fileInfoAsStr) {
	PrintWriter out = null;
	try {
	    out = new PrintWriter(destFilename); // неверно, надо сначала создать файл, также это не бинарное, надо бы уточнить условие задачи что-ли
	    out.println(fileInfoAsStr);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} finally {
	    if (out != null) {
		out.close();
	    }
	}
    }
}
