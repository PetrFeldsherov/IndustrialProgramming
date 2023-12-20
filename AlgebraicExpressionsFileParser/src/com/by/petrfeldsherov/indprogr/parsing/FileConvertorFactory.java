package com.by.petrfeldsherov.indprogr.proceederhelpers;

import com.by.petrfeldsherov.indprogr.proceeder.FileConvertor;
import com.by.petrfeldsherov.indprogr.ui.FormatType;

public class FileConvertorFactory {

    public static FileConvertor createFileConvertor(FormatType formatType) {
	FileConvertor convertor = null;

	switch (formatType) {
	case TXT:
	    convertor = new TxtFileConvertor();
	    break;
	case XML:
	    convertor = new XmlFileConvertor();
	    break;
	case JSON:
	    convertor = new JsonFileConvetor();
	    break;
	}

	return convertor;
    }
}
