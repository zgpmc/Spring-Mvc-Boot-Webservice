package com.example.demo.dao;

import com.example.demo.model.User;
import com.github.pagehelper.Page;

import java.util.List;

public interface IUserService
{

    int insert(User user);

    int insertSelective(User user);

    int insertList(List<User> users);

    int updateByPrimaryKeySelective(User user);

    Page<User> selectByIdOrNameOrNicknameOrPhone(User user);

    List<User> selectAll();
}
