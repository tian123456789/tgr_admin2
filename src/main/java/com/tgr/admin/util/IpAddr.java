package com.tgr.admin.util;

import javax.servlet.http.HttpServletRequest;

public class IpAddr {
	
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader(" x-forwarded-for ");
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" WL-Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if(ip != null && ip.equals("0:0:0:0:0:0:0:1")){
			ip = "127.0.0.1";
		}
		//通过代理登陆
		if(ip != null && ip.length() > 15){
			ip = ip.substring(0, ip.indexOf(","));
		}
		return ip;
	}
}
