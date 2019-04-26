package com.example.webservice.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

/**
 * 创建时间:2019/04/26
 * 创建人:pmc
 * 描述:
 */
@EnableWs
@Configuration
public class ServerConfig extends WsConfigurerAdapter
{
    @Bean
    public ServletRegistrationBean servletRegistrationBean(ApplicationContext applicationContext)
    {
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(applicationContext);
        messageDispatcherServlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(messageDispatcherServlet, "/WS/*");
    }
}
