package com.axkea.collection.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author Axkea
 * @Date 2023/10/25/025 16:12
 * @Description MyBatis的配置类
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.axkea.collection.dao"})
public class MyBatisConfig {

}
