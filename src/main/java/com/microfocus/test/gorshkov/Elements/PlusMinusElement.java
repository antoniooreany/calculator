package com.microfocus.test.gorshkov.Elements;

import com.microfocus.test.gorshkov.SemanticAnalyzer.Visitor;

public class PlusMinusElement implements Element {
    @Override
    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
