0.1: jpa annotation
@DateTImeFormat时间格式化
@JsonBackReference 防止对象递归

0.2: swagger url
Mapped URL path [/swagger-ui.html] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]

0.3: durid  url: /durid/index
1: poi手机号码 
对于类似电话号码或手机一类的大数值读取问题
poi数值转换
// 取值后会带一个E的问题 
double cellValue = row.getCell(k).getNumericCellValue(); 
fieldValues[k] = new DecimalFormat("#").format(cellValue); 

2: poi导入 注意点举例 
对于数值型单元的纯数值和日期型的处理 
case HSSFCell.CELL_TYPE_NUMERIC: // 数值型   
    if (HSSFDateUtil.isCellDateFormatted(cell)) {   
        //  如果是date类型则 ，获取该cell的date值   
        value = HSSFDateUtil.getJavaDate(cell.getNumericCellValue()).toString();   
    } else { // 纯数字   
        value = String.valueOf(cell.getNumericCellValue());   
}


3: 外包打包至本地maven Repository
mvn install:install-file -DgroupId=alipay -DartifactId=sdk-java -Dversion=20171123203126 -Dpackaging=jar -Dfile=alipay-sdk-java20171123203126.jar

mvn install:install-file -DgroupId=spring-boot-starter-social-facebook -DartifactId=spring-boot-starter-social-facebook -Dversion=1.5.9.RELEASE -Dpackaging=jar -Dfile=spring-boot-starter-social-facebook-1.5.9.RELEASE.jar

4: springSecurity
	本模块只实现了 默认密码编码 数据库用户登录验证 数据库role表权限注解如@PreAuthority("陆军部总长")验证

5: 熔断机制来源		熔断器（即 Hystrix组件的 Circuit Breaker ）
	
	目的: 为了解决服务故障的传播性

	在分布式系统中，服务之间相互依赖，如果一个服务出现了故障或者是网络延迟，在高并发的情况下，会导致线程阻塞，在很短的时间内该服务的线程资源会消耗殆尽，最终使得该服务不可用 。由于服务的相互依赖，可能会导致整个系统的不可用，这就是“雪崩效应”。为了防止此类事件的发生，分布式系统必然要采取相应的措施，例如“熔断机制”。

	服务 b 出现故障，请求失败次数超过设定的阀值之后，服务 b 就会开启熔断器，之后服务 b 不进行任何的业务逻辑操作，执行快速失败，直接返回请求失败的信息。其他依赖于 b 的服务就不会因为得不到响应而线程阻塞，这时除了服务 b 和依赖于服务 b 的部分功能不可用外 ，其他功能正常。
	熔断器还有另外一个机制，自我修复的机制。当服务 b 熔断后，经过一段时间，半打开熔断器。半打开的熔断器会检查一部分请求是否正常，其他请求执行快边失败，检查的请求如果响应成功，则可以判定服务 b 正常了，就会关闭服务 b 的熔断器；如果服务 b 还不正常，则继续打开熔断器 。 这利1 自我熔断机制自我修复机制在微服务架构中有精重要的意义， 一方面，它使程序更加健壮，另 一方面，为开发和运维减少很多不必要的工作 。

微服务几大特点:
1.服务的注册和发现。
2.服务的负载均衡 。
3.服务的容错。
4.服务网关。
5.服务配置的统一管理。
6.链路追踪。
7.实时日志。


	
