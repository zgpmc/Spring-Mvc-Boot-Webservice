package com.example.demo.control;

import com.example.demo.model.SysUser;
import com.example.demo.server.ServLogin;
import com.example.demo.server.SysUserServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 创建时间:2019/03/14
 * 创建人:pmc
 * 描述:
 */
@RestController
@RequestMapping(value = "")
public class CtrlLogin
{
    @Autowired
    private ServLogin servLogin;
    @Autowired
    SysUserServiceImpl sysUserService;

    @RequestMapping(value = "/test")
    public String test()
    {
        return "测试112";
    }

    @RequestMapping(value = "/bdc", method = RequestMethod.GET)
    public ResponseEntity<?> loginbdc(@Param(value = "name") String name, @Param(value = "pas") String pas)
    {
        return servLogin.loginbdc(name, pas);
    }

    @RequestMapping(value = "/net")
    public ResponseEntity<?> loginnet(String name, String pas)
    {
        return servLogin.loginnet(name, pas);
    }

    @RequestMapping(value = "/my")
    public ResponseEntity<?> loginmy()
    {
        List<SysUser> listuser = sysUserService.findAll();
        ResponseEntity<List<SysUser>> responseEntity = new ResponseEntity<>(listuser, HttpStatus.OK);
        return responseEntity;
    }
}
