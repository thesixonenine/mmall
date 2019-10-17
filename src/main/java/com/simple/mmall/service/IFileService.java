package com.simple.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author simple
 */
public interface IFileService {

    /**
     * 上传文件
     *
     * @param file
     * @param path
     * @return
     */
    String upload(MultipartFile file, String path);
}
