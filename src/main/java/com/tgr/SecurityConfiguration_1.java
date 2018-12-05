package com.tgr;


import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import com.tgr.admin.entity.User;
import com.tgr.admin.service.UserService;
import com.tgr.admin.service.impl.CustomUserDetailsService;
import com.tgr.admin.service.impl.UserServiceImpl;
import com.tgr.security.CsrfSecurityRequestMatcher;
import com.tgr.security.CustomAccessDecisionManager;
import com.tgr.security.CustomFilterSecurityInterceptor;
import com.tgr.security.CustomSecurityMetadataSource;
import com.tgr.security.SecuritySettings;

@Configuration
@EnableWebSecurity//创建一个名为 springSecurityFilterChain 的Filter 具体实现验证靠configuer(HttpSecurity)
//@Order(SecurityProperties.DEFAULT_FILTER_ORDER)//ACCESS_OVERRIDE_ORDER
@EnableConfigurationProperties(value=SecuritySettings.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)//,securedEnabled =true
@SuppressWarnings("all")
public class SecurityConfiguration_1 extends WebSecurityConfigurerAdapter {//创建了一个Servlet Filter:springSecurityFilterChain
																		   //其负责应用中的所有安全 包括:保护应用的URLS
																		   //验证提交的username和password,重定向到登录页面等

	private static final Log log = LogFactory.getLog(SecurityConfiguration_1.class);
	
	@Autowired
	private SecuritySettings settings;
	
	@Autowired
	private CustomUserDetailsService customerUserDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	//功能同  configureGlobal
	
	@Autowired
	@Qualifier("dataSource_1")
	private DataSource dataSource;
	
	/*@Bean
	CustomerUserDetailsService customerUserDetailsService() {
		return customerUserDetailsService;
	}
	*/
	
	/*
	 * loginPage:设置自定义登陆页面url
	 * loginSuccessHandler:设置自定义登陆成功处理器
	 * permitAll: 完全允许访问的一些url配置,并可以使用通配符来设置    settings指定的权限列表也赋予了完全访问权限
	 * logout: 设置使用默认的登出
	 * logoutSuccessUrl: 设定登出成功的链接
	 * rememberMe:用来记住用户的登陆状态。即用户没有执行退出时。再次打开页面将不用登陆
	 * csrf:即跨站请求伪造,这是一个防止跨站请求伪造攻击的策略设置
	 * accessDeniedPage: 配置一个拒绝访问的提示链接，其中,settings是引用自定义的配置参数
	 * 
	 * */
	/*`
	 * HttpSecurity 的注释说明
	 * 	 @Configuration
	 *   @EnableWebSecurity
		 public class FormLoginSecurityConfig extends WebSecurityConfigurerAdapter {
	
		 	@Override
		 	protected void configure(HttpSecurity http) throws Exception {
		 		http.authorizeRequests().antMatchers("/**").hasRole("USER").and().formLogin();
		 	}
	
		 	@Override
		 	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
		 	}
		 }
	 * 
	 */
	
	
/*################################################################################################################################*/
											/*以下为第一种配置方式*/
/*################################################################################################################################*/

