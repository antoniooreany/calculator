package com.microfocus.test.gorshkov;

import com.microfocus.test.gorshkov.Utils.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    @Test
    void calculate() {
        assertEquals(846_560, Calculator.calculate(
                "-37* (555 +          25* (34 - 21)) * -26"));
    }
}