package com.tgr;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	/**
	 * {@inheritDoc}
	 * <p>This implementation is empty.
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**")
				.addResourceLocations("classpath:/js/");
		registry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
	
	/**
	 * {@inheritDoc}
	 * <p>This implementation is empty.
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//registry.addViewController("/login").setViewName("login");;
		 // registry.addViewController("/").setViewName("index");//
		
	}
}
