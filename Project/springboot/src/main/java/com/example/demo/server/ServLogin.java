package com.example.demo.server;

import com.example.demo.common.ToolDB;
import com.example.demo.dao.DBBdcInfo;
import com.example.demo.dao.DBNetoBdc;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 创建时间:2019/03/14
 * 创建人:pmc
 * 描述:
 */
@Service
public class ServLogin
{
    @Autowired
    private ToolDB toolDB;
    @Autowired
    private DBBdcInfo dbBdcInfo;
    @Autowired
    private DBNetoBdc dbNetoBdc;

    public ResponseEntity<?> loginbdc(String name, String pas)
    {
        String sql = "select t.userid,t.loginname,t.username from bdcinfo.sys_user t";
        List<User> listuser = dbBdcInfo.selectTList(User.class, sql);

        ResponseEntity<List<User>> responseEntity = new ResponseEntity<>(listuser, HttpStatus.OK);
        return responseEntity;
    }

    public ResponseEntity<?> loginnet(String name, String pas)
    {
        String sql = "select u.login_name loginname,u.user_name username,u.region_code userid from netobdc.st_user u where rownum <11";
        List<User> listuser = dbNetoBdc.selectTList(User.class, sql);

        ResponseEntity<List<User>> responseEntity = new ResponseEntity<>(listuser, HttpStatus.OK);
        return responseEntity;
    }
}
