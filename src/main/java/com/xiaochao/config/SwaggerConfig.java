package com.xiaochao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @program: graduation
 * @description:
 * @author: 小超
 * @create: 2020-11-14 09:21
 **/
@Configuration
@EnableSwagger2 //开启Swagger2
public class SwaggerConfig {

    //配置了Swagger的Docket的 bean实例
    @Bean
    public Docket docket(Environment environment){

        //设置要显示的Swagger环境
        Profiles profiles=Profiles.of("dev","test");
        //通过environment.acceptsProfiles(profiles)判断是否处于自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("xiaochao")
                .enable(flag)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xiaochao.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    //配置Swagger信息=apiInfo
    private ApiInfo apiInfo(){

        //作者信息
        Contact contact = new Contact("小超", "https://blog.csdn.net/qq_43768530", "2502053207@qq.com");
        return new ApiInfo(
                "毕业管理系统api文档",
                "毕业管理系统api文档",
                "1.0",
                "https://blog.csdn.net/qq_43768530",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

}
