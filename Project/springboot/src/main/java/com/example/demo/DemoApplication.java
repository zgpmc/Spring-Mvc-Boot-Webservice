package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
@MapperScan(value = "com.example.demo.model")
public class DemoApplication extends SpringBootServletInitializer
{
    /**
     * Configure the application. Normally all you would need to do is to add sources
     * (e.g. config classes) because other settings have sensible defaults. You might
     * choose (for instance) to add default command line arguments, or set an active
     * Spring profile.
     *
     * @param builder a builder for the application context
     * @return the application builder
     * @see org.springframework.boot.builder.SpringApplicationBuilder
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
    {
        return builder.sources(DemoApplication.class);
    }

    public static void main(String[] args)
    {
        SpringApplication.run(DemoApplication.class, args);
    }

}
