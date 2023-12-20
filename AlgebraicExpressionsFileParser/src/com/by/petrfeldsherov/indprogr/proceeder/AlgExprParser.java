package com.by.petrfeldsherov.indprogr.proceeder;

import java.io.File;
import java.util.Queue;

import com.by.petrfeldsherov.indprogr.model.Expression;

public interface AlgExprParser {
    Queue<Expression> getExpressions(File f);
}
