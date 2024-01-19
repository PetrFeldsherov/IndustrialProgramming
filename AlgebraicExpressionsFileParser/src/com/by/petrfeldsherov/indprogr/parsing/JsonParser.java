package com.by.petrfeldsherov.indprogr.parsing;

import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Queue;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.by.petrfeldsherov.indprogr.exception.ParsingException;
import com.by.petrfeldsherov.indprogr.model.Expression;
import com.by.petrfeldsherov.indprogr.proceeder.AlgExprParser;
import com.by.petrfeldsherov.indprogr.ui.FormatType;

public class JsonParser implements AlgExprParser {

    Queue<Expression> expressions = new LinkedList<Expression>();
    
    @Override
    public Queue<Expression> getExpressions(File f) {
	JSONParser parser = new JSONParser();
	try (FileReader reader = new FileReader(f)) {
	    JSONObject rootJSONObject = (JSONObject) parser.parse(reader);
	    JSONArray mathJSONArray = (JSONArray) rootJSONObject.get(Tag.MATH.str());
	    for (Object item : mathJSONArray) {
		JSONObject math = (JSONObject) item;
		String expression = (String) math.get(Tag.EXPR.str());
		String values = (String) math.get(Tag.VAL.str());
		expressions.add(new Expression(expression.substring(0, expression.length() - 1), values));
	    }
	} catch (Exception e) {
	    throw new ParsingException("Cannot parse file.", FormatType.JSON, f);
	}
	return expressions;
    }

}
