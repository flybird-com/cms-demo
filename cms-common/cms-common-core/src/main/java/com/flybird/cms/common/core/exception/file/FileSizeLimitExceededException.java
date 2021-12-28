package com.flybird.cms.common.core.exception.file;

/**
 * description: 文件名大小限制异常类
 *
 * @author: flybird
 * @date: 2021-12-28 22:13:05
 */
public class FileSizeLimitExceededException extends FileException {
    private static final long serialVersionUID = 1L;

    public FileSizeLimitExceededException(long defaultMaxSize) {
        super("upload.exceed.maxSize", new Object[]{defaultMaxSize});
    }
}
