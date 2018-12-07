###########################################################################################################################################

0.1: jpa annotation
@DateTImeFormat时间格式化
@JsonBackReference 防止对象递归
###########################################################################################################################################

0.2: swagger url
Mapped URL path [/swagger-ui.html] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
###########################################################################################################################################

0.3: durid  url: /durid/index
###########################################################################################################################################

1: poi手机号码 
对于类似电话号码或手机一类的大数值读取问题
poi数值转换
// 取值后会带一个E的问题 
double cellValue = row.getCell(k).getNumericCellValue(); 
fieldValues[k] = new DecimalFormat("#").format(cellValue); 
###########################################################################################################################################

2: poi导入 注意点举例 
对于数值型单元的纯数值和日期型的处理 
case HSSFCell.CELL_TYPE_NUMERIC: // 数值型   
    if (HSSFDateUtil.isCellDateFormatted(cell)) {   
        //  如果是date类型则 ，获取该cell的date值   
        value = HSSFDateUtil.getJavaDate(cell.getNumericCellValue()).toString();   
    } else { // 纯数字   
        value = String.valueOf(cell.getNumericCellValue());   
}

###########################################################################################################################################

3: 外包打包至本地maven Repository
mvn install:install-file -DgroupId=alipay -DartifactId=sdk-java -Dversion=20171123203126 -Dpackaging=jar -Dfile=alipay-sdk-java20171123203126.jar

mvn install:install-file -DgroupId=spring-boot-starter-social-facebook -DartifactId=spring-boot-starter-social-facebook -Dversion=1.5.9.RELEASE -Dpackaging=jar -Dfile=spring-boot-starter-social-facebook-1.5.9.RELEASE.jar
###########################################################################################################################################

4: springSecurity
	本模块只实现了 默认密码编码 数据库用户登录验证 数据库role表权限注解如@PreAuthority("陆军部总长")验证
###########################################################################################################################################

5: 熔断机制来源		熔断器（即 Hystrix组件的 Circuit Breaker ）
	
	目的: 为了解决服务故障的传播性

	在分布式系统中，服务之间相互依赖，如果一个服务出现了故障或者是网络延迟，在高并发的情况下，会导致线程阻塞，在很短的时间内该服务的线程资源会消耗殆尽，最终使得该服务不可用 。由于服务的相互依赖，可能会导致整个系统的不可用，这就是“雪崩效应”。为了防止此类事件的发生，分布式系统必然要采取相应的措施，例如“熔断机制”。

	服务 b 出现故障，请求失败次数超过设定的阀值之后，服务 b 就会开启熔断器，之后服务 b 不进行任何的业务逻辑操作，执行快速失败，直接返回请求失败的信息。其他依赖于 b 的服务就不会因为得不到响应而线程阻塞，这时除了服务 b 和依赖于服务 b 的部分功能不可用外 ，其他功能正常。
	熔断器还有另外一个机制，自我修复的机制。当服务 b 熔断后，经过一段时间，半打开熔断器。半打开的熔断器会检查一部分请求是否正常，其他请求执行快边失败，检查的请求如果响应成功，则可以判定服务 b 正常了，就会关闭服务 b 的熔断器；如果服务 b 还不正常，则继续打开熔断器 。 这利1 自我熔断机制自我修复机制在微服务架构中有精重要的意义， 一方面，它使程序更加健壮，另 一方面，为开发和运维减少很多不必要的工作 。
###########################################################################################################################################

微服务几大特点:
1.服务的注册和发现。
2.服务的负载均衡 。
3.服务的容错。
4.服务网关。
5.服务配置的统一管理。
6.链路追踪。
7.实时日志。
###########################################################################################################################################

