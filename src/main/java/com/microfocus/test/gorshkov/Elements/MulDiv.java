package com.microfocus.test.gorshkov.Elements;

import com.microfocus.test.gorshkov.IllegalArithmeticExpression;
import com.microfocus.test.gorshkov.Lexeme;
import com.microfocus.test.gorshkov.LexemeBuffer;
import com.microfocus.test.gorshkov.SemanticAnalyzer.Visitor;

import java.text.MessageFormat;

public class MulDiv implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public static int mulDiv(LexemeBuffer lexemes) {
        int value = Factor.factor(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case OP_MUL -> value *= Factor.factor(lexemes);
                case OP_DIV -> value /= Factor.factor(lexemes);
                case EOF, RIGHT_BRACKET, OP_PLUS, OP_MINUS -> {
                    lexemes.back();
                    return value;
                }
                default -> throw new IllegalArithmeticExpression(MessageFormat.format(
                        "Unexpected token: \"{0}\" at position: \"{1}\"",
                        lexeme.value, lexemes.getPos()));
            }
        }
    }

}
