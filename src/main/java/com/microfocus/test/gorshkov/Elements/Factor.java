package com.microfocus.test.gorshkov.Elements;

import com.microfocus.test.gorshkov.IllegalArithmeticExpression;
import com.microfocus.test.gorshkov.Lexeme;
import com.microfocus.test.gorshkov.LexemeBuffer;
import com.microfocus.test.gorshkov.LexemeType;
import com.microfocus.test.gorshkov.SemanticAnalyzer.Visitor;
import java.text.MessageFormat;

public class Factor implements Element{
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public static int factor(LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.next();
        switch (lexeme.type) {
            case NUMBER:
                return Integer.parseInt(lexeme.value);
            case OP_MINUS:
                return -factor(lexemes);
            case LEFT_BRACKET:
                int value = PlusMinus.plusMinus(lexemes);
                lexeme = lexemes.next();
                if (lexeme.type != LexemeType.RIGHT_BRACKET) {
                    throw new IllegalArithmeticExpression(MessageFormat.format(
                            "Unexpected token: \"{0}\" at position: \"{1}\"",
                            lexeme.value, lexemes.getPos()));
                }
                return value;
            default:
                throw new IllegalArithmeticExpression(MessageFormat.format(
                        "Unexpected token: \"{0}\" at position: \"{1}\"",
                        lexeme.value, lexemes.getPos()));
        }
    }
}
