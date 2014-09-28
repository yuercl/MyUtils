package com.yuer.ip.test;

import java.io.RandomAccessFile;

import org.junit.Test;

import com.yuer.ip.QQWryFile;
import com.yuer.ip.QQWryRecord;
import com.yuer.ip.Utils;

public class QQWryTest {

	@Test
	public void testFindIP() throws Exception {
		String ip = "202.108.22.5";
		// ip = "202.108.9.155";

		QQWryFile qqWryFile = QQWryFile.getInstance();
		RandomAccessFile ipFile = qqWryFile.getIpFile();
		QQWryRecord record = qqWryFile.find(ip, ipFile);

		System.out.println(Utils.ipToStr(record.getBeginIP()));
		System.out.println(Utils.ipToStr(record.getEndIP()));
		System.out.println(record.getCountry());
		System.out.println(record.getArea());

		// System.out.println(Utils.ipToStr(16842751));
		// System.out.println(Utils.ipToLong(ip));

		qqWryFile.closeIpFile(ipFile);
		qqWryFile = null;
	}

}