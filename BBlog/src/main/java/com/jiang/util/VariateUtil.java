package com.jiang.util;

public class VariateUtil {

	public static boolean isEmpty(String str) {
		if (str == null || (str.trim()).equals(""))
			return true;
		else
			return false;
	}

	/**
	 * 分页页码除空
	 * @param pageString
	 * @return
	 */
	public static Integer solveNullPage(String pageStr) {
		if (VariateUtil.isEmpty(pageStr)) {
			pageStr = "1";
		}
		return Integer.parseInt(pageStr);
	}
}
