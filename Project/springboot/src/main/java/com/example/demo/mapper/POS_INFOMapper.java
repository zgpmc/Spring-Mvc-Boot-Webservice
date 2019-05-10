package com.example.demo.mapper;

import com.example.demo.model.POS_INFO;

public interface POS_INFOMapper {
    int deleteByPrimaryKey(Integer posId);

    int insert(POS_INFO record);

    int insertSelective(POS_INFO record);

    POS_INFO selectByPrimaryKey(Integer posId);

    int updateByPrimaryKeySelective(POS_INFO record);

    int updateByPrimaryKey(POS_INFO record);
}