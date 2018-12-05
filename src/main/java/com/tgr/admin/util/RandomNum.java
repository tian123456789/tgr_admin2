package com.tgr.admin.util;

import java.security.SecureRandom;

public class RandomNum extends java.util.Random{
	private static final long serialVersionUID = -3952301275620252901L;
	/**
	 * 每位允许的字符
	 */
	private static final String POSSIBLE_CHARS = "0123456789";

	/**
	 * 生产一个指定长度的随机字符串
	 * 
	 * @param length
	 *            字符串长度
	 * @return
	 */
	public String generateRandomString(int length) {
		StringBuilder sb = new StringBuilder(length);
		SecureRandom random = new SecureRandom();
		for (int i = 0; i < length; i++) {
			sb.append(POSSIBLE_CHARS.charAt(random.nextInt(POSSIBLE_CHARS.length())));
		}
		return sb.toString();
	}
}
