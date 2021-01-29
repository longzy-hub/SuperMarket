package com.lzy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import tk.mybatis.spring.annotation.MapperScan;

// 启动类
@SpringBootApplication // 扫描所有包
@MapperScan(basePackages = "com.lzy.mapper")
@ComponentScan(basePackages = {"com.lzy"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}
