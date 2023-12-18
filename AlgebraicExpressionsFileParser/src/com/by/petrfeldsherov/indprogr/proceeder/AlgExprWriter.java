package com.by.petrfeldsherov.indprogr.proceeder;

import java.util.Queue;

import com.by.petrfeldsherov.indprogr.calculator.AlgebraicExpression;

public interface AlgExprWriter {
    void writeToFile(String destFilename, Queue<AlgebraicExpression> algebraicExpressions);
    void writeToFile(String destFilename, String fileInfoAsText);
}
