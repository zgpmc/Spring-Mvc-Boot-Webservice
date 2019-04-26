package db;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import tool.ToolDbSource;
import tool.ToolException;
import tool.ToolString;
import tool.ToolUserProperts;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 创建时间:2019/02/20
 * 创建人:Administrator
 * 描述:
 */
public class DBConnection
{
    private DruidPooledConnection druidPooledConnection;
    private boolean TransactionIsOK = true;//事务是否完成
    private Properties properties = null;
    private DruidDataSource druidDataSource = null;
    private ToolDbSource toolDbSource = new ToolDbSource();
    private ToolException toolException = new ToolException();

    /**
     * 初始化默认连接
     */
    public DBConnection()
    {
        try
        {
            properties = ToolUserProperts.getPorperties("jdbc.properties");
            ToolDbSource toolDbSource = new ToolDbSource();
            druidDataSource = toolDbSource.createDatasource(properties);
        } catch (IOException e)
        {
            toolException.exceptionToCotrol(e);
        }
    }

    /**
     * 配置初始化连接
     *
     * @param propertfullname propert配置文件名
     * @throws Exception
     */
    public DBConnection(String propertfullname)
    {
        if (ToolString.isNullOrEmpty(propertfullname))
        {
            try
            {
                throw new Exception("属性文件名为空");
            } catch (Exception e)
            {
                toolException.exceptionToCotrol(e);
            }
        }
        try
        {
            properties = ToolUserProperts.getPorperties(propertfullname);
            ToolDbSource toolDbSource = new ToolDbSource();
            druidDataSource = toolDbSource.createDatasource(properties);
        } catch (Exception e)
        {
            toolException.exceptionToCotrol(e);
        }
    }

    /**
     * 获取连接
     *
     * @return DruidPooledConnection
     * @throws SQLException
     */
    public DruidPooledConnection getConnection()
    {
        try
        {
            closeConnection();
            druidPooledConnection = druidDataSource.getConnection();
        } catch (SQLException e)
        {
            toolException.exceptionToCotrol(e);
        }
        return druidPooledConnection;
    }

    /**
     * 获取连接配置
     *
     * @return Properties
     */
    public Properties getProperties()
    {
        return this.properties;
    }

    /**
     * 关闭连接
     *
     * @throws SQLException
     */
    private void closeConnection() throws SQLException
    {
        if (TransactionIsOK && this.druidPooledConnection != null && !this.druidPooledConnection.isClosed())
        {
            try
            {
                this.druidPooledConnection.close();
            } catch (SQLException e)
            {
                toolException.exceptionToCotrol(e);
            }
        }
        this.druidPooledConnection = null;
    }

    /**
     * 开始事务
     *
     * @throws SQLException
     */
    public void transactionBegin()
    {
        try
        {
            this.TransactionIsOK = false;
            this.druidPooledConnection.setAutoCommit(false);
        } catch (SQLException e)
        {
            toolException.exceptionToCotrol(e);
        }
    }

    /**
     * 提交事务
     *
     * @throws SQLException
     */
    public void transactionCommit()
    {
        try
        {
            if (this.druidPooledConnection != null)
            {
                this.druidPooledConnection.commit();
                this.druidPooledConnection.setAutoCommit(true);
            }
            this.TransactionIsOK = true;
            closeConnection();
        } catch (SQLException e)
        {
            try
            {
                this.druidPooledConnection.rollback();
            } catch (SQLException e1)
            {
                toolException.exceptionToCotrol(e1);
            }
            toolException.exceptionToCotrol(e);
        } finally
        {
            this.TransactionIsOK = true;
        }
    }

    /**
     * 回滚事务
     *
     * @throws SQLException
     */
    public void transactionRoback()
    {
        try
        {
            if (this.druidPooledConnection != null)
            {
                this.druidPooledConnection.rollback();
                this.druidPooledConnection.setAutoCommit(true);
            }
            closeConnection();
        } catch (SQLException e)
        {
            toolException.exceptionToCotrol(e);
        } finally
        {
            this.TransactionIsOK = true;
        }
    }

    /**
     * 对象插入数据
     *
     * @param obj       实体
     * @param tablename 表名
     * @return 操作数目
     */
    public int insert(Object obj, String tablename)
    {
        int resnum = 0;
        try
        {
            getConnection();
            Map<String, Object> map = toolDbSource.mapFromObject(obj);
            PreparedStatement preparedStatement = toolDbSource.statementFromObject(this.druidPooledConnection, map, tablename, null, false);
            resnum = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e)
        {
            toolException.exceptionToCotrol(e);
        }
        return resnum;
    }

    /**
     * sql插入数据
     *
     * @param sql    sql
     * @param params 参数
     * @return 操作数目
     */
    public int insert(String sql, Object... params)
    {
        int resnum = 0;
        try
        {
            getConnection();
            PreparedStatement preparedStatement = toolDbSource.statementFromSQL(druidPooledConnection, sql, params);
            resnum = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e)
        {
            toolException.exceptionToCotrol(e);
        } catch (Exception e)
        {
            toolException.exceptionToCotrol(e);
        }
        return resnum;
    }

