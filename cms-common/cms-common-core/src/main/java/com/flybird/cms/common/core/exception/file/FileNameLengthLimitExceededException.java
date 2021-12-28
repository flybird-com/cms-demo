package com.flybird.cms.common.core.exception.file;

/**
 * description: 文件名称超长限制异常类
 *
 * @author: flybird
 * @date: 2021-12-28 22:12:35
 */
public class FileNameLengthLimitExceededException extends FileException {
    private static final long serialVersionUID = 1L;

    public FileNameLengthLimitExceededException(int defaultFileNameLength) {
        super("upload.filename.exceed.length", new Object[]{defaultFileNameLength});
    }
}
