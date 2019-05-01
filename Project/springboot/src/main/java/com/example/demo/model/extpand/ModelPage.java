package com.example.demo.model.extpand;

/**
 * 创建时间:2019/5/1
 * 创建人:Administrator
 * 描述:
 */
public class ModelPage
{
    private int pagenum;
    private int pagesize;
    private String pageorder;

    public int getPagenum()
    {
        return pagenum;
    }

    public void setPagenum(int pagenum)
    {
        this.pagenum = pagenum;
    }

    public int getPagesize()
    {
        return pagesize;
    }

    public void setPagesize(int pagesize)
    {
        this.pagesize = pagesize;
    }

    public String getPageorder()
    {
        return pageorder;
    }

    public void setPageorder(String pageorder)
    {
        this.pageorder = pageorder;
    }
}
