package com.dashjoin.jsonata;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import static java.util.Map.of;

import static com.dashjoin.jsonata.Jsonata.jsonata;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArbitraryPrecisionSubtractionTest {

    @Test
    public void testLiterals() {
        Jsonata expr1 = jsonata("0.8 - 0.1");
        var res1 = expr1.evaluate(new HashMap<>());
        assertEquals(new BigDecimal("0.7"), res1);
    }

    @Test
    public void testLiteralsViaContext() {
        Jsonata expr1 = jsonata("a - b");
        var res1 = expr1.evaluate(of("a", 0.8, "b", 0.1));
        assertEquals(new BigDecimal("0.7"), res1);
    }

}
