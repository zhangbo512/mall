package com.macro.mall.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.*;
import java.net.URI;
import java.nio.channels.FileChannel;

/**
 * description:文件上传
 *
 * @author zb 2019/08/21 19:53
 */
@Slf4j
@RestController
@Api(tags = "FileUpdateController", description = "文件上传")
@RequestMapping(value = "file")
public class FileUpdateController {

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }
        String fileName = file.getOriginalFilename();
        String filePath = "D:\\File_Save_Path\\";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            log.info("上传成功");
            return "上传成功";
        } catch (IOException e) {
            log.error(e.toString(), e);
        }
        return "上传失败！";
    }




    public void nioCopy(MultipartFile file) {
        FileChannel fromChannel = null;
        FileChannel toChannel = null;
        try {
            fromChannel = new FileInputStream((File)file).getChannel();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String path = "D:\\File_Save_Path\\";
        try {
            toChannel = new RandomAccessFile(path, "rw").getChannel();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        long position = 0;
        long count = 0;
        try {
            count = fromChannel.size();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fromChannel.transferTo(position, count, toChannel);
            fromChannel.close();
            toChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
