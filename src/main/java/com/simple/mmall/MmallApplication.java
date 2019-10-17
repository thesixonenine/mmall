package com.simple.mmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author simple
 */
@MapperScan("com.simple.mmall.dao")
@SpringBootApplication
public class MmallApplication {
    public static void main(String[] args) {
        SpringApplication.run(MmallApplication.class, args);
    }
}

