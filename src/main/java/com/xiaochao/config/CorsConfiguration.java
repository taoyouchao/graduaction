package com.xiaochao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: graduation
 * @description:
 * @author: 小超
 * @create: 2020-12-07 19:35
 **/
@Configuration
public class CorsConfiguration {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowCredentials(true)//是否允许发送cookies信息
                        .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                        .allowedHeaders("*")//允许请求中携带header信息
                        .exposedHeaders("/*");//暴露那些头信息(因为跨域访问中默认不能获取全部头信息)
            }
        };
    }

}
