package com.example.demo.mapper;

import com.example.demo.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@Mapper
@ComponentScan
public interface ISysUserMapper
{
    int insert(@Param("sysUser") SysUser sysUser);

    int insertSelective(@Param("sysUser") SysUser sysUser);

    int insertList(@Param("sysUsers") List<SysUser> sysUsers);

    int updateByPrimaryKeySelective(@Param("sysUser") SysUser sysUser);

    SysUser selectByUserid(@Param("userid") String userid);

    List<SysUser> findAll();

}
