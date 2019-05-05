package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * 创建时间:2019/03/14
 * 创建人:pmc
 * 描述:
 */
@Component
@Configuration
public class Config
{
    @Bean(name = "bdcinfo")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.bdcinfo")
    public DataSource BdcinfoDataSource()
    {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "netobdc")
    @ConfigurationProperties(prefix = "spring.datasource.netobdc")
    public DataSource NetobdcDataSource()
    {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "durid")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.durid")
    public DataSource DuridfoDataSource()
    {
        return DataSourceBuilder.create().build();
    }
}
