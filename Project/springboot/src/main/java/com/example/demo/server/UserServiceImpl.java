package com.example.demo.server;

import com.example.demo.dao.IUserService;
import com.example.demo.expand.PageConfig;
import com.example.demo.mapper.IUserMapper;
import com.example.demo.model.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService
{

    @Resource
    private IUserMapper iUserMapper;

    @Override
    public int insert(User user)
    {
        return iUserMapper.insert(user);
    }

    @Override
    public int insertSelective(User user)
    {
        return iUserMapper.insertSelective(user);
    }

    @Override
    public int insertList(List<User> users)
    {
        return iUserMapper.insertList(users);
    }

    @Override
    public int updateByPrimaryKeySelective(User user)
    {
        return iUserMapper.updateByPrimaryKeySelective(user);
    }


    @Override
    public Page<User> selectByIdOrNameOrNicknameOrPhone(User user)
    {
        PageConfig pageConfig = new PageConfig("id");
        PageHelper.startPage(pageConfig);
        Page<User> page = iUserMapper.selectByIdOrNameOrNicknameOrPhone(user);
        return page;
    }

    @Override
    public List<User> selectAll()
    {
        return iUserMapper.selectAll();
    }
}
