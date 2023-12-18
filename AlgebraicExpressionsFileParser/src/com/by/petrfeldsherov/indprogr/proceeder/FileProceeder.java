package com.by.petrfeldsherov.indprogr.proceeder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.by.petrfeldsherov.indprogr.ui.FormatType;

public class FileProceeder {
    private List<File> filesToProceed;
    private FormatType destFormat;
    AlgExprProceeder algExprProceeder;

    public FileProceeder(List<File> filesToProceed, FormatType srcFormat, FormatType destFormat, Boolean cipherFlag) {
	this.filesToProceed = filesToProceed;
	this.destFormat = destFormat;
	if (cipherFlag) {
	    uncipherFilesToProceed();
	}
	algExprProceeder = new AlgExprProceeder(srcFormat, destFormat);
    }

    public void proceedFiles() {
	for (File f : filesToProceed) {
	    algExprProceeder.parse(f);
	    String outputFilename = getOutputFilename(f);
	    algExprProceeder.writeCalculations(f, outputFilename);
	}
    }

    private String getOutputFilename(File inputFile) {
	String inputFilename = inputFile.getName();
	return inputFilename.substring(0, inputFilename.indexOf('.')) + "_results" + destFormat.getFormatSuffix();
    }

    private void uncipherFilesToProceed() {
	// TODO we need to convert to string and use frolova's method and convert back
	// to file before proceeding

    }
}
