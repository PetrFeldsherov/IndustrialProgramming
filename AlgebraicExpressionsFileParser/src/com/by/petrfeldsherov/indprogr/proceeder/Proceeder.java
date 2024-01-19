package com.by.petrfeldsherov.indprogr.proceeder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Queue;
import com.by.petrfeldsherov.indprogr.model.Expression;
import com.by.petrfeldsherov.indprogr.parsing.ParserFactory;
import com.by.petrfeldsherov.indprogr.parsing.WriterFactory;
import com.by.petrfeldsherov.indprogr.ui.FormatType;

public class Proceeder {
    private List<File> filesToProceed;
    private FormatType srcFormat;
    private FormatType destFormat;
    private AlgExprParser parser;
    private AlgExprWriter writer;

    public Proceeder(List<File> filesToProceed, FormatType srcFormat, FormatType destFormat, boolean cipheredFlag) {
	this.filesToProceed = filesToProceed;
	this.srcFormat = srcFormat;
	this.destFormat = destFormat;
	if (cipheredFlag) {
	    uncipherFilesToProceed();
	}
	parser = ParserFactory.createAlgExprParser(srcFormat);
	writer = WriterFactory.createAlgExprWriter(destFormat);
    }

    public Proceeder(List<File> filesToProceed, FormatType srcFormat, FormatType destFormat) {
	this(filesToProceed, srcFormat, destFormat, false);
    }

    public void proceedFiles() throws IOException {
	for (File f : filesToProceed) {
	    Queue<Expression> expressions = parser.getExpressions(f);
	    String outputFilename = getOutputPathname(f);
	    if (destFormat == FormatType.TXT || srcFormat == destFormat) {
		String fileAsStr = fileToString(f);
		fileAsStr = replaceWithResults(fileAsStr, expressions, srcFormat);
		writer.writeStrInfoToFile(outputFilename, fileAsStr);
	    } else {
		writer.writeExpressionsToFile(outputFilename, expressions);
	    }
	}
    }

    private String replaceWithResults(String fileAsStr, Queue<Expression> expressions, FormatType replacementFormat) {
	while (!expressions.isEmpty()) {
	    Expression expr = expressions.poll();
	    String itemToReplace = expr.getSrcRegex(srcFormat);
	    String replacement = expr.getResultNodeStr(replacementFormat);
	    fileAsStr = fileAsStr.replaceFirst(itemToReplace, replacement);
	}
	return fileAsStr;
    }

    private void uncipherFilesToProceed() throws UnsupportedOperationException {
	throw new UnsupportedOperationException("The method uncipherFilesToProceed is not yet implemented.");
    }

    private String getOutputPathname(File inputFile) {
	String inputPathname = inputFile.getAbsolutePath();
	return inputPathname.substring(0, inputPathname.indexOf('.')) + "_results." + destFormat.getFormatSuffix();
    }

    private String fileToString(File f) throws IOException {
	return new String(Files.readAllBytes(Paths.get(f.getAbsolutePath())), StandardCharsets.UTF_8);
    }

}
