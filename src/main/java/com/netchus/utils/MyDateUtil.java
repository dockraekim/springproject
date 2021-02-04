package com.netchus.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Instant;

public class MyDateUtil {
	
	static final String YYYYMMDDHHMM00 = "yyyy-MM-dd HH:mm:00";
	static final String YYYYMMDDTHHMM00 = "yyyy-MM-dd'T'HH:mm:00";
	static final String YYYYMMDDTHHMMSS = "yyyy-MM-dd'T'HH:mm:ss";
	
	// 날짜포멧형식
	public static final String YYYYMM = "yyyy-MM";
	public static final String YYYYMMDOT = "yyyy.MM.dd";
	public static final String YYYYMMDD = "yyyy-MM-dd";
	public static final String YYYYMMDDHH = "yyyy-MM-dd HH";
	public static final String YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
	public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	
	// 시간형식
	public static final String HHMMSS = "HH:mm:ss";
	public static final String HHMM = "HH:mm";

	
	public static String getDefaultDate() {
		return DateTime.now().toString(YYYYMMDDTHHMMSS);
	}


	/**
	 * @Mehtod Name : parse
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date parse(String date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @Mehtod Name : parse
	 * @param date
	 * @return
	 */
	public static Date parse(String date) {

		date = date.replaceAll("\\W", "");

		if (date.length() == 8) {
			return MyDateUtil.parse(date, "yyyyMMdd");
		} else if (date.length() == 12) {
			return MyDateUtil.parse(date, "yyyyMMddHHmm");
		} else if (date.length() == 14) {
			return MyDateUtil.parse(date, "yyyyMMddHHmmss");
		}
		return null;
	}
	
	/**
	 * @Mehtod Name : format
	 * @param s
	 * @param format
	 * @return
	 */
	public static String format(String s, String format) {
		
		if (s == null) {
			return null;
		}
		
		Calendar c = toCalendar(s);
		return format(c, format);
	}
	
	/**
	 * @Mehtod Name : format
	 * @param cal
	 * @param format
	 * @return
	 */
	public static String format(Calendar cal, String format) {
		
		if (cal == null) {
			return null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
	}
	
	/**
	 * @Mehtod Name : toCalendar
	 * @param date
	 * @return
	 */
	public static Calendar toCalendar(String date) {

		Calendar cal = Calendar.getInstance();

		if (date.length() == 8) {
			cal.setTime(parse(date, "yyyyMMdd"));
		} else if (date.length() == 7) {
			cal.setTime(parse(date, "yyyy-MM"));
		} else if (date.length() == 10) {
			cal.setTime(parse(date, "yyyy-MM-dd"));
		} else if (date.length() == 12) {
			cal.setTime(parse(date, "yyyyMMddHHmm"));
		} else if (date.length() == 14) {
			cal.setTime(parse(date, "yyyyMMddHHmmss"));
		}

		return cal;
	}
	
	
	/**
	 * 기본 포멧을 이용하여 날짜를 돌려준다.
	 * yyyyMMdd HHddss
	 * @Mehtod Name : formatYmdhms
	 * @return
	 */
	public static String formatYmdhms(String s) {
		return MyDateUtil.format(s, YYYYMMDDHHMMSS);
	}
	
	/**
	 * 기본 포멧을 이용하여 날짜를 돌려준다.
	 * yyyyMMdd HHdd
	 * @Mehtod Name : formatYmdhm
	 * @param cal
	 * @return
	 */
	public static String formatYmdhm(Calendar cal) {
		return MyDateUtil.format(cal, YYYYMMDDHHMM);
	}
	
	@SuppressWarnings("deprecation")
	public static String viewFormatter(String dateStr) {
		
		// 1. date 형식으로 바꾸기
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sf = new SimpleDateFormat(YYYYMMDDTHHMM00);
		Date d = null;
		try {
			d = sf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		// 2. pattern 다시 바꾸고
		String pattern = "YYYYMMDDHHMM00";
		SimpleDateFormat sdft = new SimpleDateFormat(pattern);
		
		System.out.println(sdft.format(d));
		// 3. String
		
		return sdft.format(d);
	}
	
	public static String minFormatter(int min) {
		
		String pattern = YYYYMMDDTHHMMSS;
		SimpleDateFormat sdft = new SimpleDateFormat(pattern);
		// 패턴 선언
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, -((min-1)*60+59));
		Date d = cal.getTime();
		
		return sdft.format(d);
	}
	
	/*
	 * 내가 짠 부분 시작 
	*/
	
	public static void main(String[] args) {
		// 시작 시간 설정하기
//		System.out.println(minFormatter(0));
//		System.out.println(minFormatter(1));
		
		Calendar cal = Calendar.getInstance();
		System.out.println(MyDateUtil.format(cal, YYYYMMDDTHHMMSS));
		System.out.println(MyDateUtil.minFormatter(1));
		System.out.println(MyDateUtil.getPrevMin(cal, 1));
	}
	
	
	public static String getPrevMin(Calendar cal, int min) {
		// 받아온 인자의 calendar에서 min 분 전꺼 가져오기
		System.out.println("method start");
		
		cal.add(cal.MINUTE, -min);
		String s = MyDateUtil.format(cal,YYYYMMDDHHMMSS);

		System.out.println("method end");
		
		return s.toString();
	}
	
}
