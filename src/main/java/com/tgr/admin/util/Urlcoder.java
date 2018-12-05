package com.tgr.admin.util;

import java.io.UnsupportedEncodingException;

public class Urlcoder {
	public static void main(String[] args) {
		String encode = encode("中国");
		System.out.println(encode);
		
		String decode = decode(encode);
		System.out.println(decode);
	}
	
	/**
	 * 加密
	 * @return
	 */
	public static String encode(String url){
		try {
			return java.net.URLEncoder.encode(url,   "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 解码
	 * @param urlencode
	 * @return
	 */
	public static String decode(String urlencode){
		try {
			return java.net.URLDecoder.decode(urlencode,   "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
