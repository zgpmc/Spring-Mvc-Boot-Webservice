package com.example.sprboot.service;

import com.example.sprboot.common.ToolFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
 * 创建时间:2019/05/30
 * 创建人:pmc
 * 描述:
 */
@Service
public class FileUporDown
{
    ToolFile toolFile = new ToolFile();

    public String file(List<MultipartFile> fileList)
    {
        String msg = "";
        Iterator<MultipartFile> iterableFile = fileList.iterator();
        while (iterableFile.hasNext())
        {
            MultipartFile file = iterableFile.next();
            if (file.isEmpty())
            {
                msg += "请选择上传的文件!";
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
                msg += "上传成功！。";
            } catch (IOException e)
            {
                e.printStackTrace();
                msg += "上传失败！。";
            }
        }
        return msg;
    }

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
