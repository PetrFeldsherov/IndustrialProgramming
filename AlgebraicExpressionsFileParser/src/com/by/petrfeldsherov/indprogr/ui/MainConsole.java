package com.by.petrfeldsherov.indprogr.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.by.petrfeldsherov.indprogr.exception.InvalidInputException;
import com.by.petrfeldsherov.indprogr.proceeder.Proceeder;

public class MainConsole {

    public static void main(String[] args) {
	appInfo();
	while (true) {
	    usage();
	    String arguments[] = getConsoleArguments();
	    if (1 == arguments.length && arguments[0].equals("exit")) {
		break;
	    } else if (arguments.length != 5) {
		continue;
	    }

	    FormatType srcFormat = null;
	    FormatType destFormat = null;
	    List<File> filesToParse = null;
	    Boolean zipFlag = null;
	    Boolean cipherFlag = null;
	    try {
		zipFlag = getFlagValue(arguments[3]);
		cipherFlag = getFlagValue(arguments[4]);
		srcFormat = getFormatType(arguments[1]);
		destFormat = getFormatType(arguments[2]);
		filesToParse = listFilesToParse(arguments[0], srcFormat, zipFlag);
	    } catch (InvalidInputException e) {
		System.out.println(e.getMessage() + " Invalid value is \"" + e.getInvalidValue() + "\".");
		continue;
	    }

	    try {
		Proceeder proceeder = new Proceeder(filesToParse, srcFormat, destFormat, cipherFlag);
		proceeder.proceedFiles();
	    } catch (UnsupportedOperationException e) {
		System.out.println(e.getMessage());
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    System.out.println("Parsing for " + Arrays.toString(arguments) + " is completed.");
	}
    }

    private static void appInfo() {
	System.out.println(
		"Proceeds <src format> files located at <abs path for proceeding>\n\t\twhich may be either path to a file or directory path.\n"
			+ "Replaces algebraic expressions with their results if <dest format> is txt"
			+ "\n\t\tor if <dest format> = <src format>.\nJust outputs calculation results otherwise.\n\t\t"
			+ "Results are stored in <dest format> files created in src directory.\n"
			+ "<zip> and <cipher> flags correspond to the proceeding archived and ciphered files.\n\n");
    }

    private static void usage() {
	System.out.println("Usage:\n\t<abs path for parsing> <src format> <dest format> <zip : y/n> <cipher : y/n>\n\t"
		+ "input \"exit\" to finish the program execution");
    }

    private static String[] getConsoleArguments() {
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	String consoleArguments[] = null;
	try {
	    consoleArguments = reader.readLine().trim().split(" ");
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return consoleArguments;
    }

    private static FormatType getFormatType(String formatArgument) throws InvalidInputException {
	String lowerCaseArgument = formatArgument.toLowerCase();
	for (FormatType ft : FormatType.values()) {
	    if (lowerCaseArgument.matches("[.]?" + ft.getFormatSuffix())) {
		return ft;
	    }
	}
	throw new InvalidInputException("Invalid format provided.", formatArgument);
    }

    private static List<File> listFilesToParse(String absolutePathname, FormatType srcFormat, Boolean zipFlag)
	    throws InvalidInputException {
	File f = new File(absolutePathname);
	List<File> result = new ArrayList<>();

	if (!f.exists()) {
	    throw new InvalidInputException("No file exist with provided pathname.", absolutePathname);
	} else if (!f.isAbsolute()) {
	    throw new InvalidInputException("Provided path isn't absolute.", absolutePathname);
	}

	if (f.isFile()) {
	    if (!f.canRead()) {
		throw new InvalidInputException("The file to which path is provided isn't readable.", absolutePathname);
	    } else if (!f.getName().endsWith("." + srcFormat.getFormatSuffix())) {
		throw new InvalidInputException("The file to which path is provided doesn't correspond srcFormat.",
			absolutePathname);
	    }
	    result.add(f);
	} else {
	    for (File file : f.listFiles()) {
		if (file.exists() && file.isFile() && file.getName().endsWith("." + srcFormat.getFormatSuffix())) {
		    result.add(file);
		}
	    }
	    if (result.isEmpty()) {
		throw new InvalidInputException(
			"The directory to which path is provided doesn't contain any files of srcFormat.",
			absolutePathname);
	    }
	}

	return result;
    }

    private static Boolean getFlagValue(String flagArgument) throws InvalidInputException {
	String lowerCaseArgument = flagArgument.toLowerCase();
	if (lowerCaseArgument.equals("y")) {
	    return true;
	} else if (lowerCaseArgument.equals("n")) {
	    return false;
	} else {
	    throw new InvalidInputException("Invalid flag provided.", flagArgument);
	}
    }
}
