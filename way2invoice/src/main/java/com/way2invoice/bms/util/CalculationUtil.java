package com.way2invoice.bms.util;

import java.math.BigDecimal;
import java.util.function.BinaryOperator;

/**
 * A special util class for performing all the calculation
 */
public class CalculationUtil {

  public static final BinaryOperator<BigDecimal> fnDecimalMultiplication = BigDecimal::multiply;

  public static final BinaryOperator<BigDecimal> fnCalculateTax = (amount, tax) -> amount
      .multiply(tax.divide(BigDecimal.valueOf(100)));

  private CalculationUtil() {
  }
}
