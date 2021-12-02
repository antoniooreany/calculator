package com.microfocus.test.gorshkov.SemanticAnalyzer;

import com.microfocus.test.gorshkov.Elements.FactorElement;
import com.microfocus.test.gorshkov.Elements.MulDivElement;
import com.microfocus.test.gorshkov.Elements.PlusMinusElement;
import com.microfocus.test.gorshkov.Elements.ResultElement;
import com.microfocus.test.gorshkov.Utils.*;

import java.text.MessageFormat;
import java.util.List;

public class ElementVisitor implements Visitor {

    public LexemeBuffer lexemeBuffer;

    public ElementVisitor(String arithmeticExpression) {
        List<Lexeme> lexemes = SyntaxAnalyzer.lexemize(arithmeticExpression);
        this.lexemeBuffer = new LexemeBuffer(lexemes);
    }

    @Override
    public int visit(ResultElement result) {
        Lexeme lexeme = lexemeBuffer.next();
        if (lexeme.type == LexemeType.EOF) return 0;
        lexemeBuffer.back();
        return visit(new PlusMinusElement());
    }

    @Override
    public int visit(PlusMinusElement plusMinus) {
        int value = visit(new MulDivElement());
        while (true) {
            Lexeme lexeme = lexemeBuffer.next();
            switch (lexeme.type) {
                case PLUS -> value += visit(new MulDivElement());
                case MINUS -> value -= visit(new MulDivElement());
                case EOF, RIGHT_BRACKET -> {
                    lexemeBuffer.back();
                    return value;
                }
                default -> throw new IllegalArithmeticExpression(MessageFormat.format(
                        "Unexpected lexeme: \"{0}\" at position: \"{1}\"",
                        lexeme.value, lexemeBuffer.getPos()));
            }
        }
    }

    @Override
    public int visit(FactorElement factor) {
        Lexeme lexeme = lexemeBuffer.next();
        switch (lexeme.type) {
            case INT:
                return Integer.parseInt(lexeme.value);
            case MINUS:
                return -visit(new FactorElement());
            case LEFT_BRACKET:
                int value = visit(new PlusMinusElement());
                lexeme = lexemeBuffer.next();
                if (lexeme.type != LexemeType.RIGHT_BRACKET) {
                    throw new IllegalArithmeticExpression(MessageFormat.format(
                            "Unexpected lexeme: \"{0}\" at position: \"{1}\"",
                            lexeme.value, lexemeBuffer.getPos()));
                }
                return value;
            default:
                throw new IllegalArithmeticExpression(MessageFormat.format(
                        "Unexpected lexeme: \"{0}\" at position: \"{1}\"",
                        lexeme.value, lexemeBuffer.getPos()));
        }
    }

    @Override
    public int visit(MulDivElement mulDiv) {
        int value = visit(new FactorElement());
        while (true) {
            Lexeme lexeme = lexemeBuffer.next();
            switch (lexeme.type) {
                case MUL -> value *= visit(new FactorElement());
                case DIV -> value /= visit(new FactorElement());
                case EOF, RIGHT_BRACKET, PLUS, MINUS -> {
                    lexemeBuffer.back();
                    return value;
                }
                default -> throw new IllegalArithmeticExpression(MessageFormat.format(
                        "Unexpected lexeme: \"{0}\" at position: \"{1}\"",
                        lexeme.value, lexemeBuffer.getPos()));
            }
        }
    }
}
