package com.jiang.util;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 加密工具
 * @author Administrator
 *
 */
public class CryptographyUtil {

	/**
	 * Md5加密
	 * @param str
	 * @param salt
	 * @return
	 */
	public static String md5(String str, String salt){
		return new Md5Hash(str,salt).toString();
	}
	
	/**
	 * base64加密
	 * @param str
	 * @return
	 */
	public static String encBase64(String str){
		return Base64.encodeToString(str.getBytes());
	}
	
	/**
	 * base64解密
	 * @param str
	 */
	public static String decBase64(String str){
		return Base64.decodeToString(str);
	}
}
