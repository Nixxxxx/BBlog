package com.jiang.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

	/**
	 * 获取用户IP
	 * @param request
	 * @return
	 */
	public static String getRemoteIP(HttpServletRequest request) {  
		if (request.getHeader("x-forwarded-for") == null) {  
			return request.getRemoteAddr();  
		}  
		return request.getHeader("x-forwarded-for");
	} 
	
}
