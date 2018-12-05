package com.tgr.security;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;


/**
 * @author tgr
 * 权限管理决断器
 * 权限管理的关键部分
 * 实现了springSecurity的AccessDecision-Manager
 * 重载了decide函数 使用了自定义的决断管理
 * 在用户访问受保护资源时 决断器判断用户拥有的角色中是否对该资源具有访问权限
 * 如果没有访问权限将被拒绝访问 返回错误提示
 *
 */
public class CustomAccessDecisionManager implements AccessDecisionManager {

	private static final Log log = LogFactory.getLog(CustomAccessDecisionManager.class);
	
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		
		if(configAttributes == null) {
			return;
		}
		
		//config urlroles
		Iterator<ConfigAttribute> iterator = configAttributes.iterator();
		while(iterator.hasNext()) {
			ConfigAttribute configAttribute = iterator.next();
			
			String needRole = configAttribute.getAttribute();
			for(GrantedAuthority ga : authentication.getAuthorities()) {
				if(needRole.equals(ga.getAuthority())) {
					return;
				}
			}
			
			log.info("need role is "+needRole);
		}
		throw new AccessDeniedException("Cannot Access !!!!!!!!");
		
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
