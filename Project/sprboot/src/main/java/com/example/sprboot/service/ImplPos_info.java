package com.example.sprboot.service;

import com.example.sprboot.dao.ICus_InfoService;
import com.example.sprboot.model.CUS_INFO;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建时间:2019/05/10
 * 创建人:pmc
 * 描述:
 */
@Service
public class ImplPos_info implements ICus_InfoService
{
    @Override
    public int deleteByPrimaryKey(Integer cusId)
    {
        return 0;
    }

    @Override
    public int insert(CUS_INFO record)
    {
        return 0;
    }

    @Override
    public int insertSelective(CUS_INFO record)
    {
        return 0;
    }

    @Override
    public CUS_INFO selectByPrimaryKey(Integer cusId)
    {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(CUS_INFO record)
    {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(CUS_INFO record)
    {
        return 0;
    }

    @Override
    public List<CUS_INFO> selectAll(CUS_INFO cus_info)
    {
        return null;
    }

    @Override
    public PageInfo<CUS_INFO> selectPage()
    {
        return null;
    }
}
