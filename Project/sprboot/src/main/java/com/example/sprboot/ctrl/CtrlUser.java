package com.example.sprboot.ctrl;

import com.example.sprboot.model.CUS_INFO;
import com.example.sprboot.service.FileUporDown;
import com.example.sprboot.service.ImplCus_info;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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
    @Autowired
    private FileUporDown fileUporDown;

    @RequestMapping(value = "/user")
    public ResponseEntity<?> getUser(@RequestBody() CUS_INFO cus_info)
    {
        CUS_INFO cusInfo = implICus_info.selectByPrimaryKey(cus_info.getCusId());
        ResponseEntity<CUS_INFO> responseEntity = new ResponseEntity<>(cusInfo, HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/users")
    public ResponseEntity<?> getUsers(@RequestBody() CUS_INFO cus_info)
    {
        List<CUS_INFO> list = implICus_info.selectAll(cus_info);
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

    @RequestMapping(value = "/ins")
    public ResponseEntity<?> insert(@RequestBody CUS_INFO cus_info)
    {
        Integer num = implICus_info.insert(cus_info);
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>(num, HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/upd")
    public ResponseEntity<?> update(@RequestBody CUS_INFO cus_info)
    {
        Integer num = implICus_info.updateByPrimaryKeySelective(cus_info);
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>(num, HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/del")
    public ResponseEntity<?> delete(@RequestBody CUS_INFO cus_info)
    {
        Integer num = implICus_info.deleteByPrimaryKey(cus_info.getCusId());
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>(num, HttpStatus.OK);
        return responseEntity;
    }

    @RequestMapping(value = "/file")
    public String file(@RequestParam(value = "file") List<MultipartFile> fileList)
    {
        return fileUporDown.file(fileList);
    }

    @RequestMapping(value = "/down")
    public void down(HttpServletResponse response)
    {
        fileUporDown.down(response);
    }
}
