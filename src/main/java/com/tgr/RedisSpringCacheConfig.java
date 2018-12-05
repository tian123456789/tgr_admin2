package com.tgr;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author tgr
 * 
 * 该种缓存只能存储结构单一的对象   所以我注释掉了customer的关联对象
 * 
 *配置类启用springCache
 *@Cacheable   存取缓存
 *	param:
 *		  value {"",""}
 *		  key 支持SpringEL表达式
 *		  condition 触发条件
 *
 *@CachePut	      修改缓存	
 *	标注的方法在执行前不会去检查缓存中是否存在之前执行过的结果，而是每次都会执行该方法，
 *	并将执行结果以键值对的形式存入指定的缓存中
 *@CacheEviet  删除缓存
 */
@Configuration
@EnableCaching
@SuppressWarnings("all")
public class RedisSpringCacheConfig extends CachingConfigurerSupport{

	@Bean
	public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisTemplate redisTemplate) {
		RedisCacheManager manager = new RedisCacheManager(redisTemplate);
		manager.setDefaultExpiration(43200);//12小時  单位min
		return manager;
	}
	
	@Bean
	public KeyGenerator simpleKey() {
		return new KeyGenerator() {
			
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName()+":");
				for(Object obj : params) {
					sb.append(obj.toString());
				}
				System.out.println("simpleKey == "+sb.toString());
				return sb.toString();
				//simpleKey == com.tgr.admin.service.impl.CustomerSpringCacheServiceImpl:
			}
		};
	}
	
	@Bean
	public KeyGenerator objectId() {
		return new KeyGenerator() {
			
			@Override
			public Object generate(Object targrt, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(targrt.getClass().getName()+":");
				try {
					sb.append(params[0].getClass().getMethod("getId", null).invoke(params[0], null).toString());
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
				System.out.println("objectId == "+sb.toString());
				
				//create customer的时候
				//objectId == com.tgr.admin.service.impl.CustomerSpringCacheServiceImpl:348050
				return sb.toString();
			}
		};
	}
	
	@Bean
	public KeyGenerator queryAll() {
		return new KeyGenerator() {
			
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder sb = new StringBuilder();
				sb.append(target.getClass().getName()+":");
				/*for(Object obj : params) {
					sb.append(obj.toString());
				}*/
				System.out.println("simpleKey == "+sb.toString());
				return sb.toString();
				//simpleKey == com.tgr.admin.service.impl.CustomerSpringCacheServiceImpl:
			}
		};
	}
	
	
}
