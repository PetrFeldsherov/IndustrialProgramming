package com.by.petrfeldsherov.indprogr.proceeder;

import java.util.Queue;

import com.by.petrfeldsherov.indprogr.model.Expression;

public interface AlgExprWriter {
    void writeExpressionsToFile(String destFilename, Queue<Expression> algebraicExpressions);
    void writeStrInfoToFile(String destFilename, String fileInfoAsStr);
}
