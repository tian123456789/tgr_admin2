package com.tgr;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tgr.admin.configPoepertiesToBean.ConfigBean;

@RunWith(SpringJUnit4ClassRunner.class)//SpringJUnit4ClassRunner	对Spring应用程序进行集成测试
@ContextConfiguration(classes = AdminApplication.class)//加载应用程序上下文
//@SpringBootTest
//@SpringBootTest(webEnv 工 ronment = SpringBootTest.WebEnvironment . RANDOM_PORT)//上下文换成此
													//还可以加载其他的 而且指定Web测试环境的默认端口号
public class TgrAdminApplicationTests {

	@Autowired
	private ConfigBean bean;
	
	@Test
	public void get() {
		System.out.println("11111111111111111111111111111111111111");
		System.out.println(bean.getName()+"::::::::"+bean.getReverse());
	}
	
	@Test
	public void get2() {
		System.out.println("22222222222222222222222222222222222222");
		System.out.println(bean.getName()+"::::::::"+bean.getReverse());
	}

}
