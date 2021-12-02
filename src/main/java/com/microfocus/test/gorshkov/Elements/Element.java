package com.microfocus.test.gorshkov.Elements;

import com.microfocus.test.gorshkov.SemanticAnalyzer.Visitor;

public interface Element {
    int accept(Visitor visitor);
}
