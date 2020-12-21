package com.xiaochao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: graduation
 * @description:
 * @author: 小超
 * @create: 2020-11-13 19:44
 **/
@SpringBootApplication
@MapperScan("com.xiaochao.dao")
public class SpringBootGraduation {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootGraduation.class,args);
    }
}
