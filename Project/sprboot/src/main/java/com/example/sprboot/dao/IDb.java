package com.example.sprboot.dao;

import java.util.List;

/**
 * 创建时间:2019/03/15
 * 创建人:pmc
 * 描述:
 */
public interface IDb
{
    public <T> List<T> selectTList(Class<T> tClass, String SQL, Object[] params);

    //public <T> List<T> selectTList(Class<T> tClass, String SQL);

    public <T> List<String> selectStringList(String SQL, Object[] params);

    //public <T> List<String> selectStringList(String SQL);

    public <T extends Number> List<T> selectNumberList(String SQL, Object[] params);

    //public <T extends Number> List<T> selectNumberList(String SQL);

    public <T> T selectT(Class<T> tClass, String SQL, Object[] params);

    //public <T> T selectT(Class<T> tClass, String SQL);

    public int insertSQL(String SQL, Object[] params);

    //public int insertSQL(String SQL);

    public int insertT(Object object, String tablename);

    public int updateSQL(String SQL, Object[] params);

    //public int updateSQL(String SQL);

    public int deleteSQL(String SQL, Object[] params);

    //public int deleteSQL(String SQL);

    default <T> List<T> selectTList(Class<T> tClass, String SQL)
    {
        return selectTList(tClass, SQL, new Object[]{});
    }

    default <T> List<String> selectStringList(String SQL)
    {
        return selectStringList(SQL, new Object[]{});
    }

    default <T extends Number> List<T> selectNumberList(String SQL)
    {
        return selectNumberList(SQL, new Object[]{});
    }

    default <T> T selectT(Class<T> tClass, String SQL)
    {
        return selectT(tClass, SQL, new Object[]{});
    }

    default int insertSQL(String SQL)
    {
        return insertSQL(SQL, new Object[]{});
    }

    default int updateSQL(String SQL)
    {
        return updateSQL(SQL, new Object[]{});
    }

    default int deleteSQL(String SQL)
    {
        return deleteSQL(SQL, new Object[]{});
    }
}
