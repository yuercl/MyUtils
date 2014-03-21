package com.yuer.snappy;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.xerial.snappy.Snappy;

public class TestSnappy {

	/**
	 * 压缩和解压，google出的 <br />
	 * https://github.com/xerial/snappy-java<br />
	 * https://code.google.com/p/snappy/
	 * 
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	@Test
	public void compress() throws UnsupportedEncodingException, IOException {
		String input = "Hello snappy-java! Snappy-java is a JNI-based wrapper of " + "Snappy, a fast compresser/decompresser.";
		byte[] compressed = Snappy.compress(input.getBytes("UTF-8"));
		byte[] uncompressed = Snappy.uncompress(compressed);
		String result = new String(uncompressed, "UTF-8");
		System.out.println(result);
	}

}