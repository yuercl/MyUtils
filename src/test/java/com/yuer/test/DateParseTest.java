package com.yuer.test;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

public class DateParseTest {

	@Test
	public void testDate() throws ParseException {
		String[] pattern = new String[] { "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyyMMdd", "yyyy-MM-dd", "yyyy/MM/dd", "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss",
				"yyyy/MM/dd HH:mm:ss" };
		Date date = DateUtils.parseDate("20140702", pattern);
		Date date2 = DateUtils.parseDate("2014-07-02", pattern);
		Date date3 = DateUtils.parseDate("2014/07", pattern);
		Date date4 = DateUtils.parseDate("201407", pattern);
		Date date5 = DateUtils.parseDate("2014-07-02 11:20:30", pattern);
		Date date6 = DateUtils.parseDate("2014/07/02 11:20:30", pattern);
		Date date7 = DateUtils.parseDate("20140702112030", pattern);
		System.out.println(date);
		System.out.println(date2);
		System.out.println(date3);
		System.out.println(date4);
		System.out.println(date5);
		System.out.println(date6);
		System.out.println(date7);
	}
}
