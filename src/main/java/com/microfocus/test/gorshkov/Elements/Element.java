package com.microfocus.test.gorshkov.Elements;

import com.microfocus.test.gorshkov.SemanticAnalyzer.Visitor;

public interface Element {
    void accept(Visitor visitor);
}
