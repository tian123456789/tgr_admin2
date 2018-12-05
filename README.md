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
	
