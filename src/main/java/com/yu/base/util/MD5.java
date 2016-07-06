package com.yu.base.util;

public class MD5 {
	
	public static String md5(String str) throws Exception{
		return HashFile.getHashByString(str, HashFile.MD5);
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(md5("123456"));
	}
}
