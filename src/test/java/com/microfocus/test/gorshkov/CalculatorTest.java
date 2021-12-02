package com.microfocus.test.gorshkov;

import com.microfocus.test.gorshkov.Utils.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    @Test
    void calculate() {
        assertEquals(360, Calculator.calculate(
                "-3* (55 + 5* (3 - 2)) * -2"));
    }
}