package com.example.sprboot.common;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间:2019/05/29
 * 创建人:pmc
 * 描述:文件工具类
 */
public class ToolFile
{
    /**
     * 在文件路径创建文件夹或文件
     *
     * @param path 路径
     * @return file
     */
    public File createFile(Path path)
    {
        File file = new File(path.toUri());
        if (!file.exists())
        {
            file.mkdirs();
        }
        return file;
    }

    /**
     * 在文件路径创建文件夹或文件
     *
     * @param path 路径
     * @return file
     */
    public File createFile(String path)
    {
        Path p = Paths.get(path);
        return createFile(p);
    }

    /**
     * 更具路径删除文件
     *
     * @param path            路径
     * @param deleteDirectory 是否删除指定的目录,仅限路径为文件夹有效
     */
    public void deleteFile(Path path, boolean deleteDirectory)
    {
        File file = new File(path.toUri());
        if (file.exists())
        {
            _deleteFile(file);
        }
        if (file.isDirectory() && deleteDirectory)
        {
            file.delete();
        }
    }

    /**
     * 更具路径删除文件
     *
     * @param path            路径
     * @param deleteDirectory 是否删除指定的目录,仅限路径为文件夹有效
     */
    public void deleteFile(String path, boolean deleteDirectory)
    {
        Path path1 = Paths.get(path);
        deleteFile(path1, deleteDirectory);
    }

    /**
     * 私有递归删除文件方法
     *
     * @param file file
     * @return boolean
     */
    private boolean _deleteFile(File file)
    {
        boolean _ok = false;
        if (file.isFile())
        {
            _ok = file.delete();
        }
        if (file.isDirectory())
        {
            File[] files = file.listFiles();
            for (File tfile : files)
            {
                _ok = _deleteFile(tfile);
            }
            file.delete();
        }
        return _ok;
    }

    /**
     * 取得文件下所有文件，不含文件夹，不跨层级
     *
     * @param file 目录
     * @return 所有文件
     */
    public File[] getFile(File file)
    {
        List<File> fileList = new ArrayList<>();
        if (file.isFile())
        {
            fileList.add(file);
        } else
        {
            File[] files = file.listFiles(new FileFilter()
            {
                @Override
                public boolean accept(File pathname)
                {
                    return pathname.isFile();
                }
            });
            return files;
        }
        if (fileList.size() > 0)
        {
            return fileList.toArray(new File[fileList.size()]);
        }
        return new File[0];
    }

    /**
     * 更具文件夹路径取得所有文件
     *
     * @param path 文件夹路径 如果是文件路径则返回该文件
     * @return 文件数组
     */
    public File[] getFile(String path)
    {
        File file = new File(path);
        return getFile(file);
    }
}
