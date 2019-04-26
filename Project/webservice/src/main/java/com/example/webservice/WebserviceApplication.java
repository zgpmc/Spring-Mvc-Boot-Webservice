package com.example.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebserviceApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(WebserviceApplication.class, args);

        /*String url = "http://localhost:8103/ws";
        Endpoint.publish(url, new CKInfoService());*/
        System.out.println("发布成功");
    }

}
