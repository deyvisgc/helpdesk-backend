package com.helpdesk.cofing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.helpdesk.mappers")
public class MyBatisConfiguration {

}
