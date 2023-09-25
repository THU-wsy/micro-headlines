package com.thuwsy.headlines;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.thuwsy.headlines.mapper")
public class MicroHeadlinesBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroHeadlinesBootApplication.class, args);
    }

}
