package com.microfocus.test.gorshkov.Utils;

import com.microfocus.test.gorshkov.Elements.ResultElement;
import com.microfocus.test.gorshkov.SemanticAnalyzer.ElementVisitor;

public class Calculator {

    public static int getResult(String arithmeticExpression) {
        ResultElement resultElement = new ResultElement();
        ElementVisitor elementVisitor = new ElementVisitor(arithmeticExpression);
        return resultElement.accept(elementVisitor);
    }
}
