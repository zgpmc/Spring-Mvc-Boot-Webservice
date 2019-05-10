package com.example.sprboot.control;

import com.example.sprboot.model.CUS_INFO;
import com.example.sprboot.service.ImplCus_info;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/user")
    public ResponseEntity<?> getUser(@RequestBody(required = false) CUS_INFO cus_info)
    {
        List<CUS_INFO> list = implICus_info.selectAll(cus_info);
        ResponseEntity<List<CUS_INFO>> responseEntity = new ResponseEntity<>(list, HttpStatus.OK);
        return responseEntity;
    }


    @RequestMapping(value = "/page")
    public ResponseEntity<?> userPage()
    {
        PageInfo<CUS_INFO> page = implICus_info.selectPage();
        ResponseEntity<PageInfo<CUS_INFO>> responseEntity = new ResponseEntity<>(page, HttpStatus.OK);
        return responseEntity;
    }
}
