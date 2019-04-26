package com.example.demo.dao;

import com.example.demo.model.SysUser;

import java.util.List;

public interface SysUserService
{

    int insert(SysUser sysUser);

    int insertSelective(SysUser sysUser);

    int insertList(List<SysUser> sysUsers);

    int updateByPrimaryKeySelective(SysUser sysUser);

    SysUser selectByUserid(String userid);

    List<SysUser> findAll();
}
