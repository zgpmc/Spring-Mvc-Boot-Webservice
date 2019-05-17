package com.example.sprboot.service;

import com.example.sprboot.dao.ICus_InfoService;
import com.example.sprboot.expand.PageConfig;
import com.example.sprboot.mapper.CUS_INFOMapper;
import com.example.sprboot.model.CUS_INFO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    public CUS_INFOMapper cus_infoMapper;

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

    //@CacheEvict是用来标注在需要清除缓存元素的方法或类上的,@CacheEvict可以指定的属性有value、key、condition、allEntries和beforeInvocation allEntries是boolean类型，表示是否需要清除缓存中的所有元素。默认为false beforeInvocation:清除操作默认是在对应方法成功执行之后触发的，即方法如果因为抛出异常而未能成功返回时也不会触发清除操作,为true时，Spring会在调用该方法之前清除缓存中的指定元素@CacheEvict(value = "cache3", allEntries = true) })
    //value: value属性指定Cache名称 key:Spring缓存方法的返回结果时对应的key的。该属性支持SpringEL表达式 condition:condition属性指定发生的条件 SpringEL表达式@Cacheable(value={"users"}, key="#cus_info.id", condition="#cus_info.id%2==0") key 和自定义的key生成策略不能同时存在
    @Cacheable(value = "cusinfo", key = "'cus'+#cusId")
    @Override
    public CUS_INFO selectByPrimaryKey(Integer cusId)
    {
        System.out.println("没有使用缓存");
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

    @Cacheable(value = "page")
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
