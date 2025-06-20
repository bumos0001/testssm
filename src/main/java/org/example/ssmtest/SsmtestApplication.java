package org.example.ssmtest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("org.example.ssmtest.mapper")
public class SsmtestApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ioc = SpringApplication.run(SsmtestApplication.class, args);

    }
}
