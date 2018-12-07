package com.tgr.admin.configPoepertiesToBean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//有问题 p65
@PropertySource(value = "classpath:configproperties.yaml")
@ConfigurationProperties(prefix = "bean1.test")//表明该类为配置属性类
@Component// Spring Boot 在启动时通过包扫描将该类作为一个 Bean 注入 IoC 容器中。
@SuppressWarnings("all")
public class ConfigBean {

	private String name;
	
	private String reverse;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReverse() {
		return reverse;
	}

	public void setReverse(String reverse) {
		this.reverse = reverse;
	}
	
	
	
}
