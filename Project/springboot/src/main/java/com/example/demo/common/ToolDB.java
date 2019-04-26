package com.example.demo.common;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间:2019/03/14
 * 创建人:pmc
 * 描述:
 */
@Component
public class ToolDB
{
    public <T> List<T> resultSetToTes(ResultSet resultSet, Class<T> clas) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException
    {
        List<T> list = new ArrayList();
        Field[] fields = clas.getDeclaredFields();
        List<String> fieldsList = listFormFields(fields, true);
        while (resultSet.next())
        {
            T t = clas.newInstance();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int num = resultSetMetaData.getColumnCount();
            for (int i = 0; i < num; i++)
            {
                String colum = resultSetMetaData.getColumnLabel(i + 1);
                if (fieldsList.contains(colum.toLowerCase()))
                {
                    Object object = resultSet.getObject(colum);
                    if (!fieldsList.contains(colum))
                    {
                        colum = colum.toLowerCase();
                    }
                    Field field = clas.getDeclaredField(colum);
                    if (!field.isAccessible())
                    {
                        field.setAccessible(true);
                    }
                    field.set(t, object);
                }
            }
            list.add(t);
        }
        resultSet.close();
        return list;
    }

    public <T> T resultSetToTorNull(ResultSet resultSet, Class<T> clas) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException
    {
        List<T> list = resultSetToTes(resultSet, clas);
        if (list.size() > 0)
        {
            return list.get(0);
        } else
        {
            return null;
        }
    }

    public List<String> listFormFields(Field[] fields, boolean isLow)
    {
        List<String> list = new ArrayList<>();
        for (Field field : fields)
        {
            if (!field.isAccessible())
            {
                field.setAccessible(true);
            }
            String name = field.getName();
            if (isLow)
            {
                name = name.toLowerCase();
            }
            list.add(name);
        }
        return list;
    }
}
