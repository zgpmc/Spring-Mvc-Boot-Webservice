package com.example.demo.mapper;

import com.example.demo.model.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IUserMapper
{
    int insert(@Param("user") User user);

    int insertSelective(@Param("user") User user);

    int insertList(@Param("users") List<User> users);

    int updateByPrimaryKeySelective(@Param("user") User user);

    Page<User> selectByIdOrNameOrNicknameOrPhone(User user);

    List<User> selectAll();


}
