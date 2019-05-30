package com.example.sprboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;

/**
 * 创建时间:2019/03/14
 * 创建人:pmc
 * 描述:
 */

@Configuration
public class Config
{
    /**
     * 使用时一定要配置主数据连接的配置 以及类 集成mybatis时主连接出现问题是会报
     * 1.dataSource or dataSourceClassName or jdbcUrl is required
     * 2.property 'sqlsessionfactory' or 'sqlsessiontemplate' are required
     *
     * @return datasource
     */
    @Bean(name = "durid")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource DuridfoDataSource()
    {
        return DataSourceBuilder.create().build();
    }

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
    /**
     * 文件上传配置
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();

        //单个文件最大
        factory.setMaxFileSize("10240KB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("102400KB");
        return factory.createMultipartConfig();
    }
}
