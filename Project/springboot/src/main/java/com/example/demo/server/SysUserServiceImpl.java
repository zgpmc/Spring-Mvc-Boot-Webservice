package com.example.demo.server;

import com.example.demo.dao.SysUserService;
import com.example.demo.mapper.SysUserMapper;
import com.example.demo.model.SysUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService
{

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public int insert(SysUser sysUser)
    {
        return sysUserMapper.insert(sysUser);
    }

    @Override
    public int insertSelective(SysUser sysUser)
    {
        return sysUserMapper.insertSelective(sysUser);
    }

    @Override
    public int insertList(List<SysUser> sysUsers)
    {
        return sysUserMapper.insertList(sysUsers);
    }

    @Override
    public int updateByPrimaryKeySelective(SysUser sysUser)
    {
        return sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    @Override
    public SysUser selectByUserid(String userid)
    {
        return sysUserMapper.selectByUserid(userid);
    }

    @Override
    public List<SysUser> findAll()
    {
        return sysUserMapper.findAll();
    }


}
