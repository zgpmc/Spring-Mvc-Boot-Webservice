package com.example.demo.expand;

import com.example.demo.model.extpand.ModelPage;
import com.github.pagehelper.IPage;

/**
 * 创建时间:2019/5/1
 * 创建人:Administrator
 * 描述:
 */
public class PageConfig implements IPage
{
    private int _pageNum = 1;
    private int _pageSize = 20;
    private String _pageOrder = "";

    public PageConfig(String _pageOrder)
    {
        this._pageOrder = _pageOrder;
    }

    /**
     * 翻页对象
     *
     * @param _pageNum   当前页
     * @param _pageSize  页面大小
     * @param _pageOrder 排序字段
     */
    public PageConfig(int _pageNum, int _pageSize, String _pageOrder)
    {
        this._pageSize = _pageSize;
        this._pageNum = _pageNum;
        this._pageOrder = _pageOrder;
    }

    public PageConfig(ModelPage modelPage)
    {
        this._pageSize = modelPage.getPagesize();
        this._pageNum = modelPage.getPagenum();
        this._pageOrder = modelPage.getPageorder();
    }

    @Override
    public Integer getPageNum()
    {
        return this._pageNum;
    }

    @Override
    public Integer getPageSize()
    {
        return this._pageSize;
    }

    @Override
    public String getOrderBy()
    {
        return this._pageOrder;
    }
}
