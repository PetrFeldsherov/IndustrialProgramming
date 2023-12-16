package com.by.petrfeldsherov.indprogr.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.by.petrfeldsherov.indprogr.exception.InvalidInputException;
import com.by.petrfeldsherov.indprogr.parser.AlgebraicExpressionsFileParser;

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
	    List<Path> pathesToParse = null;
	    Boolean zipFlag = null;
	    Boolean cipherFlag = null;
	    try {
		srcFormat = getFormatType(arguments[1]);
		destFormat = getFormatType(arguments[2]);
		pathesToParse = listPathesToParse(arguments[0], srcFormat);
		zipFlag = getFlagValue(arguments[3]);
		cipherFlag = getFlagValue(arguments[4]);
	    } catch (InvalidInputException e) {
		System.out.println(e.getMessage() + " Invalid value is \"" + e.getInvalidValue() + "\".");
		continue;
	    } catch (DirectoryIteratorException e) {
		System.out.println(e.getMessage());
		continue;
	    } catch (IOException e) {
		System.out.println(e.getMessage());
		continue;
	    }

	    AlgebraicExpressionsFileParser aefp = new AlgebraicExpressionsFileParser(pathesToParse, srcFormat,
		    destFormat, zipFlag, cipherFlag);
	    try {
		aefp.parseProvidedFiles();
	    } catch (UnsupportedOperationException e) {
		System.out.println(e.getMessage());
	    }
	    System.out.println("Parsing for " + Arrays.toString(arguments) + " is completed.");
	}
    }

    private static void appInfo() {
	System.out.println(
		"Parses <srs format> files located at <abs path for parcing>, which may be either filepath or dirpath.\n"
			+ "Stores the results in <dest format> files created in that same directory.\n"
			+ "<zip> and <cipher> flags correspond to the searching for archived or ciphered files in addition common ones.");
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

    private static List<Path> listPathesToParse(String absolutePathname, FormatType srcFormat)
	    throws InvalidInputException, DirectoryIteratorException, IOException {
	Path path = Paths.get(absolutePathname);
	List<Path> result = new ArrayList<>();

	if (!Files.exists(path)) {
	    throw new InvalidInputException("Provided doesn't exist.", absolutePathname);
	} else if (!path.isAbsolute()) {
	    throw new InvalidInputException("Provided path isn't absolute.", absolutePathname);
	}

	if (Files.isRegularFile(path)) {
	    if (!Files.isReadable(path)) {
		throw new InvalidInputException("The file to which path is provided isn't readable.", absolutePathname);
	    } else if (!path.endsWith("." + srcFormat.getFormatSuffix())) {
		throw new InvalidInputException("The file to which path is provided doesn't correspond srcFormat.",
			absolutePathname);
	    }

	    result.add(path);
	    return result;
	} else {
	    DirectoryStream<Path> stream = null;
	    try {
		stream = Files.newDirectoryStream(path, "*[.]" + srcFormat.getFormatSuffix()); // {$srcFormat, zip} или же прямо тут в UI модуле отдельно зипы просмотреть: для каждого с суффиксом зип рекурсивно по названию и добавить раззипованные файлы если они нужного формата, тогда флаг зип отрабатывает в этом модуле и класс наш можно и вовсе сделать с параметрами по умолчанию, только как проверить что раззипованный файл удовлетворяет формату... короче пока что неясно
		for (Path entry : stream) {
		    result.add(entry);
		}
		if (result.isEmpty()) {
		    throw new InvalidInputException(
			    "The directory to which path is provided doesn't contain any files of srcFormat.",
			    absolutePathname);
		}
	    } catch (DirectoryIteratorException e) {
		throw e.getCause();
	    } finally {
		try {
		    if (stream != null) {
			stream.close();
		    }
		} catch (IOException e) {
		    throw e;
		}
	    }

	    return result;
	}
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