    /**
     * 无参sql插入数据
     *
     * @param sql sql
     * @return 操作数目
     */
    public int insert(String sql)
    {
        return insert(sql, new Object[]{});
    }

    /**
     * 对象更新数据必须包含主键列
     *
     * @param obj        实体对象
     * @param tablename  表名
     * @param primarykey 主键列名
     * @return 操作数目
     */
    public int update(Object obj, String tablename, String primarykey)
    {
        int resnum = 0;
        try
        {
            getConnection();
            Map<String, Object> map = toolDbSource.mapFromObject(obj);
            PreparedStatement preparedStatement = toolDbSource.statementFromObject(this.druidPooledConnection, map, tablename, primarykey,
                    true);
            resnum = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e)
        {
            toolException.exceptionToCotrol(e);
        }
        return resnum;
    }

    /**
     * sql更新数据
     *
     * @param sql    sql
     * @param params 参数
     * @return 操作数目
     */
    public int update(String sql, Object... params)
    {
        int resnum = 0;
        try
        {
            getConnection();
            PreparedStatement preparedStatement = toolDbSource.statementFromSQL(druidPooledConnection, sql, params);
            resnum = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e)
        {
            toolException.exceptionToCotrol(e);
        } catch (Exception e)
        {
            toolException.exceptionToCotrol(e);
        }
        return resnum;
    }

    /**
     * 无参sql更新数据
     *
     * @param sql sql
     * @return 操作数目
     */
    public int update(String sql)
    {
        return update(sql, new Object[]{});
    }

    /**
     * sql删除数据
     *
     * @param sql    sql
     * @param params 参数
     * @return 操作数目
     */
    public int delete(String sql, Object... params)
    {
        int resnum = 0;
        try
        {
            getConnection();
            PreparedStatement preparedStatement = toolDbSource.statementFromSQL(druidPooledConnection, sql, params);
            resnum = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e)
        {
            toolException.exceptionToCotrol(e);
        } catch (Exception e)
        {
            toolException.exceptionToCotrol(e);
        }
        return resnum;
    }

    /**
     * 无参sql删除数据
     *
     * @param sql sql
     * @return 操作数目
     */
    public int delete(String sql)
    {
        return delete(sql, new Object[]{});
    }

    /**
     * sql查询单个实体对象
     *
     * @param tClass 实体类
     * @param sql    sql
     * @param params 参数
     * @param <T>    实体对象
     * @return T
     */
    public <T> T selectFirstOrNull(Class<T> tClass, String sql, Object... params)
    {
        T t = null;
        try
        {
            getConnection();
            PreparedStatement preparedStatement = toolDbSource.statementFromSQL(druidPooledConnection, sql, params);
            ResultSet resultSet = preparedStatement.executeQuery();
            t = toolDbSource.resultSetToT(resultSet, tClass);
            preparedStatement.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 无参sql查询单个实体对象
     *
     * @param tClass 实体类
     * @param sql    sql
     * @param <T>    实体对象
     * @return T
     */
    public <T> T selectFirstOrNull(Class<T> tClass, String sql)
    {
        return selectFirstOrNull(tClass, sql, new Object[]{});
    }

    /**
     * sql查询多个数据对象
     *
     * @param tClass 实体类
     * @param sql    sql
     * @param params 参数
     * @param <T>    实体
     * @return T
     */
    public <T> List<T> select(Class<T> tClass, String sql, Object... params)
    {
        List<T> tList = new ArrayList<>();
        try
        {
            getConnection();
            PreparedStatement preparedStatement = toolDbSource.statementFromSQL(druidPooledConnection, sql, params);
            ResultSet resultSet = preparedStatement.executeQuery();
            tList = toolDbSource.resultSetToListT(resultSet, tClass);
            preparedStatement.close();
        } catch (SQLException e)
        {
            toolException.exceptionToCotrol(e);
        } catch (Exception e)
        {
            toolException.exceptionToCotrol(e);
        }
        return tList;
    }

    /**
     * 无参sql查询多个数据对象
     *
     * @param tClass 实体类
     * @param sql    sql
     * @param <T>    实体
     * @return T
     */
    public <T> List<T> select(Class<T> tClass, String sql)
    {
        return select(tClass, sql, new Object[]{});
    }

    /**
     * sql查询是否有数据
     *
     * @param sql    sql
     * @param params 参数
     * @return boolean
     */
    public boolean selectHaveData(String sql, Object... params)
    {
        boolean res = false;
        try
        {
            getConnection();
            PreparedStatement preparedStatement = toolDbSource.statementFromSQL(druidPooledConnection, sql, params);
            ResultSet resultSet = preparedStatement.executeQuery();
            res = toolDbSource.resultHaveData(resultSet);
            preparedStatement.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 无参sql查询是否有数据
     *
     * @param sql sql
     * @return boolean
     */
    public boolean selectHaveData(String sql)
    {
        return selectHaveData(sql, new Object[]{});
    }
}
