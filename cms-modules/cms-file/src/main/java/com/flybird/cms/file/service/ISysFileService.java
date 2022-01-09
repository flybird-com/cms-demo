package com.flybird.cms.file.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * description: 文件上传接口
 *
 * @author: flybird
 * @date: 2022-01-03 20:43:31
 */
public interface ISysFileService {
    /**
     * 文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    public String uploadFile(MultipartFile file) throws Exception;
}
