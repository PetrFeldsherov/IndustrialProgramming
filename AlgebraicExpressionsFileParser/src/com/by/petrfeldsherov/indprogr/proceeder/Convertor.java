package com.by.petrfeldsherov.indprogr.proceeder;

import java.io.File;

import com.by.petrfeldsherov.indprogr.proceederhelpers.FileConvertorFactory;
import com.by.petrfeldsherov.indprogr.proceederhelpers.StringConvertorFactory;
import com.by.petrfeldsherov.indprogr.ui.FormatType;

public class Convertor {
    FileConvertor fileConvertor;
    StringConvertor stringConvertor;
    
    public Convertor(FormatType srcFormat, FormatType destFormat) {
	fileConvertor = FileConvertorFactory.createFileConvertor(srcFormat);
	stringConvertor = StringConvertorFactory.createStringConvertor(destFormat);
    }
    
    String fileToString(File f) {
	return fileConvertor.fileToString(f);
    }
    
    File stringToFile(String s) {
	return stringConvertor.stringToFile(s); // это черезчур, надо создать класс со статическими методами, где без инстанциации будет просто отфутболиваться нужный метод, тут фактори это плохо, но сначала не по форме а по содержанию разберемся
    }
}
