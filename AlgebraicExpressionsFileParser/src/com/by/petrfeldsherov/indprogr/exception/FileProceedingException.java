package com.by.petrfeldsherov.indprogr.exception;

import java.io.File;

import com.by.petrfeldsherov.indprogr.ui.FormatType;

@SuppressWarnings("serial")
public class FileProceedingException extends RuntimeException {

    private FormatType format;
    private File file;

    public FileProceedingException(FormatType format, File file) {
	this.format = format;
	this.file = file;
    }

    public FileProceedingException(String message, FormatType format, File file) {
	super(message);
	this.format = format;
	this.file = file;
    }

    public FormatType getFormat() {
	return format;
    }

    public File getFile() {
	return file;
    }

}
