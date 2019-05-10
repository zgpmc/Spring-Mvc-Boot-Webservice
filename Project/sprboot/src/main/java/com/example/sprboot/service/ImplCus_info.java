package com.example.sprboot.service;

import com.example.sprboot.dao.ICus_InfoService;
import com.example.sprboot.expand.PageConfig;
import com.example.sprboot.mapper.CUS_INFOMapper;
import com.example.sprboot.model.CUS_INFO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建时间:2019/05/10
 * 创建人:pmc
 * 描述:
 */
@Service
public class ImplCus_info implements ICus_InfoService
{
    @Autowired
    private CUS_INFOMapper cus_infoMapper;

    @Override
    public int deleteByPrimaryKey(Integer cusId)
    {
        return cus_infoMapper.deleteByPrimaryKey(cusId);
    }

    @Override
    public int insert(CUS_INFO record)
    {
        return cus_infoMapper.insertSelective(record);
    }

    @Override
    public int insertSelective(CUS_INFO record)
    {
        return cus_infoMapper.insertSelective(record);
    }

    @Override
    public CUS_INFO selectByPrimaryKey(Integer cusId)
    {
        return cus_infoMapper.selectByPrimaryKey(cusId);
    }

    @Override
    public int updateByPrimaryKeySelective(CUS_INFO record)
    {
        return cus_infoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CUS_INFO record)
    {
        return cus_infoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<CUS_INFO> selectAll(CUS_INFO cus_info)
    {
        return cus_infoMapper.selectAll(cus_info);
    }

    @Override
    public PageInfo<CUS_INFO> selectPage()
    {
        PageConfig pageConfig = new PageConfig("id");
        PageHelper.startPage(pageConfig);
        List<CUS_INFO> pageList = cus_infoMapper.selectPage();
        PageInfo pageInfo = new PageInfo(pageList);
        return pageInfo;
    }
}
