package com.tgr.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;


/**
 * @author tgr
 *权限配置资源管理器 实现了springSecurity的FilterInvocationSecurityMetadataSource
 *	其启动时导入权限列表 权限控制资源管理器为权限决断器实时提供支持 
 *	判断用户是否在受保护的范围之内
 */
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private static final Log log = LogFactory.getLog(CustomSecurityMetadataSource.class);
	
	private Map<String, Collection<ConfigAttribute>> resourceMap = null;
	private PathMatcher pathMatcher = new AntPathMatcher();
	
	private String urlroles;
	
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		String url = ((FilterInvocation) object).getRequestUrl();
		
		log.debug("》》》》 请求路径是"+url);
		
		if(resourceMap == null)
			resourceMap = loadResourceMatchAuthority();
			
			Iterator<String> ite = resourceMap.keySet().iterator();
			while(ite.hasNext()) {
				String resURL = ite.next();
				if(pathMatcher.match(resURL, url)) {
					return resourceMap.get(resURL);
				}
			}
			return resourceMap.get(url);

	}
	
	private Map<String, Collection<ConfigAttribute>> loadResourceMatchAuthority() {
		Map<String, Collection<ConfigAttribute>> map = new HashMap<String,Collection<ConfigAttribute>>();
		if(urlroles != null && !urlroles.isEmpty()) {
			String[] resources  = urlroles.split(";");
			for(String resource : resources) {
				String[] urls = resource.split("=");
				String[] roles = urls[1].split(",");
				Collection<ConfigAttribute> list = new ArrayList<ConfigAttribute>();
				
				for(String role : roles) {
					ConfigAttribute config = new SecurityConfig(role.trim());
					list.add(config);
				}
				//key : url value: roles
				map.put(urls[0], list);
			}
		}else {
			log.error("!!!!! 'securityconfig.urlroles' must be set");
		}
		log.info("loaded urlRole Resources.");
		return map;
	}
	
	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public CustomSecurityMetadataSource(String urlroles) {
		super();
		this.urlroles = urlroles;
		resourceMap = loadResourceMatchAuthority();
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
