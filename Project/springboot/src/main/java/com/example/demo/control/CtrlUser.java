package com.example.demo.control;

import com.example.demo.model.User;
import com.example.demo.server.UserServiceImpl;
import com.github.pagehelper.Page;
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
    private UserServiceImpl userServiceImpl;

    @RequestMapping(value = "/user")
    public ResponseEntity<?> getUser()
    {
        List<User> list = userServiceImpl.selectAll();
        ResponseEntity<List<User>> responseEntity = new ResponseEntity<>(list, HttpStatus.OK);
        return responseEntity;
    }


    @RequestMapping(value = "/page")
    public ResponseEntity<?> userPage(@RequestBody User user)
    {
        Page<User> page = userServiceImpl.selectByIdOrNameOrNicknameOrPhone(user);
        ResponseEntity<Page<User>> responseEntity = new ResponseEntity<>(page, HttpStatus.OK);
        return responseEntity;
    }
}
