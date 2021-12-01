package com.microfocus.test.gorshkov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    @Test
    void calculate() {
        assertEquals(300, Calculator.calculate("-3* (-55 + 5* (3 - 2)) * 2"));
    }
}