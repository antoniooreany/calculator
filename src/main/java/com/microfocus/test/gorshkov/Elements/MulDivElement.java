package com.microfocus.test.gorshkov.Elements;

import com.microfocus.test.gorshkov.SemanticAnalyzer.Visitor;

public class MulDivElement implements Element {
    @Override
    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
