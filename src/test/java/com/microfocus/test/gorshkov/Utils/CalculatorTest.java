package com.microfocus.test.gorshkov.Utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void getResult() {
        assertEquals(846_560, Calculator.getResult(
                "-37* (555 +          25* (34 - 21)) * -26"));
    }
}