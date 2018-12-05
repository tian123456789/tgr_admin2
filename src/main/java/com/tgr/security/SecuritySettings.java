package com.tgr.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@SuppressWarnings("unused")
@ConfigurationProperties(prefix = "securityconfig")//设置参数前缀
public class SecuritySettings {
	
	private String logoutsuccessurl = "/logout";//退出成功练剑
	private String permitall = "/api";//允许访问的url列表
	private String deniedpage = "/deny";//设定拒绝访问的信息提示链接
	private String urlroles;//权限管理规则,是链接地址与角色权限的配置列表
	
	public String getLogoutsuccessurl() {
		return logoutsuccessurl;
	}
	public void setLogoutsuccessurl(String logoutsuccessurl) {
		this.logoutsuccessurl = logoutsuccessurl;
	}
	public String getPermitall() {
		return permitall;
	}
	public void setPermitall(String permitall) {
		this.permitall = permitall;
	}
	public String getDeniedpage() {
		return deniedpage;
	}
	public void setDeniedpage(String deniedpage) {
		this.deniedpage = deniedpage;
	}
	public String getUrlroles() {
		return urlroles;
	}
	public void setUrlroles(String urlroles) {
		this.urlroles = urlroles;
	}
	
	
}
