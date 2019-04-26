package com.example.demo.dao;

import com.example.demo.common.ToolDB;
import com.example.demo.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间:2019/03/15
 * 创建人:pmc
 * 描述:
 */
@Component
public class DbDao implements IDb
{
    @Autowired
    private ToolDB toolDB;
    private Connection CONNECTION = null;
    protected DataSource dataSource = null;

    public DataSource initDataSource()
    {
        return dataSource;
    }

    public Connection getConnection()
    {
        if (CONNECTION != null)
        {
            try
            {
                CONNECTION.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        try
        {
            CONNECTION = initDataSource().getConnection();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return CONNECTION;
    }

    private PreparedStatement getPreparedStatement(String SQL, Object[] params)
    {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try
        {
            preparedStatement = connection.prepareStatement(SQL);
            ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
            int paramnum = parameterMetaData.getParameterCount();
            if (params.length != paramnum)
            {
                throw new Exception("SQL参数与提供的参数数目不一致！");
            } else
            {
                if (params.length > 0)
                {
                    for (int i = 0; i < paramnum; i++)
                    {
                        preparedStatement.setObject(i + 1, params[i] == null ? null : params[i]);
                    }
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    @Override
    public <T> List<T> selectTList(Class<T> tClass, String SQL, Object[] params)
    {
        List<T> list = new ArrayList<>();
        PreparedStatement preparedStatement = getPreparedStatement(SQL, params);
        try
        {
            ResultSet resultSet = preparedStatement.executeQuery();
            list = toolDB.resultSetToTes(resultSet, tClass);
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        } catch (InstantiationException e)
        {
            e.printStackTrace();
        } catch (NoSuchFieldException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                preparedStatement.close();
                CONNECTION.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        return list;
    }

    @Override
    public <T> List<String> selectStringList(String SQL, Object[] params)
    {
        return null;
    }

    @Override
    public <T extends Number> List<T> selectNumberList(String SQL, Object[] params)
    {
        return null;
    }

    @Override
    public <T> T selectT(Class<T> tClass, String SQL, Object[] params)
    {
        List<T> list = selectTList(tClass, SQL, params);

        return null;
    }

    @Override
    public int insertSQL(String SQL, Object[] params)
    {
        return 0;
    }

    @Override
    public int insertT(Object object, String tablename)
    {
        return 0;
    }

    @Override
    public int updateSQL(String SQL, Object[] params)
    {
        return 0;
    }

    @Override
    public int deleteSQL(String SQL, Object[] params)
    {
        return 0;
    }
}
