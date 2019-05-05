package com.example.demo.dao;

import com.example.demo.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * 创建时间:2019/03/15
 * 创建人:pmc
 * 描述:
 */
@Component
public class DBBdcInfo extends DbDao
{
    @Autowired
    private Config config;

    public DBBdcInfo getBdcInfo()
    {
        return this;
    }

    @Override
    public DataSource initDataSource()
    {
        if (config != null)
        {
            //super.dataSource = config.BdcinfoDataSource();
        }
        return super.dataSource;
    }
}