package tool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.Map.Entry;

/**
 * 创建时间:2018/12/19
 * 创建人:Administrator
 * 描述:
 */
public class ToolDbSource
{
    /**
     * datasource 列表
     */
    private HashMap<String, DataSource> dataSourceHashMap = new HashMap<String, DataSource>();

    /**
     * 创建单个datasouce 如有多个数据库连接 建议使用2个参数的创建方法
     *
     * @param properties 配置文件读取后的属性数组
     * @return DataSource
     */
    public DruidDataSource createDatasource(Properties properties)
    {
        DruidDataSource dataSource = null;
        if (!properties.equals(null))
        {
            try
            {
                dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return dataSource;
    }

    /**
     * 依据配置文件单个创建datasource
     *
     * @param properfilename 配置文件名,配置文件不能重命,否则将不创建,该值将作为获取datasouce的key
     * @param properties     配置文件读取后的属性
     */
    public void createDatasource(String properfilename, Properties properties)
    {
        if (properfilename != null && !properfilename.isEmpty() && properties != null)
        {
            if (!dataSourceHashMap.containsKey(properfilename))
            {
                DataSource dataSource = createDatasource(properties);
                if (dataSource != null)
                {
                    dataSourceHashMap.put(properfilename, dataSource);
                }
            }
        }
    }

    /**
     * 依据配置文件一次创建多个datasource
     *
     * @param properfilenamearr 配置文件名数组,配置文件不能重名,否则将不创建,该值将作为获取datasouce的key 应该与propertiesarr一一对应
     * @param propertiesarr     配置文件读取后的属性数组,数据配置连接字符串不要配置一样,否则重复创建数据连接
     */
    public void createDatasource(String[] properfilenamearr, Properties[] propertiesarr)
    {
        if (properfilenamearr.length > 0 && propertiesarr.length > 0 && properfilenamearr.length == propertiesarr.length)
        {
            for (int i = 0; i < properfilenamearr.length; i++)
            {
                createDatasource(properfilenamearr[i], propertiesarr[i]);
            }
        }
    }

    /**
     * 得到创建的datasource 调用前先调用createDatasource方法创建datasouce 否则获取到的是空的datasouce列表 可用配置文件名获取datasouce
     *
     * @return HashMap String,Object
     */
    public Map<String, Object> mapFromObject(Object object)
    {
        Map<String, Object> mapkeyval = new HashMap<>();
        Class clas = object.getClass();
        try
        {
            for (Field field : clas.getFields())
            {
                Object tobj = field.get(object);
                mapkeyval.put(field.getName(), tobj);
            }
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        return mapkeyval;
    }

    /**
     * 提供连接、需要更新或插入的实体键值对、表名 构建PreparedStatement 返回值可直接执行
     *
     * @param connection      连接
     * @param objectKeyValMap 实体键值对
     * @param tableName       表名
     * @param primaryKey      更新实体必须唯一标识 用于where
     * @param isUpdate        true更新 false插入
     * @return PreparedStatement
     * @throws SQLException
     */
    public PreparedStatement statementFromObject(Connection connection, Map<String, Object> objectKeyValMap, String
            tableName, String primaryKey, boolean isUpdate) throws SQLException
    {
        PreparedStatement preparedStatement = null;
        if (connection != null && objectKeyValMap != null && objectKeyValMap.keySet().size() > 0)
        {
            String strSql = isUpdate ? (" update " + tableName + " set ") : (" insert into " + tableName + "(%s) " + "values(%s) ");
            String primaryValue = "";
            String filedstr = "";
            String valuestr = "";
            Iterator<Entry<String, Object>> iterator = objectKeyValMap.entrySet().iterator();
            Map.Entry<String, Object> entry;
            try
            {
                while (iterator.hasNext())
                {
                    entry = iterator.next();
                    String filed = entry.getKey();
                    Object value = entry.getValue();
                    if (filed.toLowerCase() == primaryKey.toLowerCase())
                    {
                        primaryValue = String.valueOf(value);
                    }
                    if (value instanceof Date)
                    {
                        if (isUpdate)
                        {
                            filedstr += filed + "=to_date('" + value + "','yyyy-MM-dd HH24:mi:ss'),";
                        } else
                        {
                            filedstr += filed + ",";
                            valuestr += "to_date('" + value + "','yyyy-MM-dd HH24:mi:ss'),";
                        }
                    } else
                    {
                        if (isUpdate)
                        {
                            filedstr += filed + "='" + value + "',";
                        } else
                        {
                            filedstr += filed + ",";
                            valuestr += "'" + value + "',";
                        }
                    }
                }
                if (isUpdate)
                {
                    strSql = strSql + " " + filedstr.substring(0, filedstr.length() - 1) + " where " + primaryKey + "='" + primaryValue + "'";
                } else
                {
                    strSql = String.format(strSql, filedstr.substring(0, filedstr.length() - 1), valuestr.substring(0, valuestr.length() - 1));
                }
                preparedStatement = connection.prepareStatement(strSql);
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return preparedStatement;
    }

    /**
     * 提供连接、sql、参数 构建PreparedStatement 返回值可直接执行
     *
     * @param connection 连接
     * @param sql        sql
     * @param params     sql所需参数
     * @return PreparedStatement
     * @throws Exception
     */
    public PreparedStatement statementFromSQL(Connection connection, String sql, Object... params) throws Exception
    {
        PreparedStatement preparedStatement = null;
        if (connection != null)
        {
            preparedStatement = connection.prepareStatement(sql);
            ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
            int paramcount = parameterMetaData.getParameterCount();
            if (paramcount != params.length)
            {
                throw new Exception("提供的参数与sql所需参数个数不匹配！");
            } else
            {
                for (int i = 1; i <= paramcount; i++)
                {
                    Object val = params[i - 1];
                    preparedStatement.setObject(i, null == val ? "" : params[i - 1]);
                }
            }
        }
        return preparedStatement;
    }

    /**
     * result转成list结果
     *
     * @param resultSet
     * @param tClass    实体类
     * @param <T>       实体类
     * @return List<T>
     */
    public <T> List<T> resultSetToListT(ResultSet resultSet, Class<T> tClass)
    {
        List<T> tList = new ArrayList<>();
        try
        {
            Field[] fields = tClass.getFields();
            List<String> fieldList = listFromFields(fields, false);
            while (resultSet.next())
            {
                T t = tClass.newInstance();
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++)
                {
                    String columnname = resultSetMetaData.getColumnLabel(i);
                    if (fieldList.contains(columnname.toLowerCase()))
                    {
                        Object object = resultSet.getObject(columnname);
                        Field targetField = tClass.getField(columnname);
                        targetField.set(t, object);
                    }
                }
                tList.add(t);
            }
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
                resultSet.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return tList;
    }

    /**
     * 返回第一条数据对象
     *
     * @param resultSet
     * @param tClass
     * @param <T>
     * @return T
     */
    public <T> T resultSetToT(ResultSet resultSet, Class<T> tClass)
    {
        T t = null;
        try
        {
            List<T> tList = resultSetToListT(resultSet, tClass);
            if (tList.size() > 0)
            {
                t = tList.get(0);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 用于非实体的单列数据查询 支持类型String Integer Float Double Date
     *
     * @param resultSet resultSet
     * @return List<String>
     */
    public <T> List<T> resultSetSingleColumn(ResultSet resultSet)
    {
        List<T> tList = new ArrayList<>();
        try
        {
            while (resultSet.next())
            {
                T t = null;
                String.class.getPackage();
                switch (t.getClass().getSimpleName())
                {
                    case "String":
                        resultSet.getString(1);
                        break;
                    case "Integer":
                        resultSet.getInt(1);
                        break;
                    case "Float":
                        resultSet.getFloat(1);
                        break;
                    case "Double":
                        resultSet.getDouble(1);
                        break;
                    case "Date":
                        resultSet.getDate(1);
                        break;
                }
                tList.add(t);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                resultSet.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return tList;
    }

    /**
     * 判断resultSet是否有数据
     *
     * @param resultSet resultSet
     * @return boolean
     */
    public boolean resultHaveData(ResultSet resultSet)
    {
        boolean res = true;
        try
        {
            if (!resultSet.next())
            {
                res = false;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                resultSet.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return res;
    }

    /**
     * 字段转list
     *
     * @param fields  字段
     * @param isUpper 是否大写否则小写
     * @return List<String>
     */
    private List<String> listFromFields(Field[] fields, boolean isUpper)
    {
        List<String> list = new ArrayList<>();
        if (fields.length > 0)
        {
            for (Field field : fields)
            {
                String fieldname = isUpper ? field.getName().toUpperCase() : field.getName().toLowerCase();
                list.add(fieldname);
            }
        }
        return list;
    }
}
