package com.lzy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
    // http://localhost:8088/swagger-ui.html   原ui路径
    // 配置swagger2核心配置docket
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2) // 指定api的类型为swagger2
                .apiInfo(apiInfo()) // 用于定义api文档汇总信息，页面显示的文案材料
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lzy.controller")) // 指定controller包
                .paths(PathSelectors.any()) // 所有的controller包
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder() // 接口的信息建造者
                .title("超市平台 接口api") // 文档页标题
                .contact(new Contact("lzy",
                        "https://www.lzy.com",
                        "2587234011@qq.com")) // 联系人信息
                .description("为贵工程超市平台提供的api文档") // 详细信息
                .termsOfServiceUrl("https://www.lzy.com") // 网站地址
                .build();
    }
}
