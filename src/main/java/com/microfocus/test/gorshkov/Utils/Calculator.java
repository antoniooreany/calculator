package com.microfocus.test.gorshkov.Utils;

import com.microfocus.test.gorshkov.Elements.ResultElement;
import com.microfocus.test.gorshkov.SemanticAnalyzer.ElementVisitor;

public class Calculator {

    public static int calculate(String arithmeticExpression) {
        ElementVisitor elementVisitor = new ElementVisitor(arithmeticExpression);
        return elementVisitor.visit(new ResultElement());
    }
}
