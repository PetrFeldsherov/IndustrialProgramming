package com.by.petrfeldsherov.indprogr.exception;

import java.io.File;

import com.by.petrfeldsherov.indprogr.ui.FormatType;

@SuppressWarnings("serial")
public class WritingException extends FileProceedingException {

    public WritingException(String message, FormatType format, File file) {
	super(message, format, file);
    }

    public WritingException(FormatType format, File file) {
	this("Error while writing to " + file.getAbsolutePath() + " of format " + format.getFormatSuffix() + ".", format,
		file);
    }

}
