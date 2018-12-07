package com.tgr.admin.configPoepertiesToBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "测试配置文件属性配置到bean")
@RestController
@EnableConfigurationProperties({ConfigBean.class})
@RequestMapping("yml_to_bean")
public class UseConfigBeanController {

	@Autowired
	private static ConfigBean bean;
	
	@ApiOperation(value = "获取")
	@GetMapping(value = "get")
	public String get() {
		return bean.getName()+"::::::::"+bean.getReverse();
	}
	
	public static void main(String[] args) {
		System.out.println(bean.getName()+"::::::::"+bean.getReverse());
	}
}
