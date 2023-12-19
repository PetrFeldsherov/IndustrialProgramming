package com.by.petrfeldsherov.indprogr.proceederhelpers;

import com.by.petrfeldsherov.indprogr.proceeder.StringConvertor;
import com.by.petrfeldsherov.indprogr.ui.FormatType;

public class StringConvertorFactory {
    public static StringConvertor createStringConvertor(FormatType formatType) {
	StringConvertor convertor = null;

	switch (formatType) {
	case TXT:
	    convertor = new TxtStringConvertor();
	    break;
	case XML:
	    convertor = new XmlStringConvertor();
	    break;
	case JSON:
	    convertor = new JsonStringConvetor();
	    break;
	}

	return convertor;
    }
}
