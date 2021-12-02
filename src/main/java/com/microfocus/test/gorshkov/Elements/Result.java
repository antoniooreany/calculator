package com.microfocus.test.gorshkov.Elements;

import com.microfocus.test.gorshkov.Lexeme;
import com.microfocus.test.gorshkov.LexemeBuffer;
import com.microfocus.test.gorshkov.LexemeType;
import com.microfocus.test.gorshkov.SemanticAnalyzer.Visitor;

public class Result implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public static int result(LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.next();
        if (lexeme.type == LexemeType.EOF) return 0;
        lexemes.back();
        return PlusMinus.plusMinus(lexemes);
    }
}
