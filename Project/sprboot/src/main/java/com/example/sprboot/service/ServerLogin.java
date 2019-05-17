package com.example.sprboot.service;

import com.example.sprboot.common.ToolDB;
import com.example.sprboot.dao.DBBdcInfo;
import com.example.sprboot.dao.DBNetoBdc;
import com.example.sprboot.model.extpand.User;
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
public class ServerLogin
{
    @Autowired
    private ToolDB toolDB;
    @Autowired
    private DBBdcInfo dbBdcInfo;
    @Autowired
    private DBNetoBdc dbNetoBdc;

    public ResponseEntity<?> loginbdc(String name, String pas)
    {
        //String sql = "select t.userid,t.loginname,t.username from bdcinfo.sys_user t";
        String sql="SELECT t.Cus_Id uerid,t.Cus_Name loginname,t.Cus_Name username FROM cus_info t";
        List<User> listuser = dbBdcInfo.selectTList(User.class, sql);

        ResponseEntity<List<User>> responseEntity = new ResponseEntity<>(listuser, HttpStatus.OK);
        return responseEntity;
    }

    public ResponseEntity<?> loginnet(String name, String pas)
    {
        String sql = "SELECT t.Cus_Id uerid,t.Cus_Name loginname,t.Cus_Name username FROM cus_info t";
        List<User> listuser = dbNetoBdc.selectTList(User.class, sql);

        ResponseEntity<List<User>> responseEntity = new ResponseEntity<>(listuser, HttpStatus.OK);
        return responseEntity;
    }
}
