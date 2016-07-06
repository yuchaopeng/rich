package com.yu.base.util;

import java.util.HashMap;
import java.util.Map;

public class ReturnMapUtil {
	public static Map<String,Object> getErrorMap(String errorCode,String errorMsg,Map<String,Object> map){
		if(map == null){
			map = new HashMap<String,Object>();
		}
		map.put("result", errorCode);
		map.put("message", errorMsg);
		return map;
	}
	
	public static Map<String,Object> getErrorMap(String errorCode,String errorMsg){
		return getErrorMap(errorCode, errorMsg, null);
	}
}	
