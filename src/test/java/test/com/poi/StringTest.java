package test.com.poi;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class StringTest {

	@Test
	public void stringTest() {
		String s = "ssss";
		s = StringUtils.leftPad(s, 10, "a");
		System.out.println(s);
	}
}