springBoot主要特点
	1.自动装配	
		当程序的 porn 文件引入了 Feign 的起步依赖， Spring Boot 就会在程序中自动引入默认的 Feign 的配置 Bean 。
	spring 可以在配置文件显示声明全部的Bean,但这是不明智的,就是一个比萨自己选择全部配料的加载
	spring boot 是直接选择一个有特色的 让spring boot处理各种细节比自己声明上下文全部Bean要容易很多
		1.1 实现方式
			
		1.1 使用显式配置进行覆盖
			1.1.1 使用注意举例
			@Configuration
			@EnableConfigurationProperties
			@ConditionalOnClass({ EnableWebSecurity.class })
			@ConditionalOnMissingBean(WebSecurityConfiguration.class)
			@ConditionalOnWebApplication
			public class SpringBootWebSecurityConfiguration {
			
			如果我想应用此功能
			看到@ConditionalOnClass注解后，你就应该知道Classpath里必须要有@EnableWebSecurity注解。
				@ConditionalOnWebApplication 说 明 这 必 须 是 个 Web 应 用 程 序 。 					@ConditionalOnMissingBean注解才是我们的安全配置类关键所在。
				@ConditionalOnMissingBean注解要求当下没有WebSecurityConfiguration类型的Bean。虽然				表面上我们并没有这么一个Bean，但通过在SecurityConfig上添加@EnableWeb-3.2 通过属性文件外置配置
				Security注解，我们实际上间接创建了一个WebSecurityConfiguration Bean。所以在自动
				配置时，这个Bean就已经存在了， @ConditionalOnMissingBean条件不成立， 				SpringBootWebSecurityConfiguration提供的配置就被跳过了。
				虽然Spring Boot的自动配置和@ConditionalOnMissingBean让你能显式地覆盖那些可以
				自动配置的Bean，但并不是每次都要做到这种程度。让我们来看看怎么通过设置几个简单的配置
				属性调整自动配置组件吧
				
	
				1.2 使用属性进行精细化配置
	2. 起步依赖
		程序需要 Spring MVC 的功能，那么需要引入 spring-core 、 spring-web
		和 spring-webmvc 等依赖，但是如果程序使用 Spring Boot 的起步依赖，只 需要加入44
		spring-boot-starter-web 的依赖，它会自动引入 SpringMVC 功能的相关依赖。
	
	3. Actuator 组件
		Spring Boot 能够提供自动装配和起步依赖 ， 解决了以前重量级的 xml 配置和依赖管理的
		各种问题。 一切都显得那么敏捷、智能，但是却带来了一系列的其他问题 ： 开发者该怎么知道
		应用程序中注入了哪些 Bean ？ 应用程序的运行状态是怎么样的？为了解决这些问题 ， Spring
		Boot 提供了 Actuator 组件，井提供了对程序的运行状态的监控功能。。

###########################################################################################################################################
将配置文件属性注入bean 并放进IOC容器
	1.定义spring的一个实体bean装载配置文件信息，其它要使用配置信息是注入该实体bean
		@Component
		@ConfigurationProperties(locations = "classpath:mail.properties", ignoreUnknownFields = false, prefix = "mail")
		----------
		@Autowired
    	private MailProperties mailProperties;
	2.@Bean+@ConfigurationProperties
		 @Bean
   		 @ConfigurationProperties(locations = "classpath:mail.properties", prefix = "mail")
    	 public MailProperties mailProperties(){
    3.@ConfigurationProperties + @EnableConfigurationProperties
    	@ConfigurationProperties(locations = "classpath:mail.properties", ignoreUnknownFields = false, prefix = "mail")
		public class MailProperties {}
		-------------------
		@EnableConfigurationProperties(MailProperties.class)
		public class TestProperty1 {
   		 @Autowired
    	private MailProperties mailProperties;
    	
@EnableConfigurationProperties注解。该注解是用来开启对@ConfigurationProperties注解配置Bean的支持。也就是@EnableConfigurationProperties注解告诉Spring Boot 能支持@ConfigurationProperties。如果不指定会看到没有可利用bean异常

@PropertySource 注解上加 location,并指明该配置文件的路径 
例:
配置文件yaml
	例如属性my.number: ${random.int) 配置到javaBean 利用@ConfigurationProperties(prefix =”my”)注解到javaBean类上
													+@Component项目启动时 通过包扫描放入IOC容器
							      		  或者单一属性@Value (” ${ my .name } ”)
			@ConfigurationProperties，表明该类为配置属性类
	应用配置的JavaBean属性类到其他类
		    @EnableConfigurationProperties 注解，并指明 ConfigBean 类，其代码清单如下 ：
		    @Controller
		    public class LucyController {
				@Autowired
				ConfigBean configBean ;
			}
###########################################################################################################################################


springBootApplication自动扫描下级包依赖问题

	@SpringBootApplication注解主要了@Configuration、@EnableAutoConfiguation、@ComponentScan；若不使用@SpringBootApplication注解，则可以再入口类上直接使用@Configuration、@EnableAutoConfiguation、@ComponentScan。
	其中@EnableAutoConfiguation让Spring Boot根据类路径中的jar包依赖为当前项目进行自动配置。
	例如，添加了spring-boot-starter-web依赖，会自动添加Tomcat 和 Spring MVC的依赖，那么Spring Boot会对Tomcat和Spring MVC进行自动配置。
	Spring Boot会自动扫描@SpringBootApplication 所在类的同级包 （如com.springboot.text.myspringboot)以及下级包里的Bean。建议入口类放置的位置在groupId+rctifactID组合的包名下。
	
###########################################################################################################################################

深入理解自动装配
https://www.cnblogs.com/niechen/p/9027804.html?utm_source=tuicool&utm_medium=referral

###########################################################################################################################################

错误自动配置
Error starting ApplicationContext. To display the auto-configuration report re-run your application with 'debug' enabled.
2018-12-05 19:26:45 ERROR o.s.b.SpringApplication[771]: Application startup failed
org.springframework.beans.factory.BeanDefinitionStoreException: Failed to process import candidates for configuration class [com.tg.tgr_admin2.MyDataSourceConfiguration]; nested exception is java.io.FileNotFoundException: class path resource [org/springframework/boot/autoconfigure/security/servlet/WebSecurityEnablerConfiguration.class] cannot be opened because it does not exist
	解决
	需要在启动类的@EnableAutoConfiguration或@SpringBootApplication中添加exclude = {WebSecurityEnablerConfiguration.class}，排 除此	类的autoconfig。启动以后就可以正常运行。 
	
	@EnableAutoConfiguration(exclude = {MainController.class})
	public class MainController {
	
	@Configuration
	@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
	public class MyDataSourceConfiguration {
继而又出现
	org.springframework.beans.factory.BeanDefinitionStoreException: Failed to process import candidates for 	configuration class [com.tg.tgr_admin2.AdminApplication]; nested exception is java.lang.IllegalStateException: The 	following classes could not be excluded because they are not auto-configuration classes:
	- com.tgr.admin.MainController
	
https://www.e-learn.cn/content/java/1069530
###########################################################################################################################################

	
	


	
