package com.by.petrfeldsherov.indprogr.proceederhelpers;

import com.by.petrfeldsherov.indprogr.proceeder.AlgExprParser;
import com.by.petrfeldsherov.indprogr.ui.FormatType;

public class AlgExprParserFactory {

    public static AlgExprParser createAlgExprParser(FormatType srcFormatType) {
	AlgExprParser parser = null;

	switch (srcFormatType) {
	case TXT:
	    parser = new TxtAlgExprParser();
	    break;
	case XML:
	    parser = new XmlAlgExprParser();
	    break;
	case JSON:
	    parser = new JsonAlgExprParser();
	    break;
	}

	return parser;
    }
}
