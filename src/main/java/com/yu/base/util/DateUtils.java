package com.yu.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static final String format_yyyyyMMdd = "yyyy-MM-dd";
	
	public static final String format_yyyyMMdd_HHmmss = "yyyy-MM-dd HH:mm:ss";
	
	public static final String format_yyMMddHHmmss = "yyMMddHHmmss";
	
	public static String getDateString(Date date){
		if(date == null){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format_yyyyMMdd_HHmmss);
		return sdf.format(date);
	}
	
	public static String getSimpleDateString(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(format_yyyyyMMdd);
		return sdf.format(date);
	}
	
	public static Date getNowTime(){
		return Calendar.getInstance().getTime();
	}
	
	public static Date getSimpleDate(String dateStr) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(format_yyyyyMMdd);
		return sdf.parse(dateStr);
	}
	
	public static String getSimpleNowDateString(){
		SimpleDateFormat sdf = new SimpleDateFormat(format_yyMMddHHmmss);
		return sdf.format(getNowTime());
	}
	
	public static String getSimpleDateString(){
		SimpleDateFormat sdf = new SimpleDateFormat(format_yyyyyMMdd);
		return sdf.format(getNowTime());
	}
	
	public static String getPrevSimpleDateString(){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -1);
		return getSimpleDateString(c.getTime());
	}
}
