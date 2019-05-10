package com.example.demo.mapper;

import com.example.demo.model.CUS_INFO;

import java.util.List;

public interface CUS_INFOMapper
{
    int deleteByPrimaryKey(Integer cusId);

    int insert(CUS_INFO record);

    int insertSelective(CUS_INFO record);

    CUS_INFO selectByPrimaryKey(Integer cusId);

    int updateByPrimaryKeySelective(CUS_INFO record);

    int updateByPrimaryKey(CUS_INFO record);

    List<CUS_INFO> selectAll(CUS_INFO cus_info);

    List<CUS_INFO> selectPage();
}