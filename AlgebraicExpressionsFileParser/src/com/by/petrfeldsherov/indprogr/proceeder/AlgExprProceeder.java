package com.by.petrfeldsherov.indprogr.proceeder;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import com.by.petrfeldsherov.indprogr.calculator.AlgebraicExpression;
import com.by.petrfeldsherov.indprogr.proceederhelpers.AlgExprParserFactory;
import com.by.petrfeldsherov.indprogr.proceederhelpers.AlgExprWriterFactory;
import com.by.petrfeldsherov.indprogr.ui.FormatType;

public class AlgExprProceeder { // плохо еще то что у меня пакедж хелперс, можно сделать утилз, в котором будут конвертера два и сериализейшн, в котором будут фактори по письму и парсингу, еще надо переименовать громоздкие креате
    private FormatType srcFormat;
    private FormatType destFormat;
    private HashMap<File, Queue<AlgebraicExpression>> algebraicExpressions;
    private AlgExprParser parser;
    private AlgExprWriter writer;
    private Convertor convertor;
    
    

    public AlgExprProceeder(FormatType srcFormat, FormatType destFormat) {
	this.srcFormat = srcFormat;
	this.destFormat = destFormat;
	parser = AlgExprParserFactory.createAlgExprParser(srcFormat);
	writer = AlgExprWriterFactory.createAlgExprWriter(destFormat);
	convertor = new Convertor(srcFormat, destFormat); // перевод в строку это просто обычный утилз с подгоном через свитч сделать, перевод в файл это нужно перегрузку райтТуФайл слделать и тогда не так уродски с иерархией будет помимо прочего
    }

    public void parse(File f) {
	Queue<AlgebraicExpression> expressions = parser.getAlgebraicExpressions(f);
	algebraicExpressions.put(f, expressions);
    }
    
    public void writeCalculations(File src, String destFilename) {
	if (destFormat == FormatType.TXT || srcFormat == destFormat) {
	    String srcAsString = convertor.fileToString(src); // должен быть статик метод со вторым параметром FormatType
	    srcAsString = passResults(srcAsString);
	    
	    // TODO write to dest as string, только неясно получить ли мне сам хмл нужно или еще что.
	    // пока что все очень плохо, я навешал функционала, который думал, что стройно выстроится и быстро закодируется, а пока что сплошные траблы, вот как я надеялся записать в файл нечто, короче тут надо получить результат и прописать его в какое-то место в памяти дополнительным методом, страшно
	} else {
	    writer.writeToFile(destFilename, algebraicExpressions.get(src));
	}
    }

    private String passResults(String src) {
	Queue<AlgebraicExpression> expressions = (LinkedList<AlgebraicExpression>) algebraicExpressions.get(src);
	while (!expressions.isEmpty()) {
	    AlgebraicExpression expression = expressions.poll();
	    src.replaceAll(expression.toConditionNode(srcFormat), expression.toResultNode(srcFormat)); // тоже не самая тривиальная задача, судя по всему нужно просто сделать три наследника где только принт фирсттег или принтсекондтег будут отличаться   
	}
	return src;
    }
}
