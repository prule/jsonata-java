package com.dashjoin.jsonata;

import static com.dashjoin.jsonata.Jsonata.jsonata;
import static java.util.Map.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import com.dashjoin.jsonata.json.Json;

import java.math.BigDecimal;

public class NumberTest {

  /**
   * this case fails, because the double value 1.0 is "untouched"
   */
  @Disabled
  @Test
  public void testDouble() {
    Jsonata expr1 = jsonata("x");
    var res = expr1.evaluate(of("x", 1.0));
    assertEquals(1, res);
  }

  /**
   * a computation is applied, and com.dashjoin.jsonata.Utils.convertNumber(Number) casts the double to int
   */
  @Test
  public void testDouble2() {
    Jsonata expr1 = jsonata("x+0");
    var res = expr1.evaluate(of("x", 1.0));
    assertTrue(BigDecimal.valueOf(1.0).compareTo((BigDecimal) res)==0);
  }

  /**
   * here, the JSON parser immediately converts double 1.0 to int 1
   */
  @Test
  public void testDouble3() {
    Jsonata expr1 = jsonata("x");
    var res = expr1.evaluate(Json.parseJson("{\"x\":1.0}"));
    assertEquals(1, res);
  }

  /**
   * "clean" the input using com.dashjoin.jsonata.Utils.convertNumber(Number)
   */
  @Test
  public void testDouble4() {
    Jsonata expr1 = jsonata("x");
    var res = expr1.evaluate(of("x", BigDecimal.valueOf(1.0)));
    assertTrue(BigDecimal.valueOf(1).compareTo((BigDecimal) res)==0);
  }

  /**
   * int 1 is converted to double when divided by 2
   */
  @Test
  public void testInt() {
    Jsonata expr1 = jsonata("$ / 2");
    var res = expr1.evaluate(BigDecimal.valueOf(1));
    assertEquals(BigDecimal.valueOf(0.5), res);
  }

  /**
   * JSONata constant 1.0 evaluates to 1
   */
  @Test
  public void testConst() {
    Jsonata expr1 = jsonata("1.0");
    var res = expr1.evaluate(null);
    assertEquals(BigDecimal.valueOf(1.0), res);
  }
}
