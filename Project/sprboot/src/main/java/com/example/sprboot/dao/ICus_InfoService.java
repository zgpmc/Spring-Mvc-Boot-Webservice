package com.example.sprboot.dao;

import com.example.sprboot.model.CUS_INFO;
import com.github.pagehelper.PageInfo;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface ICus_InfoService
{
    int deleteByPrimaryKey(Integer cusId);

    int insert(CUS_INFO record);

    int insertSelective(CUS_INFO record);

    @Cacheable(value = "'cus", key = "'cus_'+#cusId")
    CUS_INFO selectByPrimaryKey(Integer cusId);

    int updateByPrimaryKeySelective(CUS_INFO record);

    int updateByPrimaryKey(CUS_INFO record);

    @Cacheable(value = "cuss", key = "'cuss_'+cus_info.cusId")
    List<CUS_INFO> selectAll(CUS_INFO cus_info);

    @Cacheable(value = "page")
    PageInfo<CUS_INFO> selectPage();
}