package com.flybird.cms.common.core.exception.file;


import com.flybird.cms.common.core.exception.base.BaseException;

/**
 * description: 文件信息异常类
 *
 * @author: flybird
 * @date: 2021-12-28 22:12:23
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}
