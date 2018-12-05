package com.tgr.admin.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class AppUtil implements ServletContextListener {
 
    private static WebApplicationContext springContext;
    public AppUtil() {
        super();
    }
    public void contextInitialized(ServletContextEvent event) {
        springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
    }
    public void contextDestroyed(ServletContextEvent event) {
    }
    public static ApplicationContext getApplicationContext() {
        return springContext;
    }
    public static <T> T getBean(String bean,Class<T> clazz){
    	return springContext.getBean(bean, clazz);
    }
    public static Object getBean(String bean){
    	return springContext.getBean(bean);
    }
	public static String getAbsolutePath() {
		return springContext.getServletContext().getRealPath("/");
	}
	public static String getContextPath() {
		return springContext.getServletContext().getContextPath();
	}
} 
