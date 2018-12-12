package com.tgr;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.client.RestTemplate;

import com.tgr.admin.nosql.RedisService;


/*
 * 	@SpringBootApplication 
 * 注解包含了
 * 			@SpringBootConfiguration  注释其是一个spring-boot入口 
 * 			@EnableAutoConfiguration 和
 * 			@ComponentScan 	开启了包扫 描、 配置和 自动配置 的功能 。
*/


//@EnableAdminServer 暂时不能用 其所依赖的其他bean我还没有
@SpringBootApplication//
//@ComponentScan(basePackages= {"com.tgr.security"})//spring扫描我的security配置
@SuppressWarnings("all")
public class AdminApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
	}
	
	@Autowired
	private RestTemplateBuilder restTemplateBuilder;

	public RestTemplate restTemplate() {
		return restTemplateBuilder.build();
	}
	
	@Bean
	public <V> RedisService<V> redisService(RedisConnectionFactory factory) {
		RedisService<V> redis = new RedisService<V>(factory);
		redis.afterPropertiesSet();
		return redis;
	}
	
	/**
	 * spring-boot 文件配置
	 * 
	 * @return
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("100Mb");
		factory.setMaxRequestSize("1000Mb");
		factory.setLocation("E:\\tempFile");
		return factory.createMultipartConfig();
	}
	
}
