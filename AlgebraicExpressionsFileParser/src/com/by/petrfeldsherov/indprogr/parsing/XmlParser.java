package com.by.petrfeldsherov.indprogr.parsing;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.by.petrfeldsherov.indprogr.exception.ParsingException;
import com.by.petrfeldsherov.indprogr.model.Expression;
import com.by.petrfeldsherov.indprogr.proceeder.AlgExprParser;
import com.by.petrfeldsherov.indprogr.ui.FormatType;

public class XmlParser implements AlgExprParser {

    Queue<Expression> expressions = new LinkedList<Expression>();

    @Override
    public Queue<Expression> getExpressions(File f) throws ParsingException {
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	Document doc = null;
	try {
	    doc = dbf.newDocumentBuilder().parse(f);
	} catch (Exception e) {
	    throw new ParsingException(e.getMessage(), FormatType.XML, f);
	}

	NodeList mathNodes = doc.getElementsByTagName(Tag.MATH_NODE.getTagName());
	for (int i = 0; i < mathNodes.getLength(); i++) {
	    NodeList mathNodeContent = mathNodes.item(i).getChildNodes();

	    try {
		expressions.add(getExpression(mathNodeContent));
	    } catch (NullPointerException e) {
		throw new ParsingException(e.getMessage(), FormatType.XML, f);
	    }
	}

	return expressions;
    }

    private Expression getExpression(NodeList mathNodeContent) throws NullPointerException {
	String expressionPart = null;
	String valuesPart = null;

	for (int i = 0; i < mathNodeContent.getLength(); i++) {
	    if (mathNodeContent.item(i).getNodeType() != Node.ELEMENT_NODE) {
		continue;
	    }

	    if (mathNodeContent.item(i).getNodeName() == Tag.EXPRESSION_PART.getTagName()) {
		expressionPart = mathNodeContent.item(i).getTextContent();
	    } else if (mathNodeContent.item(i).getNodeName() == Tag.VALUES_PART.getTagName()) {
		valuesPart = mathNodeContent.item(i).getTextContent();
	    }
	}

	if (expressionPart == null || valuesPart == null) {
	    throw new NullPointerException(
		    "Either expression part or values part is null in the " + Tag.MATH_NODE.getTagName() + " node.");
	}

	return new Expression(expressionPart, valuesPart);
    }

}
