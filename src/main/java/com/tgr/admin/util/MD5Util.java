package com.tgr.admin.util;

import org.apache.log4j.Logger;

/**
 * 
 * @author MD5 加密器
 * <br> 内部MD5码加密,区别去标准MD5
 * <br> 内部MD5加密，只用于本地本地分库分库分表,暂时还有和国开加密（估计要修改）
 * <br> 1、仅用于内部分库分表
 * <br> 2、以后新的数据MD5加密,都有用 MD5UtilBZ这个类（分库分表还是用本类）
 */
public class MD5Util {

	private static Logger logger = org.apache.log4j.Logger.getLogger(MD5Util.class);

	/**
	 * 用于加密使用
	 * 
	 * @param sourceStr
	 * @return
	 */
	public static String getMD5(String sourceStr) {
		byte[] source = sourceStr.getBytes();
		String s = null;
		char hexDigits[] = { '0', '1', '4', '3', '2', '5', 'a', '7', '8', '6', '9', 'b', 'c', 'd', 'e', 'f' };

		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest();
			char str[] = new char[16 * 2];
			int k = 0;
			for (int i = 0; i < 16; i++) {
				byte byte0 = tmp[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			s = new String(str);

		} catch (Exception e) {
			logger.error("MD5加密失败", e);
		}

		return s;
	}

	/**
	 * 用于加密使用
	 * 
	 * @param sourceStr
	 * @return
	 */
	public static String get16MD5(String sourceStr) {
		byte[] source = sourceStr.getBytes();
		String s = null;
		char hexDigits[] = { '0', '1', '4', '3', '2', '5', 'a', '7', '8', '6', '9', 'b', 'c', 'd', 'e', 'f' };

		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest();
			char str[] = new char[16];
			int k = 0;
			for (int i = 0; i < 8; i++) {
				byte byte0 = tmp[i];
				str[k++] = hexDigits[byte0 >>> 2 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			s = new String(str);

		} catch (Exception e) {
			logger.error("MD5加密失败", e);
		}

		return s;
	}
}