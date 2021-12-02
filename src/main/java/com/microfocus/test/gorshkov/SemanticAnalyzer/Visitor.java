package com.microfocus.test.gorshkov.SemanticAnalyzer;

import com.microfocus.test.gorshkov.Elements.Factor;
import com.microfocus.test.gorshkov.Elements.MulDiv;
import com.microfocus.test.gorshkov.Elements.PlusMinus;
import com.microfocus.test.gorshkov.Elements.Result;

public interface Visitor {
    void visit(Result result);
    void visit(PlusMinus plusMinus);
    void visit(MulDiv mulDiv);
    void visit(Factor factor);
}
