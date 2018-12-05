@DateTImeFormat时间格式化
@JsonBackReference 防止对象递归

Mapped URL path [/swagger-ui.html] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]

1, 对于类似电话号码或手机一类的大数值读取问题
poi数值转换
// 取值后会带一个E的问题 
double cellValue = row.getCell(k).getNumericCellValue(); 
fieldValues[k] = new DecimalFormat("#").format(cellValue); 

2, 对于数值型单元的纯数值和日期型的处理 
case HSSFCell.CELL_TYPE_NUMERIC: // 数值型   
    if (HSSFDateUtil.isCellDateFormatted(cell)) {   
        //  如果是date类型则 ，获取该cell的date值   
        value = HSSFDateUtil.getJavaDate(cell.getNumericCellValue()).toString();   
    } else { // 纯数字   
        value = String.valueOf(cell.getNumericCellValue());   
}


mvn install:install-file -DgroupId=alipay -DartifactId=sdk-java -Dversion=20171123203126 -Dpackaging=jar -Dfile=alipay-sdk-java20171123203126.jar

mvn install:install-file -DgroupId=spring-boot-starter-social-facebook -DartifactId=spring-boot-starter-social-facebook -Dversion=1.5.9.RELEASE -Dpackaging=jar -Dfile=spring-boot-starter-social-facebook-1.5.9.RELEASE.jar
	