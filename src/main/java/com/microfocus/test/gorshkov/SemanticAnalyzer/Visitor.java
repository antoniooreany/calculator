package com.microfocus.test.gorshkov.SemanticAnalyzer;

import com.microfocus.test.gorshkov.Elements.FactorElement;
import com.microfocus.test.gorshkov.Elements.MulDivElement;
import com.microfocus.test.gorshkov.Elements.PlusMinusElement;
import com.microfocus.test.gorshkov.Elements.ResultElement;

public interface Visitor {
    int visit(ResultElement resultElement);
    int visit(PlusMinusElement plusMinusElement);
    int visit(MulDivElement mulDivElement);
    int visit(FactorElement factorElement);
}
