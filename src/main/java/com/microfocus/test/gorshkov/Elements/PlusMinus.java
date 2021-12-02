package com.microfocus.test.gorshkov.Elements;

import com.microfocus.test.gorshkov.IllegalArithmeticExpression;
import com.microfocus.test.gorshkov.Lexeme;
import com.microfocus.test.gorshkov.LexemeBuffer;
import com.microfocus.test.gorshkov.SemanticAnalyzer.Visitor;
import java.text.MessageFormat;

public class PlusMinus implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public static int plusMinus(LexemeBuffer lexemes) {
        int value = MulDiv.mulDiv(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case OP_PLUS -> value += MulDiv.mulDiv(lexemes);
                case OP_MINUS -> value -= MulDiv.mulDiv(lexemes);
                case EOF, RIGHT_BRACKET -> {
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
