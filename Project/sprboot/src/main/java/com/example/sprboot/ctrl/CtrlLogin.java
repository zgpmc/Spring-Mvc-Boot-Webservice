package com.example.sprboot.ctrl;

import com.example.sprboot.model.CUS_INFO;
import com.example.sprboot.service.ImplCus_info;
import com.example.sprboot.service.ServerLogin;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创建时间:2019/03/14
 * 创建人:pmc
 * 描述:
 */
@RestController
@RequestMapping("/login")
public class CtrlLogin
{
    @Autowired
    private ServerLogin serverLogin;
    @Autowired
    ImplCus_info implCus_info;
    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping("/test")
    public ResponseEntity<?> test()
    {
        CUS_INFO cusInfo = new CUS_INFO();
        cusInfo.setCusId(0);
        cusInfo.setCusName("测试");
        cusInfo.setCusIdno("测试");
        ResponseEntity<CUS_INFO> responseEntity = new ResponseEntity<>(cusInfo, HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/bdc")
    public ResponseEntity<?> loginbdc(@Param(value = "name") String name, @Param(value = "pas") String pas)
    {
        return serverLogin.loginbdc(name, pas);
    }

    @RequestMapping(value = "/net")
    public ResponseEntity<?> loginnet(String name, String pas)
    {
        return serverLogin.loginnet(name, pas);
    }

    @RequestMapping(value = "/my")
    public ResponseEntity<?> loginmy(@RequestBody(required = false) CUS_INFO cus_info)
    {
        int id = cus_info.getCusId();
        CUS_INFO listuser = implCus_info.selectByPrimaryKey(id);
        //redisTemplate.opsForValue().set("c" + id, cus_info);
        System.out.println("c" + id + "->" + redisTemplate.hasKey("c" + id));
        ResponseEntity<CUS_INFO> responseEntity = new ResponseEntity<>(listuser, HttpStatus.OK);
        return responseEntity;
    }
}
