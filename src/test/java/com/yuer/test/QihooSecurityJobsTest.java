package com.yuer.test;
import org.junit.Test;

public class QihooSecurityJobsTest {
	@Test
	public void testDecode() {
		// 360安全招聘 十六进制题目
		String source = "253444253534253435253335253439253434253435253737253444253533253431253738253444253434253637253637253446253534253642253637253444253534253435253738253439253434253435253737253446253533253431253738253444253434253435253637253444253534253435253332253439253434253435253738253444253533253431253331253444253533253431253331253445253433253431253330253446253433253431253344";
		for (int i = 0; i < source.length() / 2; i++) {
			String item = source.substring(i * 2, i * 2 + 2);
			// System.out.println(item);
			int code = Integer.parseInt(item, 16);
			char result = (char) code;
			System.out.print(result);
		}
		// 然后result是urlencode格式（因为很多%），然后是base64格式（因为末尾有=）
	}
}
