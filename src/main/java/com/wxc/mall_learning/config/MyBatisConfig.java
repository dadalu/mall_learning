package com.wxc.mall_learning.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.wxc.mall_learning.mbg.mapper")
public class MyBatisConfig {

}
