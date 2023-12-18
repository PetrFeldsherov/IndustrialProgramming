package com.by.petrfeldsherov.indprogr.proceeder;

import java.io.File;
import java.util.Queue;

import com.by.petrfeldsherov.indprogr.calculator.AlgebraicExpression;

public interface AlgExprParser {
    Queue<AlgebraicExpression> getAlgebraicExpressions(File f);
}
