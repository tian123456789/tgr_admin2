package com.tgr.spring.run;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.tgr.spring.bean.DemoService;

public class ImportRun {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.tgr.spring");
		DemoService ds = context.getBean(DemoService.class);
		System.out.println("####################################");
		ds.doSomeThing();
	}
}
