package com.example.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 创建时间:2019/03/14
 * 创建人:pmc
 * 描述:
 */

@Configuration
public class Config
{
    @Bean(name = "bdcinfo")
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

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception
    {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        DataSource dataSource = DuridfoDataSource();
        sqlSessionFactoryBean.setDataSource(dataSource);
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        return sqlSessionFactory;
    }
}
