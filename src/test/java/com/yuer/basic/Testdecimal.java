package com.yuer.basic;

import java.math.BigDecimal;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

public class Testdecimal {

	@Test
	public void testBigDecimal() {
		System.out.println(2.3 * 100);

		System.out.println(Double.parseDouble("2.3") * 100);
		System.out.println(Double.parseDouble("2.29") * 100);
		System.out.println(Double.parseDouble("2") * 100);
		System.out.println(Double.parseDouble("2.2") * 100);
		System.out.println("----");

		BigDecimal bg = new BigDecimal("2.3").multiply(new BigDecimal(100));
		BigDecimal bg2 = new BigDecimal("2.3").multiply(new BigDecimal("100"));
		BigDecimal bg3 = new BigDecimal(2.3).multiply(new BigDecimal(100));
		BigDecimal bg4 = new BigDecimal(2.3).multiply(new BigDecimal("100"));
		System.out.println(bg);
		System.out.println(bg2);
		System.out.println(bg3);
		System.out.println(bg4);

		System.out.println("----");
		System.out.println(NumberUtils.toDouble("2.3") * 100);
		System.out.println(NumberUtils.toDouble("2.29") * 100);
		System.out.println(NumberUtils.toDouble("2") * 100);
		System.out.println(NumberUtils.toDouble("2.2") * 100);
	}
}