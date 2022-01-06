package com.flybird.cms.file.service;

import com.flybird.cms.file.utils.FileUploadUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * description: 本地文件存储
 *
 * @author: flybird
 * @date: 2022-01-03 20:44:15
 */
//@Primary
@Service
public class LocalSysFileServiceImpl implements ISysFileService {
    /**
     * 资源映射路径 前缀
     */
    @Value("${file.prefix}")
    public String localFilePrefix;

    /**
     * 域名或本机访问地址
     */
    @Value("${file.domain}")
    public String domain;

    /**
     * 上传文件存储在本地的根路径
     */
    @Value("${file.path}")
    private String localFilePath;

    /**
     * 本地文件上传接口
     *
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        String name = FileUploadUtils.upload(localFilePath, file);
        String url = domain + localFilePrefix + name;
        return url;
    }
}
