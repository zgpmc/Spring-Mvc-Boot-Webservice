package com.example.sprboot.ctrl;

import com.example.sprboot.model.CUS_INFO;
import com.example.sprboot.service.ImplCus_info;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 创建时间:2019/5/1
 * 创建人:Administrator
 * 描述:
 */
@RestController
@RequestMapping(value = "/user")
public class CtrlUser
{
    @Autowired
    private ImplCus_info implICus_info;
    @Autowired
    private RedisTemplate redisTemplate;

    //@CacheEvict是用来标注在需要清除缓存元素的方法或类上的,@CacheEvict可以指定的属性有value、key、condition、allEntries和beforeInvocation allEntries是boolean类型，表示是否需要清除缓存中的所有元素。默认为false beforeInvocation:清除操作默认是在对应方法成功执行之后触发的，即方法如果因为抛出异常而未能成功返回时也不会触发清除操作,为true时，Spring会在调用该方法之前清除缓存中的指定元素@CacheEvict(value = "cache3", allEntries = true) })
    @RequestMapping(value = "/user")
//value: value属性指定Cache名称 key:Spring缓存方法的返回结果时对应的key的。该属性支持SpringEL表达式 condition:condition属性指定发生的条件 SpringEL表达式@Cacheable(value={"users"}, key="#cus_info.id", condition="#cus_info.id%2==0")
    public ResponseEntity<?> getUser(@RequestBody(required = true) CUS_INFO cus_info)
    {
        CUS_INFO cusInfo = implICus_info.selectByPrimaryKey(cus_info.getCusId());
        ValueOperations<String, CUS_INFO> valueOperations = redisTemplate.opsForValue();
        String key = "cus_" + cus_info.getCusId();
        valueOperations.set(key, cusInfo);
        System.out.println(key + "->" + redisTemplate.hasKey(key));
        ResponseEntity<CUS_INFO> responseEntity = new ResponseEntity<>(cusInfo, HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/users")
    public ResponseEntity<?> getUsers(@RequestBody(required = false) CUS_INFO cus_info)
    {
        List<CUS_INFO> list = implICus_info.selectAll(cus_info);
        ValueOperations<String, List<CUS_INFO>> valueOperations = redisTemplate.opsForValue();
        String key = "cuss_" + cus_info.getCusId();
        valueOperations.set(key, list);
        System.out.println(key + "->" + redisTemplate.hasKey(key));
        ResponseEntity<List<CUS_INFO>> responseEntity = new ResponseEntity<>(list, HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/page")
    public ResponseEntity<?> userPage()
    {
        PageInfo<CUS_INFO> pageInfo = implICus_info.selectPage();
        ResponseEntity<PageInfo<CUS_INFO>> responseEntity = new ResponseEntity<>(pageInfo, HttpStatus.OK);
        return responseEntity;
    }
}
