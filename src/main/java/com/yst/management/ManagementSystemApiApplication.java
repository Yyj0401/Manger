package com.yst.management;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.yst.management.mapper")
public class ManagementSystemApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagementSystemApiApplication.class, args);
    }

}
