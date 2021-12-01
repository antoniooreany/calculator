package com.microfocus.test.gorshkov;

import java.text.MessageFormat;

/*
    THE GRAMMAR RULES ARE:

    result : plusMinus* EOF ;
    plusMinus: mulDiv ( ( '+' | '-' ) mulDiv )* ;
    mulDiv : factor ( ( '*' | '/' ) factor )* ;
    factor : unaryMinus | NUMBER | '(' result ')' ;
    unaryMinus : '-' factor
*/

public class SemanticAnalyzer {
    public static int result(LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.next();
        if (lexeme.type == LexemeType.EOF) return 0;
        lexemes.back();
        return plusMinus(lexemes);
    }

    public static int plusMinus(LexemeBuffer lexemes) {
        int value = mulDiv(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case OP_PLUS -> value += mulDiv(lexemes);
                case OP_MINUS -> value -= mulDiv(lexemes);
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

    public static int mulDiv(LexemeBuffer lexemes) {
        int value = factor(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case OP_MUL -> value *= factor(lexemes);
                case OP_DIV -> value /= factor(lexemes);
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

    public static int factor(LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.next();
        switch (lexeme.type) {
            case NUMBER:
                return Integer.parseInt(lexeme.value);
            case OP_MINUS:
                return -factor(lexemes);
            case LEFT_BRACKET:
                int value = plusMinus(lexemes);
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
