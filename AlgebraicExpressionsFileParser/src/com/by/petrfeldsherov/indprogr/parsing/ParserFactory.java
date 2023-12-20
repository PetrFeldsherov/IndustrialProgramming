package com.by.petrfeldsherov.indprogr.parsing;

import com.by.petrfeldsherov.indprogr.proceeder.AlgExprParser;
import com.by.petrfeldsherov.indprogr.ui.FormatType;

public class ParserFactory {

    public static AlgExprParser createAlgExprParser(FormatType srcFormatType) {
	AlgExprParser parser = null;

	switch (srcFormatType) {
	case TXT:
	    parser = new TxtParser();
	    break;
	case XML:
	    parser = new XmlParser();
	    break;
	case JSON:
	    parser = new JsonParser();
	    break;
	}

	return parser;
    }
}
