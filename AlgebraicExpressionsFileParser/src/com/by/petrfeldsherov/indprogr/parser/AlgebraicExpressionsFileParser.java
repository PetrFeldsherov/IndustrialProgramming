package com.by.petrfeldsherov.indprogr.parser;

import java.nio.file.Path;
import java.util.List;

import com.by.petrfeldsherov.indprogr.ui.FormatType;

public class AlgebraicExpressionsFileParser {
    private List<Path> pathesToParse;
    private FormatType srcFormat;
    private FormatType destFormat;
    private Boolean zipFlag;
    private Boolean cipherFlag;

    public AlgebraicExpressionsFileParser(List<Path> pathesToParse, FormatType srcFormat, FormatType destFormat,
	    Boolean zipFlag, Boolean cipherFlag) {
	this.pathesToParse = pathesToParse;
	this.srcFormat = srcFormat;
	this.destFormat = destFormat;
	this.zipFlag = zipFlag;
	this.cipherFlag = cipherFlag;
    }
    
    public void parseProvidedFiles() {
	throw new UnsupportedOperationException("The method is not yet implemented.");
    }
}
