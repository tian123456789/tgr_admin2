package com.tgr.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.tgr.spring.bean.DemoService;

@Configuration
@Import(DemoService.class)
public class DemoConfig {

}
