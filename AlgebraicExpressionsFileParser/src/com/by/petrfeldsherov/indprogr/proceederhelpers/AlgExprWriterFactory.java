package com.by.petrfeldsherov.indprogr.proceederhelpers;

import com.by.petrfeldsherov.indprogr.proceeder.AlgExprWriter;
import com.by.petrfeldsherov.indprogr.ui.FormatType;

public class AlgExprWriterFactory {

    public static AlgExprWriter createAlgExprWriter(FormatType destFormatType) {
	AlgExprWriter writer = null;
	
	switch (destFormatType) {
	case TXT:
	    writer = new TxtAlgExprWriter();
	    break;
	case XML:
	    writer = new XmlAlgExprWriter();
	    break;
	case JSON:
	    writer = new JsonAlgExprWriter();
	    break;
	}
	
	return writer;
    }
}
