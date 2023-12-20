package com.by.petrfeldsherov.indprogr.parsing;

import com.by.petrfeldsherov.indprogr.proceeder.AlgExprWriter;
import com.by.petrfeldsherov.indprogr.ui.FormatType;

public class WriterFactory {

    public static AlgExprWriter createAlgExprWriter(FormatType destFormatType) {
	AlgExprWriter writer = null;
	
	switch (destFormatType) {
	case TXT:
	    writer = new TxtWriter();
	    break;
	case XML:
	    writer = new XmlWriter();
	    break;
	case JSON:
	    writer = new JsonWriter();
	    break;
	}
	
	return writer;
    }
}
