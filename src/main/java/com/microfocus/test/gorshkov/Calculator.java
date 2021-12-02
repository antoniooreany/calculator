package com.microfocus.test.gorshkov;

import com.microfocus.test.gorshkov.Elements.Result;

import java.util.List;

public class Calculator {

    public static LexemeBuffer lexemeBuffer;

    public static int calculate(String arithmeticExpression) {
        List<Lexeme> lexemes = SyntaxAnalyzer.lexemize(arithmeticExpression);
        LexemeBuffer lexemeBuffer = new LexemeBuffer(lexemes);
        return Result.result(lexemeBuffer);
    }
}
