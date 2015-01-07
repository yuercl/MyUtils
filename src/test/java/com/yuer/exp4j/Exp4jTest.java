package com.yuer.exp4j;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import org.junit.Test;

public class Exp4jTest {

	@Test
	public void testExp() {
		Expression e = new ExpressionBuilder("3 * sin(y) - 2 / (x - 2)").variables("x", "y").build().setVariable("x", 2.3).setVariable("y", 3.14);
		double result = e.evaluate();
		System.out.println(result);

		e = new ExpressionBuilder("3 *  x ^ 2 + 4 * x + 5").variables("x").build().setVariable("x", 2);
		System.out.println(e.evaluate());
	}
}
