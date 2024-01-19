package com.by.petrfeldsherov.indprogr.utils;

import java.io.File;
import java.util.Queue;

import com.by.petrfeldsherov.indprogr.model.Expression;
import com.by.petrfeldsherov.indprogr.ui.FormatType;

public class Utils {

    public static String fileToString(File f, FormatType srcFormat) {
	if (srcFormat == FormatType.TXT) {
	    return txtToString(f);
	} else if (srcFormat == FormatType.XML) {
	    return xmlToString(f);
	} else {
	    return jsonToString(f);
	}
    }

    public static String replaceWithResults(String fileStr, Queue<Expression> expressions, FormatType srcFormat)
	    throws UnsupportedOperationException {
	throw new UnsupportedOperationException("The method replaceWithResults is not yet implemented.");
    }

    private static String xmlToString(File f) {
	return null;

    }

    private static String txtToString(File f) {
	return null;

    }

    private static String jsonToString(File f) {
	return null;

    }
}
