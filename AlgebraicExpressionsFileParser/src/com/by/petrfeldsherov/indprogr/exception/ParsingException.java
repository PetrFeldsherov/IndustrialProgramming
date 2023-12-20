package com.by.petrfeldsherov.indprogr.exception;

import java.io.File;

import com.by.petrfeldsherov.indprogr.ui.FormatType;

@SuppressWarnings("serial")
public class ParsingException extends FileProceedingException {

    public ParsingException(String message, FormatType format, File file) {
	super(message, format, file);
    }

    public ParsingException(FormatType format, File file) {
	this("Error while parsing " + file.getAbsolutePath() + " of format " + format.getFormatSuffix() + ".", format,
		file);
    }

}
