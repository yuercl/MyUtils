package com.yuer.dbutils.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
	
	public static final long MILLIS_PER_SECOND = 1000L;
	public static final long MILLIS_PER_MINUTE = 60000L;
	public static final long MILLIS_PER_HOUR = 3600000L;
	public static final long MILLIS_PER_DAY = 86400000L;
	public static final int SEMI_MONTH = 1001;
	public static final int RANGE_WEEK_SUNDAY = 1;
	public static final int RANGE_WEEK_MONDAY = 2;
	public static final int RANGE_WEEK_RELATIVE = 3;
	public static final int RANGE_WEEK_CENTER = 4;
	public static final int RANGE_MONTH_SUNDAY = 5;
	public static final int RANGE_MONTH_MONDAY = 6;
	public static final String DEFAULT_TIME_FORMAT = "yyyyMMddHHmmss";
	public static final Locale DEFAULT_LOCALE = Locale.getDefault();

	public static Date parse(String date, String fromat,
			Locale locale) throws ParseException {
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
		        fromat == null ? "yyyyMMddHHmmss" : fromat,
		                locale == null ? DEFAULT_LOCALE : locale);
		Date localDate = localSimpleDateFormat.parse(date);
		return localDate;
	}

	public static Date parse(String date, String format)
			throws ParseException {
		return parse(date, format, DEFAULT_LOCALE);
	}

	public static String format(long time, String format) {
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				format == null ? "yyyyMMddHHmmss" : format);
		String str = localSimpleDateFormat.format(new Date(time));
		return str;
	}
	
	public static String format(Timestamp date, String format) {
        return format(new Date(date.getTime()), format, DEFAULT_LOCALE);
    }
	
	public static String format(Date date, String format) {
		return format(date, format, DEFAULT_LOCALE);
	}

	public static String format(Date date, String format,
			Locale locale) {
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
				format == null ? "yyyyMMddHHmmss" : format,
				        locale == null ? DEFAULT_LOCALE : locale);
		String str = localSimpleDateFormat.format(date);
		return str;
	}

	/**
	 * @param 要转换的毫秒数
	 * @return 该毫秒数转换为 * days * hours * minutes * seconds 后的格式
	 */
	public static String formatDuring(long mss) {
		long days = mss / (1000 * 60 * 60 * 24);
		long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
		long seconds = (mss % (1000 * 60)) / 1000;
		return days + " 天 " + hours + " 时 " + minutes + " 分 "
				+ seconds + " 秒 ";
	}
	
	/**
	 * @param begin 时间段的开始
	 * @param end	时间段的结束
	 * @return	输入的两个Date类型数据之间的时间间格用* days * hours * minutes * seconds的格式展示
	 */
	public static String formatDuring(Date begin, Date end) {
		return formatDuring(end.getTime() - begin.getTime());
	}
	
	public static long getNow() {
		return new Date().getTime();
	}
	
	public static Timestamp getNowTimestamp() {
		return new Timestamp(new Date().getTime());
	}
	
	public static String getNow(String format) {
		return getNow(format, DEFAULT_LOCALE);
	}

	public static String getNow(String format, Locale locale) {
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(
		        format == null ? "yyyyMMddHHmmss" : format,
		                locale == null ? DEFAULT_LOCALE : locale);
		String str = localSimpleDateFormat.format(new Date());
		return str;
	}

	public static boolean isSameDay(Date paramDate1, Date paramDate2) {
		if ((paramDate1 == null) || (paramDate2 == null))
			throw new IllegalArgumentException("The date must not be null");
		Calendar localCalendar1 = Calendar.getInstance();
		localCalendar1.setTime(paramDate1);
		Calendar localCalendar2 = Calendar.getInstance();
		localCalendar2.setTime(paramDate2);
		return isSameDay(localCalendar1, localCalendar2);
	}

	public static boolean isSameDay(Calendar paramCalendar1,
			Calendar paramCalendar2) {
		if ((paramCalendar1 == null) || (paramCalendar2 == null))
			throw new IllegalArgumentException("The date must not be null");
		return (paramCalendar1.get(0) == paramCalendar2.get(0))
				&& (paramCalendar1.get(1) == paramCalendar2.get(1))
				&& (paramCalendar1.get(6) == paramCalendar2.get(6));
	}

	public static boolean isSameLocalTime(Calendar paramCalendar1,
			Calendar paramCalendar2) {
		if ((paramCalendar1 == null) || (paramCalendar2 == null))
			throw new IllegalArgumentException("The date must not be null");
		return (paramCalendar1.get(14) == paramCalendar2.get(14))
				&& (paramCalendar1.get(13) == paramCalendar2.get(13))
				&& (paramCalendar1.get(12) == paramCalendar2.get(12))
				&& (paramCalendar1.get(11) == paramCalendar2.get(11))
				&& (paramCalendar1.get(6) == paramCalendar2.get(6))
				&& (paramCalendar1.get(1) == paramCalendar2.get(1))
				&& (paramCalendar1.get(0) == paramCalendar2.get(0))
				&& (paramCalendar1.getClass() == paramCalendar2.getClass());
	}

	public static Date addYears(Date paramDate, int paramInt) {
		return add(paramDate, 1, paramInt);
	}

	public static Date addMonths(Date paramDate, int paramInt) {
		return add(paramDate, 2, paramInt);
	}

	public static Date addWeeks(Date paramDate, int paramInt) {
		return add(paramDate, 3, paramInt);
	}

	public static Date addDays(Date paramDate, int paramInt) {
		return add(paramDate, 5, paramInt);
	}

	public static Date addHours(Date paramDate, int paramInt) {
		return add(paramDate, 11, paramInt);
	}

	public static Date addMinutes(Date paramDate, int paramInt) {
		return add(paramDate, 12, paramInt);
	}

	public static Date addSeconds(Date paramDate, int paramInt) {
		return add(paramDate, 13, paramInt);
	}

	public static Date addMilliseconds(Date paramDate, int paramInt) {
		return add(paramDate, 14, paramInt);
	}

	private static Date add(Date paramDate, int paramInt1, int paramInt2) {
		if (paramDate == null)
			throw new IllegalArgumentException("The date must not be null");
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.setTime(paramDate);
		localCalendar.add(paramInt1, paramInt2);
		return localCalendar.getTime();
	}

	public static Date setYears(Date paramDate, int paramInt) {
		return set(paramDate, 1, paramInt);
	}

	public static Date setMonths(Date paramDate, int paramInt) {
		return set(paramDate, 2, paramInt);
	}

	public static Date setDays(Date paramDate, int paramInt) {
		return set(paramDate, 5, paramInt);
	}

	public static Date setHours(Date paramDate, int paramInt) {
		return set(paramDate, 11, paramInt);
	}

	public static Date setMinutes(Date paramDate, int paramInt) {
		return set(paramDate, 12, paramInt);
	}

	public static Date setSeconds(Date paramDate, int paramInt) {
		return set(paramDate, 13, paramInt);
	}

	public static Date setMilliseconds(Date paramDate, int paramInt) {
		return set(paramDate, 14, paramInt);
	}

	private static Date set(Date paramDate, int paramInt1, int paramInt2) {
		if (paramDate == null)
			throw new IllegalArgumentException("The date must not be null");
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.setLenient(false);
		localCalendar.setTime(paramDate);
		localCalendar.set(paramInt1, paramInt2);
		return localCalendar.getTime();
	}

	public static Calendar toCalendar(Date paramDate) {
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.setTime(paramDate);
		return localCalendar;
	}
	
	public static Timestamp parseTimestamp(String dateS, String format) throws ParseException {
	    Date date = parse(dateS, format);
	    return new Timestamp(date.getTime());
	}

	/**
	 * 比较两个时间大小
	 * @param date1
	 * @param date2
	 * @return date1大于date2 返回1，date1等于date2 返回0, date1少于date2 返回-1
	 */
	public static int compare(Date date1, Date date2) {
		return date1.compareTo(date2);
	}
	
}