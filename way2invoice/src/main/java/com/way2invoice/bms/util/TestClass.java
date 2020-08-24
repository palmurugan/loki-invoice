package com.way2invoice.bms.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class TestClass {

  public static void main(String[] args) {
    String number = "1";
    String prefix = "INV";
    System.out.println(String.format("%s-%06d", prefix,Long.parseLong(number)));
    System.out.println(String.format("BIL-%06d", Long.parseLong(number)));
  }
}
