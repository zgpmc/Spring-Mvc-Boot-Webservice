package com.example.sprboot.control;

import com.example.sprboot.model.CUS_INFO;
import com.example.sprboot.service.ImplCus_info;
import com.example.sprboot.service.ServerLogin;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping("/test")
    public String test()
    {
        return "测试112";
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
        List<CUS_INFO> listuser = implCus_info.selectAll(cus_info);
        ResponseEntity<List<CUS_INFO>> responseEntity = new ResponseEntity<>(listuser, HttpStatus.OK);
        return responseEntity;
    }
}
