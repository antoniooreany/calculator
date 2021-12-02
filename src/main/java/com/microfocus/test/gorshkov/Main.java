/*
        Grammar0:
        STMT            : "(" STMT ")" | STMT OPERATOR STMT | INT;
        OPERATOR        : "+ "| "-" | "*" | "/";
        INT             : POSITIVE_DIGIT INT | POSITIVE_DIGIT DIGIT | DIGIT;
        POSITIVE_DIGIT  : "1"|"2"|"3"|"4"|"5"|"6"|"7"|"8"|"9";
        DIGIT           : POSITIVE_DIGIT | "0";

        Grammar1:
        STMT            : "(" STMT ")" | STMT OPERATOR STMT | FLOAT | INT;
        OPERATOR        : "+ "| "-" | "*" | "/";
        FLOAT           : INT "." INT;
        INT             : POSITIVE_DIGIT INT | POSITIVE_DIGIT DIGIT | DIGIT;
        POSITIVE_DIGIT  : "1"|"2"|"3"|"4"|"5"|"6"|"7"|"8"|"9";
        DIGIT           : POSITIVE_DIGIT | "0";

        STMT            : "(" STMT ")" | STMT OPERATOR STMT | FLOAT;
        OPERATOR        : "+ "| "-" | "*" | "/";
        FLOAT           : /\d+(\.\d+)?/;

    *** GRAMMAR ***
        Terminals:
        +  EOF ) ( FLOAT STOP * / - \d+(\.\d+)? EMPTY
        NonTerminals:
        OPERATOR S' STMT
        Productions:
        0: S' = STMT STOP
        1: STMT = STMT OPERATOR STMT
        2: STMT = ( STMT )
        3: STMT = FLOAT
        4: OPERATOR = +
        5: OPERATOR = -
        6: OPERATOR = *
        7: OPERATOR = /

        STMT : TERM | STMT ADDOP TERM ;
        TERM : FACTOR | FACTOR MULOP FACTOR ;
        FACTOR : "(" STMT ")" | NUMBER;
        ADDOP : "+" | "-";
        MULOP : "*"|"/";
        NUMBER: /\d+(\.\d+)?/;

        STMT :  STMT ADDOP STMT {1, left}
        |  STMT MULOP STMT {2, left}
        | "(" STMT ")" | NUMBER;
        ADDOP : "+" | "-";
        MULOP : "*"|"/";
        NUMBER: /\d+(\.\d+)?/;

        S → SS,
        S → (S),
        S → ()

        S → x
        S → y
        S → z
        S → S + S
        S → S – S
        S → S * S
        S → S / S
        S → (S)

        S → "0"|"1"|"2"|"3"|"4"|"5"|"6"|"7"|"8"|"9"
        S → S + S
        S → S – S
        S → S * S
        S → S / S
        S → (S)

// TODO the grammar have to be unambiguous grammar (by the operations and the braces prioritization)

        T → x
        T → y
        T → z
        S → S + T
        S → S – T
        S → S * T
        S → S / T
        T → (S)
        S → T

        T → "0"|"1"|"2"|"3"|"4"|"5"|"6"|"7"|"8"|"9"
        T → TT
        S → S "+" T
        S → S "–" T
        S → S "*" T
        S → S "/" T
        T → "("S")"
        S → T

https://en.wikipedia.org/wiki/Chomsky_hierarchy

         Expr → Expr + Term
         Expr → Term
         Term → Term * FactorElement
         Term → FactorElement
         FactorElement → "(" Expr ")"
         FactorElement → integer

        Expr    ← Sum
        Sum     ← Product (('+' / '-') Product)*
        Product ← Power (('*' / '/') Power)*
        Power   ← Value ('^' Power)?
        Value   ← [0-9]+ / '(' Expr ')'

        Value   ← [0-9.]+ / '(' Expr ')'
        Product ← Expr (('*' / '/') Expr)*
        Sum     ← Expr (('+' / '-') Expr)*
        Expr    ← Product / Sum / Value

  /////////////////////////////////////////////////////////////
  ///// FINALLY:///////////////////////////////////////////////

        result : plusMinus* EOF ;
        plusMinus: mulDiv ( ( '+' | '-' ) mulDiv )* ;
        mulDiv : factor ( ( '*' | '/' ) factor )* ;
        factor : unaryMinus | INT | '(' result ')' ;
        unaryMinus : '-' factor
 */

package com.microfocus.test.gorshkov;

import com.microfocus.test.gorshkov.Utils.Calculator;

import java.text.MessageFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String arithmeticExpression;
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Input an arithmetic expression and press <Enter>: ");
            arithmeticExpression = in.nextLine();
        }
        System.out.println(MessageFormat.format("The result of the calculation is: {0}",
                Calculator.calculate(arithmeticExpression)));
    }
}
