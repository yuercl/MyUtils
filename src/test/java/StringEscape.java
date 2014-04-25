import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;

public class StringEscape {
	@Test
	public void testStringEscape() {
		String testStr = "< > \" &";
		System.out.println("Escaped : " + StringEscapeUtils.escapeHtml4(testStr));
	}

	@Test
	public void testSystemOut() {
		System.out.println(500 + 20 + "java" + 1 + 1);

	}

}
