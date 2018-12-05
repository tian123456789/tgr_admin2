package com.tgr.admin.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUitl {
	/**
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date getBeforeDay(Date date, int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DATE);
		calendar.set(Calendar.DATE, day - n);
		return calendar.getTime();
	}

	/**
	 * 获取当前日期的前N天
	 * 
	 * @param n
	 * @return
	 */
	public static Date getCurrentDay(int n) {
		return getBeforeDay(new Date(), n);
	}

	/**
	 * 时间转换
	 * 
	 * @param date
	 * @param pattern
	 */
	public static String dateToString(Date date, String pattern) {
		if (pattern == null || "".equals(pattern)) {
			pattern = "yyyy-MM-dd";
		}
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}

	public static String dateString(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		return dateFormat.format(date);
	}
	
	/**
	 * 时间转换'yyyy-MM-dd'
	 * 
	 * @param date
	 */
	public static String dateToString(Date date) {
		return dateToString(date, null);
	}

	/**
	 * 时间转换
	 * 
	 * @param date
	 * @param pattern
	 */
	public static Date stringToDate(String source, String pattern) {
		if (pattern == null || "".equals(pattern)) {
			pattern = "yyyy-MM-dd";
		}
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		Date parse = null;
		try {
			parse = dateFormat.parse(source);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return parse;
	}

	/**
	 * 时间转换'yyyy-MM-dd'
	 * 
	 * @param date
	 */
	public static Date stringToDate(String source) {
		return stringToDate(source, null);
	}

	/**
	 * 将现在的时间转化为标准格式输出
	 * 
	 * @return
	 */
	public static String Date() {
		String date = null;

		try {
			date = formateDate(new Date(), "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 时间格式转化yyyy-MM-dd HH:mm:ss
	 */
	public static String Date(Date date) {
		String date1 = null;

		try {
			date1 = formateDate(date, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date1;
	}
	

	public static String DateString(Date date) {
		String date1 = null;

		try {
			date1 = formateDate(date, "yyyy年MM月dd日 HH:mm");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date1;
	}
	
	/**
	 * 时间格式转化yyyyMMddHHmmssSSS
	 */
	public static String dateStr(Date date) {
		String date1 = null;

		try {
			date1 = formateDate(date, "yyyyMMddHHmmssSSS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date1;
	}

	/**
	 * 按照一定的格式将日期格式输出
	 */
	public static String formateDate(Date resource, String typeString) throws Exception {
		try {
			return new SimpleDateFormat(typeString).format(resource);
		} catch (Exception e) {
			throw new Exception("日期格式化出错！" + e.getMessage());
		}
	}

	/**
	 * 比较两个时间大小： 前者大返回 1，前者小返回 -1，相等返回0
	 * 
	 * @param date1
	 * @param date2
	 */
	public static int diffmin(Date date1, Date date2) {
		if (date1.getTime() - date2.getTime() > 0) {
			return 1;
		} else if (date1.getTime() - date2.getTime() < 0) {
			return -1;
		} else {
			return 0;
		}
	}
	
	/**
	 * 获取当前时候的周
	 * @author lvwenwen
	 * @dateTime 2016-7-27 下午12:13:54
	 * @description  
	 * @param i  1星期日---7星期六
	 * @param pattern
	 * @return
	 */
	public static Date getweekday(int i, String pattern){
		Calendar rili=Calendar.getInstance();
		rili.set(Calendar.DAY_OF_WEEK, i);
		String s = DateUitl.dateToString(rili.getTime(), pattern);
		return stringToDate(s, pattern);
	}
	
	public static void main(String[] args) {
		Date beforeDay = getBeforeDay(new Date(), 1);
		String dateToString = dateToString(beforeDay);
		System.out.println(dateToString);
	}
}
