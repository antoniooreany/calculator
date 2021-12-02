package com.microfocus.test.gorshkov.SemanticAnalyzer;

import com.microfocus.test.gorshkov.Calculator;
import com.microfocus.test.gorshkov.Elements.Factor;
import com.microfocus.test.gorshkov.Elements.MulDiv;
import com.microfocus.test.gorshkov.Elements.PlusMinus;
import com.microfocus.test.gorshkov.Elements.Result;

public class ElemVisitor implements Visitor {

    @Override
    public void visit(Result result) {
        Result.result(Calculator.lexemeBuffer);
    }

    @Override
    public void visit(PlusMinus plusMinus) {
        PlusMinus.plusMinus(Calculator.lexemeBuffer);
    }

    @Override
    public void visit(MulDiv mulDiv) {
        MulDiv.mulDiv(Calculator.lexemeBuffer);
    }

    @Override
    public void visit(Factor factor) {
        Factor.factor(Calculator.lexemeBuffer);
    }
}
