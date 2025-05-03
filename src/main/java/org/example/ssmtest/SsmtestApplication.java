package org.example.ssmtest;

import org.example.ssmtest.model.entity.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SsmtestApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ioc = SpringApplication.run(SsmtestApplication.class, args);

    }
}
