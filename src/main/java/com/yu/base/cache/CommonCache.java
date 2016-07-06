package com.yu.base.cache;

import java.util.HashMap;
import java.util.Map;

public class CommonCache {
	public static String WEB_SITE_CONFIG_KEY = "WEB_SITE_CONFIG_KEY";
	
	private static Map<String,Object> commonCache = new HashMap<>();
	
	public static void put(String key,Object value){
		commonCache.put(key, value);
	}
	
	public static Object get(String key){
		return commonCache.get(key);
	}
}
