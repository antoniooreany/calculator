package com.microfocus.test.gorshkov;

import java.util.List;

public class Calculator {
    public static int calculate(String inputExpression) {
        List<Lexeme> lexemes = SyntaxAnalyzer.lexemize(inputExpression);
        LexemeBuffer lexemeBuffer = new LexemeBuffer(lexemes);
        return SemanticAnalyzer.result(lexemeBuffer);
    }
}
