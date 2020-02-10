package com.water.neptune.ets.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Zhang Miaojie
 * @date 2020/2/10
 */
@RequestMapping(value = "/")
@RestController
public class UploadController {

    /**
     * 上传视频文件
     *
     * @param file
     */
    @RequestMapping(value = "/upload/video")
    public void uploadVideo(@RequestParam("file") MultipartFile file) {
        // 1，校验文件

        // 2，上传文件

        // 3，提交到转码服务
    }
}
