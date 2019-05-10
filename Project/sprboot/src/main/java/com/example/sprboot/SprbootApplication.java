package com.example.sprboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.example.sprboot.mapper")
public class SprbootApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(SprbootApplication.class, args);
    }

}
