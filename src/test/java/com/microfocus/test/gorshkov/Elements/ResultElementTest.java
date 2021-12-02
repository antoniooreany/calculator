package com.microfocus.test.gorshkov.Elements;

import com.microfocus.test.gorshkov.SemanticAnalyzer.ElementVisitor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultElementTest {

    @Test
    void accept() {
        String arithmeticExpression = "-37* (555 +          25* (34 - 21)) * -26";
        ResultElement resultElement = new ResultElement();
        ElementVisitor elementVisitor = new ElementVisitor(arithmeticExpression);
        int result = resultElement.accept(elementVisitor);
        assertEquals(846_560, result);
    }
}