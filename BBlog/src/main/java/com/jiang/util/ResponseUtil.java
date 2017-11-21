package com.jiang.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class ResponseUtil {
	
	/**
	 * 向页面返回JSONObject对象
	 * @param response
	 * @param json
	 */
	public static void writeJson(HttpServletResponse response,JSONObject json){
		try {  
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
