package com.yu.web.base.util;

import java.math.BigDecimal;

import org.springframework.util.StringUtils;

public class AmountUtil {
	public static String changeY2F(String amount){
		if(StringUtils.isEmpty(amount)){
			return null;
		}
		BigDecimal yuan = new BigDecimal(amount);
		BigDecimal y100 = new BigDecimal(100);
		BigDecimal fen = yuan.multiply(y100).setScale(0,BigDecimal.ROUND_HALF_UP);
		return fen.toString();
	}
	
	public static String changeF2Y(String amount){
		if(StringUtils.isEmpty(amount)){
			return null;
		}
		if("0".equals(amount)) return "0";
		BigDecimal fen = new BigDecimal(amount);
		BigDecimal y100 = new BigDecimal(100);
		BigDecimal yuan = fen.divide(y100).setScale(2,BigDecimal.ROUND_HALF_UP);
		String yuanStr = yuan.toString();
		if(yuanStr.charAt(yuanStr.length() - 1) == '0' && yuanStr.charAt(yuanStr.length() - 2) == '0'){
			yuanStr = yuanStr.substring(0, yuanStr.indexOf("."));
		}else if(yuanStr.charAt(yuanStr.length() - 1) == '0' && yuanStr.charAt(yuanStr.length() - 2) != '0'){
			yuanStr = yuanStr.substring(0, yuanStr.indexOf(".")+2);
		}
		return yuanStr;
	}
	
	public static String changeF2Y(Integer amount){
		if(StringUtils.isEmpty(amount)){
			return null;
		}
		return changeF2Y(String.valueOf(amount));
	}
	
	public static void main(String[] args) {
		System.out.println(changeY2F("100.1123"));
		System.out.println(changeF2Y("13929211"));
	}
	
}
