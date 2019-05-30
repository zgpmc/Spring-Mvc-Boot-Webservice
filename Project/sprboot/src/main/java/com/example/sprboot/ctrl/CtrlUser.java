package com.example.sprboot.ctrl;

import com.example.sprboot.common.ToolFile;
import com.example.sprboot.model.CUS_INFO;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
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
    ToolFile toolFile = new ToolFile();
    @Autowired
    private ImplCus_info implICus_info;
    @Autowired
    private RedisTemplate redisTemplate;

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
    public String file(@RequestParam(value = "file") List<MultipartFile> fileList, RedirectAttributes redirectAttributes)
    {
        String msg = "";
        Iterator<MultipartFile> iterableFile = fileList.iterator();
        while (iterableFile.hasNext())
        {
            MultipartFile file = iterableFile.next();
            if (file.isEmpty())
            {
                msg += "请选择上传的文件!";
                redirectAttributes.addFlashAttribute("请选择上传的文件！");
                return msg;
            }
            try
            {
                long size = file.getSize();
                System.out.println(size);
                String filename = file.getOriginalFilename();
                msg += "文件:" + filename + "->大小:" + new BigDecimal("" + (float) (size / 1024)).setScale(0, BigDecimal
                        .ROUND_HALF_UP) +
                        "KB->";
                String root = "/upfile/";
                Path path = Paths.get(root + filename);
                String projectPath = System.getProperty("user.dir");
                System.out.println(projectPath);
                toolFile.deleteFile(path, false);
                toolFile.createFile(root);
                file.transferTo(path);
                redirectAttributes.addFlashAttribute("msg", "成功！");
                msg += "上传成功！。";
            } catch (IOException e)
            {
                e.printStackTrace();
                msg += "上传失败！。";
            }
        }
        return msg;
    }

    @RequestMapping(value = "/down")
    public void down(HttpServletResponse response)
    {
        String filepath = "F:\\upfile";
        File fileDir = new File(filepath);
        File[] files = toolFile.getFile(filepath);
        if (fileDir.exists())
        {
            if (files.length > 0)
            {
                File fi = files[0];
                try
                {
                    response.setContentType("application/force-download;charset=utf-8");// 设置强制下载不打开
                    response.setHeader("Content-Disposition", "attachment; filename*=utf-8''" + URLEncoder.encode(fi.getName(), "UTF-8"));
                    byte[] filebyte = new byte[(int) fi.length()];
                    OutputStream outputStream = response.getOutputStream();
                    FileInputStream fileInputStream = new FileInputStream(fi);
                    fileInputStream.read(filebyte);
                    outputStream.write(filebyte);
                    outputStream.flush();
                    outputStream.close();
                    fileInputStream.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
