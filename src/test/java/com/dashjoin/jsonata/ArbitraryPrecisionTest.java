package com.dashjoin.jsonata;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Stream;

import static com.dashjoin.jsonata.Jsonata.jsonata;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArbitraryPrecisionTest {

    static Stream<Arguments> cases() {
        return Stream.of(
                // subtraction
                Arguments.of("0.8 - 0.1", new BigDecimal("0.7"), Map.of()),
                Arguments.of("a - b", new BigDecimal("0.7"), Map.of("a", 0.8, "b", 0.1)),
                // addition
                Arguments.of("0.8 + 0.1", new BigDecimal("0.9"), Map.of()),
                Arguments.of("a + b", new BigDecimal("0.9"), Map.of("a", 0.8, "b", 0.1)),
                // multiplication
                Arguments.of("0.8 * 0.1", new BigDecimal("0.08"), Map.of()),
                Arguments.of("a * b", new BigDecimal("0.08"), Map.of("a", 0.8, "b", 0.1)),
                // division
                Arguments.of("0.8 / 0.1", new BigDecimal("8"), Map.of()),
                Arguments.of("a / b", new BigDecimal("8"), Map.of("a", 0.8, "b", 0.1))
        );
    }

    @ParameterizedTest
    @MethodSource("cases")
    void test(String expression, BigDecimal expected, Map<String, Object> input) {
        Jsonata expr = jsonata(expression);
        var res = expr.evaluate(input);
        assertEquals(expected, res);
    }
}