	@Override
 	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
 		auth.userDetailsService(customerUserDetailsService).passwordEncoder(passwordEncoder());
 		auth.eraseCredentials(false);
 	}
	
	/**
	 * Override this method to configure {@link WebSecurity}. For example, if you wish to
	 * ignore certain requests.
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/assets/**", "/css/**", "/fonts/**", "/img/**", "/js/**","/clipboard/**","/js/**"
				,"/rightclick/**",
				"/swagger-ui.html","/swagger-resources/**","/v2/api-docs/**","/webjars/springfox-swagger-ui/**"
				,"/druid","/druid/**","/static/**"
				);
	}
	
	/*
	 *  authorizeRequests()，formLogin()、httpBasic()这三个方法返回的分别是ExpressionUrlAuthorizationConfigurer、
		FormLoginConfigurer、HttpBasicConfigurer，他们都是SecurityConfigurer接口的实现类，分别代表的是不同类型的安全配置器。
		分别对应单个过滤器
		FilterSecurityInterceptor、UsernamePasswordAuthenticationFilter、BasicAuthenticationFilter。
	*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		    http		
					 //安全策略ExpressionUrlAuthorizationConfigurer、
					.authorizeRequests()//The most basic example is to configure all URLs to require the role "ROLE_USER". The configuration below requires authentication to every URL and will grant access to both the user "admin" and "user".
					/*.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
						@Override
						public <O extends FilterSecurityInterceptor> O postProcess(O o) {
							o.setPublishAuthorizationSuccess(true);
							o.setSecurityMetadataSource(securityMetadataSource());
							o.setAccessDecisionManager(accessDecisionManager());
							o.setAuthenticationManager(authenticationManager);
							return o;
						}
					})*/
					//.antMatchers("/user/**").hasAnyRole("HJ1") //数据库 必须加ROLE_
					.anyRequest().authenticated()//其他所有路径都需要权限校验  必不可少 没有下边  authorities' cannot be found on object of type 'com.tgr.admin.entity.User' - maybe not public?
					
			.and()
					//登录表单FormLoginConfigurer
					.formLogin()//内部注册 UsernamePasswordAuthenticationFIlter
					.loginPage("/login")//表单登录页面地址.html
					.loginProcessingUrl("/to_login")//form表单Post请求url提交地址,默认为/login
					.usernameParameter("username")//form表单用户名参数
					.passwordParameter("password")//form表单密码参数名
					.successForwardUrl("/index.html")//登录成功跳转地址.html
					.failureForwardUrl("/fail")//登录失败跳转地址.html
					.permitAll()//允许所有用户都有权限访问登录页面
					.successHandler(new onAuthentiationSuccess())//认证成功处理器
					.and().exceptionHandling().accessDeniedPage("/401")
					//.antMatchers(settings.getPermitall().split(",")).permitAll()
					;
						/*.csrf().requireCsrfProtectionMatcher(csrfSecurityRequestMatcher())//.requireCsrfProtectionMatcher(csrfSecurityRequestMatcher())  .disable()
					.and()
						.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
					.and()
						.logout().logoutSuccessUrl(settings.getDeniedpage())
					.and()
						.exceptionHandling().accessDeniedPage(settings.getDeniedpage())
					.and()
						.rememberMe().tokenValiditySeconds(1209600).tokenRepository(tokenRepository());*/
						
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	class onAuthentiationSuccess extends SavedRequestAwareAuthenticationSuccessHandler{
		@Override
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws ServletException, IOException {
				User userDetail = (User) authentication.getPrincipal();
				log.info("登陆用户user: "+userDetail.getUsername()+" login "+request.getContextPath());
				log.info("IP: "+ getIPAddr(request));
				request.getSession().setAttribute("currentUser", userDetail);
				super.onAuthenticationSuccess(request, response, authentication);
		}
	}
	
	/**
	 * @return
	 * CSRF保护列表
	 * 
	 */
	private CsrfSecurityRequestMatcher csrfSecurityRequestMatcher() {
		CsrfSecurityRequestMatcher csrfSecurityRequestMatcher =
				new CsrfSecurityRequestMatcher();
		
		List<String> list = new ArrayList<String>();
		list.add("/**");///rest/
		csrfSecurityRequestMatcher.setExecludeUrls(list);
		return csrfSecurityRequestMatcher;
	}
	
	/**
	 * @return
	 * 指定保存登录用户token的 数据源
	 */
	@Bean
	public JdbcTokenRepositoryImpl tokenRepository(){
		JdbcTokenRepositoryImpl jtr = new JdbcTokenRepositoryImpl();
		jtr.setDataSource(dataSource);
		return jtr;
	}
	
	
	/*权限管理配置
	 * 
	 * CustomFIlterSecurityInterceptor: 权限管理过滤器
	 * 		extends AbstractSecurityInterceptor 
			implements Filter
			
	 * 		实时监控用户行为 防止用户访问未被授权的资源
	 * 
	 * CustomAccessDecisionManager: 权限管理决断器
	 * 		implements AccessDecisionManager
	 * 
	 * 		权限管理决断器
	 * 		权限管理的关键部分
	 * 		实现了springSecurity的AccessDecision-Manager
	 * 		重载了decide函数 使用了自定义的决断管理
	 * 		在用户访问受保护资源时 决断器判断用户拥有的角色中是否对该资源具有访问权限
	 * 		如果没有访问权限将被拒绝访问 返回错误提示
	 * 
	 * 
	 * CustomSecurityMetadataSource: 权限配置资源管理器
	 * 		implements FilterInvocationSecurityMetadataSource
	 * 
	 * 		权限配置资源管理器 实现了springSecurity的FilterInvocationSecurityMetadataSource
	 *		其启动时导入权限列表 权限控制资源管理器为权限决断器实时提供支持 
	 *		判断用户是否在受保护的范围之内
	 * 
	 * */
	
	/*@Bean
	public CustomFilterSecurityInterceptor customFilter() {
		CustomFilterSecurityInterceptor customFilter = 
				new CustomFilterSecurityInterceptor();
		customFilter.setSecurityMetadataSource(securityMetadataSource());
		customFilter.setAccessDecisionManager(accessDecisionManager());
		customFilter.setAuthenticationManager(authenticationManager);
		return customFilter;
	}*/
	
	@Bean
	public CustomAccessDecisionManager accessDecisionManager() {
		return new CustomAccessDecisionManager();
	}
	
	@Bean
	public CustomSecurityMetadataSource securityMetadataSource() {
		return new CustomSecurityMetadataSource(settings.getUrlroles());
	}
	
					/*认证或者登陆成功处理器来源*/
	
	/*package org.springframework.security.web.authentication;*/
	//					||||||||
	//public class SavedRequestAwareAuthenticationSuccessHandler
	//	extends SimpleUrlAuthenticationSuccessHandler
	//public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException
	///*     */   {
	///*  77 */     SavedRequest savedRequest = this.requestCache.getRequest(request, response);
	///*     */     
	///*  79 */     if (savedRequest == null) {
	///*  80 */       super.onAuthenticationSuccess(request, response, authentication);
	///*     */       
	///*  82 */       return;
	///*     */     }
	///*  84 */     String targetUrlParameter = getTargetUrlParameter();
	///*  85 */     if ((isAlwaysUseDefaultTargetUrl()) || ((targetUrlParameter != null) && 
	///*  86 */       (StringUtils.hasText(request
	///*  87 */       .getParameter(targetUrlParameter))))) {
	///*  88 */       this.requestCache.removeRequest(request, response);
	///*  89 */       super.onAuthenticationSuccess(request, response, authentication);
	///*     */       
	///*  91 */       return;
	///*     */     }
	///*     */     
	///*  94 */     clearAuthenticationAttributes(request);
	///*     */     
	///*     */ 
	///*  97 */     String targetUrl = savedRequest.getRedirectUrl();
	///*  98 */     this.logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
	///*  99 */     getRedirectStrategy().sendRedirect(request, response, targetUrl);
	///*     */   }
	
	public String getIPAddr(HttpServletRequest request){
        if (request == null)
            return null;
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getHeader("Proxy-Client-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getHeader("WL-Proxy-Client-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getHeader("HTTP_CLIENT_IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
            ip = request.getRemoteAddr();
        if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip))
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            }
            catch (UnknownHostException unknownhostexception) {
            }
        return ip;
	}
}
